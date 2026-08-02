const mongoose = require("mongoose");

const transactionSchema = new mongoose.Schema(
  {
    bookId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "Book",
      required: true,
    },
    userId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "User",
      required: true,
    },

    // 🚀 BUG FIX: Agar frontend date na bheje, to by default aaj ki date le lo
    issueDate: { type: Date, default: Date.now },
    returnDate: { type: Date }, // Optional at the time of request

    status: {
      type: String,
      enum: ["Pending", "Issued", "Returned", "Rejected"],
      default: "Pending",
    },
  },
  { timestamps: true },
);

module.exports = mongoose.model("Transaction", transactionSchema);
