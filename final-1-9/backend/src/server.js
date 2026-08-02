require("dotenv").config();
const http = require("http");
const { Server } = require("socket.io");
const app = require("./app");
const connectDB = require("./config/db");
const User = require("./models/User");
const Transaction = require("./models/Transaction");
const Notification = require("./models/Notification");
const cron = require("node-cron");

const PORT = process.env.PORT || 5000;
const server = http.createServer(app);

const io = new Server(server, {
  cors: { origin: "*", methods: ["GET", "POST", "PUT", "DELETE", "PATCH"] },
  pingTimeout: 60000,
  pingInterval: 25000,
});

app.set("io", io);

io.on("connection", (socket) => {
  console.log(`🔌 New client connected: ${socket.id}`);
  socket.on("disconnect", () =>
    console.log(`❌ Client disconnected: ${socket.id}`),
  );
});

// 🚀 CRON JOB: Har Minute chalega (Accurate Dates Track Karega)
cron.schedule("* * * * *", async () => {
  try {
    const today = new Date();
    // Indian Time/Local Time track karne ke liye
    const offset = today.getTimezoneOffset() * 60000;
    const todayStr = new Date(today.getTime() - offset)
      .toISOString()
      .split("T")[0];

    // Time hata do, sirf Date match karo
    today.setHours(0, 0, 0, 0);

    const activeIssues = await Transaction.find({ status: "Issued" })
      .populate("userId")
      .populate("bookId");
    let hasChanges = false;

    for (let issue of activeIssues) {
      if (!issue.userId || !issue.bookId || !issue.returnDate) continue;

      const returnD = new Date(issue.returnDate);
      returnD.setHours(0, 0, 0, 0);

      // Din ka difference nikalna
      const diffTime = returnD.getTime() - today.getTime();
      const diffDays = Math.round(diffTime / (1000 * 60 * 60 * 24));

      // 🚀 FIX: Get Short Transaction ID in lowercase (e.g. #fba1d3)
      const txIdShort = issue._id.toString().slice(-6).toLowerCase();

      // 🛑 SCENARIO 1: OVERDUE (Jis din date ho ya uske baad)
      // Jab diffDays 0 ya minus ho jaye
      if (diffDays <= 0) {
        if (issue.userId.status !== "Deactivate") {
          await User.findByIdAndUpdate(issue.userId._id, {
            status: "Deactivate",
          });
          hasChanges = true;
        }

        // Notify only once for overdue
        if (!issue.overdueNotified) {
          // Message For Student
          await Notification.create({
            title: "Book Overdue! Account Deactivated",
            desc: `Please return "${issue.bookId.title}" (Tx ID: #${txIdShort}) immediately to reactivate account.`,
            type: "danger",
            icon: "fa-ban",
            target: issue.userId.email,
          });
          // Message For Admin
          await Notification.create({
            title: "Student Overdue",
            desc: `${issue.userId.name}'s account deactivated for overdue book "${issue.bookId.title}" (Tx ID: #${txIdShort}).`,
            type: "danger",
            icon: "fa-ban",
            target: "admin",
          });

          issue.overdueNotified = true;
          await issue.save();
          hasChanges = true;
        }
      }
      // ⚠️ SCENARIO 2: DAILY REMINDER (5 days pehle se lekar 1 day pehle tak)
      else if (diffDays <= 5 && diffDays >= 1) {
        // Check karega ki aaj ka reminder bheja gaya hai ya nahi
        if (issue.lastReminderDate !== todayStr) {
          const dueText = `in ${diffDays} day${diffDays > 1 ? "s" : ""}`;

          await Notification.create({
            title: "Reminder: Return Date Approaching",
            desc: `"${issue.bookId.title}" (Tx ID: #${txIdShort}) is due ${dueText}.`,
            type: "warning",
            icon: "fa-clock",
            target: issue.userId.email,
          });

          issue.lastReminderDate = todayStr; // Update date ki aaj message chala gaya
          await issue.save();
          hasChanges = true;
        }
      }
    }

    // Agar koi naya message gaya hai ya status deactivate hua hai, toh live socket bhejo
    if (hasChanges) {
      io.emit("db_updated");
    }
  } catch (error) {
    console.error("Cron Error:", error);
  }
});

server.listen(PORT, "0.0.0.0", () => {
  console.log(`🚀 Server running with WebSockets on port ${PORT}`);
});

connectDB().then(async () => {
  try {
    const adminExists = await User.findOne({ role: "admin" });
    if (!adminExists) {
      await User.create({
        name: "Admin User",
        username: "admin",
        email: "admin@library.com",
        phone: "0000000000",
        password: "password123",
        role: "admin",
        status: "Active",
      });
      console.log("✅ Admin user automatically created!");
    }
  } catch (err) {}
});
