const Transaction = require("../models/Transaction");
const Book = require("../models/Book");

exports.getTransactions = exports.getAllTransactions = async (req, res) => {
  try {
    const query = req.user.role === "admin" ? {} : { userId: req.user._id };
    const transactions = await Transaction.find(query)
      .populate("bookId", "title author coverImage")
      .populate("userId", "name email")
      .sort({ createdAt: -1 });
    res.json({ success: true, data: transactions });
  } catch (error) {
    res.status(500).json({ success: false, message: error.message });
  }
};

// CREATE TRANSACTION
exports.createTransaction = exports.addTransaction = async (req, res) => {
  try {
    if (!req.body.userId && req.user) req.body.userId = req.user._id;

    let finalStatus = req.body.status;
    if (!finalStatus)
      finalStatus = req.user.role === "admin" ? "Issued" : "Pending";

    req.body.status = finalStatus;
    req.body.issueDate = new Date();

    if (finalStatus === "Issued") {
      const returnDate = new Date();
      returnDate.setDate(returnDate.getDate() + 7);
      req.body.returnDate = req.body.returnDate || returnDate;
    }

    const transaction = await Transaction.create(req.body);

    // 🚀 BUG 1 FIX: Corrected variable from 'availableQty' to 'available'
    if (finalStatus === "Issued") {
      const book = await Book.findById(transaction.bookId);
      if (book && book.available > 0) {
        book.available = book.available - 1;
        await book.save();
      }
    }

    res.status(201).json({ success: true, data: transaction });
  } catch (error) {
    res.status(500).json({ success: false, message: error.message });
  }
};

// UPDATE STATUS
exports.updateStatus = exports.updateTxStatus = async (req, res) => {
  try {
    const { status } = req.body;
    let updateData = { status };

    if (status === "Issued") {
      updateData.issueDate = new Date();
      const returnDate = new Date();
      returnDate.setDate(returnDate.getDate() + 7);
      updateData.returnDate = returnDate;
    }

    const transaction = await Transaction.findByIdAndUpdate(
      req.params.id,
      updateData,
      { returnDocument: "after", runValidators: true },
    );

    if (!transaction)
      return res
        .status(404)
        .json({ success: false, message: "Transaction not found" });

    // 🚀 BUG 1 FIX: Corrected variable from 'availableQty' to 'available'
    if (status === "Issued" || status === "Returned") {
      const book = await Book.findById(transaction.bookId);
      if (book) {
        book.available =
          status === "Issued" ? book.available - 1 : book.available + 1;
        await book.save();
      }
    }

    res.json({ success: true, data: transaction });
  } catch (error) {
    res.status(500).json({ success: false, message: error.message });
  }
};

exports.deleteTransaction = async (req, res) => {
  try {
    const transaction = await Transaction.findByIdAndDelete(req.params.id);
    if (!transaction)
      return res
        .status(404)
        .json({ success: false, message: "Transaction not found" });
    res.json({ success: true, message: "Transaction deleted" });
  } catch (error) {
    res.status(500).json({ success: false, message: error.message });
  }
};
