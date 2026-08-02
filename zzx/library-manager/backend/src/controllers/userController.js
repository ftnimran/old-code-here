const User = require("../models/User");
const bcrypt = require("bcryptjs"); // 🚀 BCRYPT IMPORT KIYA (Password hash karne ke liye)

// 1. GET ALL USERS (Members List)
exports.getUsers = exports.getAllUsers = async (req, res) => {
  try {
    // 🚀 BUG 2 FIX: Ab API sirf unko fetch karegi jo "admin" nahi hain!
    const users = await User.find({ role: { $ne: "admin" } }).select(
      "-password",
    );
    res.json({ success: true, data: users });
  } catch (error) {
    res.status(500).json({ success: false, message: error.message });
  }
};

// 2. GET SINGLE USER
exports.getUserById = exports.getUser = async (req, res) => {
  try {
    const user = await User.findById(req.params.id).select("-password");
    if (!user)
      return res
        .status(404)
        .json({ success: false, message: "User not found" });
    res.json({ success: true, data: user });
  } catch (error) {
    res.status(500).json({ success: false, message: error.message });
  }
};

// 3. UPDATE USER PROFILE (Profile / Password Change)
exports.updateUser = exports.updateProfile = async (req, res) => {
  try {
    // 🚀 BUG 1 FIX: Agar User ne Password change karne ka input diya hai, toh pehle usko HASH karo!
    if (req.body.password) {
      const salt = await bcrypt.genSalt(10);
      req.body.password = await bcrypt.hash(req.body.password, salt);
    }

    const user = await User.findByIdAndUpdate(req.params.id, req.body, {
      returnDocument: "after",
      runValidators: true,
    }).select("-password");

    if (!user)
      return res
        .status(404)
        .json({ success: false, message: "User not found" });
    res.json({ success: true, data: user });
  } catch (error) {
    res.status(500).json({ success: false, message: error.message });
  }
};

// 4. DELETE USER
exports.deleteUser = async (req, res) => {
  try {
    const user = await User.findByIdAndDelete(req.params.id);
    if (!user)
      return res
        .status(404)
        .json({ success: false, message: "User not found" });
    res.json({ success: true, message: "User deleted successfully" });
  } catch (error) {
    res.status(500).json({ success: false, message: error.message });
  }
};
