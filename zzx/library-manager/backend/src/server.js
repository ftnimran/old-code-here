require("dotenv").config();
const http = require("http");
const { Server } = require("socket.io");
const app = require("./app");
const connectDB = require("./config/db");
const User = require("./models/User");

const PORT = process.env.PORT || 5000;

// HTTP Server banaya (Express app ko wrap karke)
const server = http.createServer(app);

// Socket.io Initialize kiya
const io = new Server(server, {
  cors: {
    origin: "*", // Frontend ko connect hone ki permission di
    methods: ["GET", "POST", "PUT", "DELETE", "PATCH"],
  },
});

// io object ko express app me save kar diya taaki routes/middleware use kar sakein
app.set("io", io);

io.on("connection", (socket) => {
  console.log(`🔌 New client connected: ${socket.id}`);
  socket.on("disconnect", () => {
    console.log(`❌ Client disconnected: ${socket.id}`);
  });
});

// ... (Upar ka code same rahega jahan io.on connection hai) ...

// 🚀 FIX: Server ko pehle start karein taaki Render timeout na ho!
server.listen(PORT, "0.0.0.0", () => {
  console.log(`🚀 Server running with WebSockets on port ${PORT}`);
});

// Phir Database connect karein aur Admin check karein
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
      console.log("✅ Admin user automatically created in MongoDB!");
    }
  } catch (err) {
    console.error("❌ Error creating admin user:", err.message);
  }
});