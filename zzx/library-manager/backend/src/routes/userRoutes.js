const express = require("express");
const {
  getUsers,
  updateUser,
  deleteUser,
} = require("../controllers/userController");
const { protect, admin } = require("../middlewares/authMiddleware");
const router = express.Router();

router.get("/", protect, admin, getUsers);
router
  .route("/:id")
  .put(protect, updateUser)
  .delete(protect, admin, deleteUser);

module.exports = router;
