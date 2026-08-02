const User = require("../models/User");
const jwt = require("jsonwebtoken");

const generateToken = (id) =>
  jwt.sign({ id }, process.env.JWT_SECRET, { expiresIn: "30d" });

exports.registerUser = async (req, res) => {
  const { name, username, email, phone, password } = req.body;

  try {
    if (!name || !username || !email || !phone || !password) {
      return res
        .status(400)
        .json({ success: false, message: "Sabhi fields bharna zaroori hai!" });
    }

    const userExists = await User.findOne({ $or: [{ email }, { username }] });
    if (userExists) {
      return res
        .status(400)
        .json({
          success: false,
          message: "Email ya Username pehle se kisi ne le liya hai!",
        });
    }

    const user = await User.create({
      name,
      username,
      email,
      phone,
      password,
      role: "student",
    });

    res.status(201).json({
      success: true,
      user: {
        id: user._id,
        name: user.name,
        username: user.username,
        email: user.email,
        phone: user.phone,
        role: user.role,
        avatar: user.avatar,
        lastSeenNotificationId: user.lastSeenNotificationId, // 🔥 Added this
      },
      token: generateToken(user._id),
    });
  } catch (error) {
    res
      .status(500)
      .json({ success: false, message: "Server Error: " + error.message });
  }
};

exports.loginUser = async (req, res) => {
  const { identifier, password } = req.body;

  try {
    if (!identifier || !password) {
      return res
        .status(400)
        .json({
          success: false,
          message: "Email/Username aur Password dono likhein!",
        });
    }

    const user = await User.findOne({
      $or: [{ email: identifier }, { username: identifier }],
    });

    if (user && (await user.matchPassword(password))) {
      if (user.status === "Deactivate") {
        return res
          .status(403)
          .json({
            success: false,
            message: "Aapka account block kar diya gaya hai.",
          });
      }
      user.lastLogin = Date.now();
      await user.save();

      res.json({
        success: true,
        user: {
          id: user._id,
          name: user.name,
          username: user.username,
          email: user.email,
          phone: user.phone,
          role: user.role,
          avatar: user.avatar,
          lastSeenNotificationId: user.lastSeenNotificationId, // 🔥 Added this
        },
        token: generateToken(user._id),
      });
    } else {
      res
        .status(401)
        .json({ success: false, message: "Galat Email/Username ya Password" });
    }
  } catch (error) {
    res
      .status(500)
      .json({ success: false, message: "Server Error: " + error.message });
  }
};
