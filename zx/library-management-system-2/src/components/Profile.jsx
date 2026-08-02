import { useState, useEffect, useRef } from "react";
import { useLibrary } from "../context/LibraryContext";

export default function Profile() {
  const { user, setUser } = useLibrary();

  // Form Data State
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    avatar: "",
  });

  // Edit Mode State (Fields disabled by default)
  // 'avatar' track karega ki naya image upload hua hai ya nahi
  const [editMode, setEditMode] = useState({
    name: false,
    email: false,
    password: false,
    avatar: false,
  });

  // Refs for auto-focusing
  const nameRef = useRef(null);
  const emailRef = useRef(null);
  const passwordRef = useRef(null);

  useEffect(() => {
    if (user) {
      setFormData({
        name: user.name || "",
        email: user.email || "",
        password: "",
        avatar: user.avatar || "",
      });
    }
  }, [user]);

  const handleInputChange = (e) => {
    const { id, value } = e.target;
    setFormData((prev) => ({ ...prev, [id]: value }));
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      if (file.size > 2 * 1024 * 1024) {
        alert("Image size should be less than 2MB");
        return;
      }
      const reader = new FileReader();
      reader.onloadend = () => {
        setFormData((prev) => ({ ...prev, avatar: reader.result }));
        // Image change hone par edit mode on kar do taaki Save button dikhe
        setEditMode((prev) => ({ ...prev, avatar: true }));
      };
      reader.readAsDataURL(file);
    }
  };

  // Function to enable edit mode and focus the input
  const enableEdit = (field, inputRef) => {
    setEditMode((prev) => ({ ...prev, [field]: true }));
    setTimeout(() => {
      if (inputRef.current) inputRef.current.focus();
    }, 10);
  };

  // Function to cancel edits and revert to original data
  const handleCancel = () => {
    setFormData({
      name: user.name || "",
      email: user.email || "",
      password: "",
      avatar: user.avatar || "",
    });
    setEditMode({ name: false, email: false, password: false, avatar: false });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!formData.name || !formData.email) {
      alert("Name and Email are required!");
      return;
    }

    const updatedUser = {
      ...user,
      name: formData.name,
      email: formData.email,
      avatar: formData.avatar,
    };

    if (formData.password) {
      updatedUser.password = formData.password;
    }

    const usersList = JSON.parse(localStorage.getItem("lib_users")) || [];
    const userIndex = usersList.findIndex((u) => u.id === user.id);

    if (userIndex !== -1) {
      usersList[userIndex] = updatedUser;
    } else {
      usersList.push(updatedUser);
    }
    localStorage.setItem("lib_users", JSON.stringify(usersList));

    setUser(updatedUser);

    alert("Profile changes saved permanently!");

    // Save hone ke baad fields lock kar dena aur Save button hide kar dena
    setFormData((prev) => ({ ...prev, password: "" }));
    setEditMode({ name: false, email: false, password: false, avatar: false });
  };

  // Check if any field is currently being edited
  const isEditingAny =
    editMode.name || editMode.email || editMode.password || editMode.avatar;

  return (
    <div className="animate-fade-in max-w-5xl mx-auto">
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-text-dark">User Profile</h1>
        <p className="text-text-muted mt-1">
          Manage your account settings and preferences.
        </p>
      </div>

      <div className="flex flex-col md:flex-row gap-8">
        {/* Left Side: Profile Preview Card */}
        <div className="flex-1 bg-bg-white p-8 rounded-2xl shadow-sm border border-border-color text-center h-fit relative">
          <div className="relative w-32 h-32 mx-auto mb-5 group">
            <div className="w-full h-full rounded-full border-4 border-primary overflow-hidden bg-bg-light shadow-inner flex items-center justify-center">
              <img
                src={
                  formData.avatar ||
                  `https://ui-avatars.com/api/?name=${encodeURIComponent(formData.name || "Admin")}&size=120&background=6C63FF&color=fff`
                }
                alt="Profile View"
                className="w-full h-full object-cover transition-transform duration-300 group-hover:scale-105"
              />
            </div>

            <label
              className="absolute bottom-0 right-0 bg-primary text-white w-10 h-10 rounded-full flex items-center justify-center cursor-pointer shadow-md hover:bg-[#5b54e0] transition-colors border-2 border-bg-white"
              title="Upload Photo from Computer"
            >
              <i className="fa-solid fa-camera"></i>
              <input
                type="file"
                accept="image/*"
                className="hidden"
                onChange={handleFileChange}
              />
            </label>
          </div>

          <h2 className="text-2xl font-bold text-text-dark">
            {formData.name || "Admin User"}
          </h2>
          <p className="text-text-muted uppercase text-sm font-semibold tracking-wider mt-1 mb-6">
            {user?.role || "ADMIN"}
          </p>

          <div className="mt-8 pt-6 border-t border-border-color flex justify-around">
            <div>
              <h4 className="text-primary font-bold text-xl">ACTIVE</h4>
              <p className="text-text-muted text-xs uppercase tracking-wider mt-1">
                Status
              </p>
            </div>
            <div>
              <h4 className="text-primary font-bold text-xl">2025</h4>
              <p className="text-text-muted text-xs uppercase tracking-wider mt-1">
                Member Since
              </p>
            </div>
          </div>
        </div>

        {/* Right Side: Edit Form Fields */}
        <div className="flex-[2] bg-bg-white p-8 rounded-2xl shadow-sm border border-border-color">
          <h3 className="text-xl font-bold text-text-dark mb-6">
            Profile Details
          </h3>

          <form onSubmit={handleSubmit} className="space-y-5">
            {/* Full Name Input */}
            <div>
              <div className="flex items-center justify-between mb-1.5">
                <label
                  htmlFor="name"
                  className="text-sm font-medium text-text-dark"
                >
                  Full Name
                </label>
              </div>
              <div className="relative">
                <input
                  ref={nameRef}
                  type="text"
                  id="name"
                  className={`w-full p-3 pr-10 border border-border-color rounded-lg focus:outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all font-medium ${!editMode.name ? "bg-bg-light/50 text-text-muted cursor-not-allowed" : "bg-bg-white text-text-dark"}`}
                  value={formData.name}
                  onChange={handleInputChange}
                  disabled={!editMode.name}
                  required
                />
                {!editMode.name && (
                  <button
                    type="button"
                    onClick={() => enableEdit("name", nameRef)}
                    className="absolute right-3 top-1/2 -translate-y-1/2 text-text-muted hover:text-primary transition-colors cursor-pointer"
                    title="Edit Name"
                  >
                    <i className="fa-solid fa-pen"></i>
                  </button>
                )}
              </div>
            </div>

            {/* Email Address Input */}
            <div>
              <div className="flex items-center justify-between mb-1.5">
                <label
                  htmlFor="email"
                  className="text-sm font-medium text-text-dark"
                >
                  Email Address
                </label>
              </div>
              <div className="relative">
                <input
                  ref={emailRef}
                  type="email"
                  id="email"
                  className={`w-full p-3 pr-10 border border-border-color rounded-lg focus:outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all font-medium ${!editMode.email ? "bg-bg-light/50 text-text-muted cursor-not-allowed" : "bg-bg-white text-text-dark"}`}
                  value={formData.email}
                  onChange={handleInputChange}
                  disabled={!editMode.email}
                  required
                />
                {!editMode.email && (
                  <button
                    type="button"
                    onClick={() => enableEdit("email", emailRef)}
                    className="absolute right-3 top-1/2 -translate-y-1/2 text-text-muted hover:text-primary transition-colors cursor-pointer"
                    title="Edit Email"
                  >
                    <i className="fa-solid fa-pen"></i>
                  </button>
                )}
              </div>
            </div>

            {/* Role (Always Disabled) */}
            <div>
              <label className="block text-sm font-medium text-text-dark mb-1.5">
                System Account Role
              </label>
              <input
                type="text"
                value={(user?.role || "Admin").toUpperCase()}
                className="w-full p-3 border border-border-color rounded-lg bg-bg-light text-text-muted cursor-not-allowed opacity-60 font-semibold select-none"
                disabled
              />
            </div>

            {/* Change Password Input */}
            <div>
              <div className="flex items-center justify-between mb-1.5">
                <label
                  htmlFor="password"
                  className="text-sm font-medium text-text-dark"
                >
                  Change Password (Optional)
                </label>
              </div>
              <div className="relative">
                <input
                  ref={passwordRef}
                  type="password"
                  id="password"
                  placeholder={
                    !editMode.password ? "••••••••" : "Enter new password"
                  }
                  className={`w-full p-3 pr-10 border border-border-color rounded-lg focus:outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all ${!editMode.password ? "bg-bg-light/50 text-text-muted cursor-not-allowed tracking-widest" : "bg-bg-white text-text-dark"}`}
                  value={formData.password}
                  onChange={handleInputChange}
                  disabled={!editMode.password}
                />
                {!editMode.password && (
                  <button
                    type="button"
                    onClick={() => enableEdit("password", passwordRef)}
                    className="absolute right-3 top-1/2 -translate-y-1/2 text-text-muted hover:text-primary transition-colors cursor-pointer"
                    title="Edit Password"
                  >
                    <i className="fa-solid fa-pen"></i>
                  </button>
                )}
              </div>
            </div>

            {/* Save & Cancel Buttons Container (Fixed height to prevent layout shift) */}
            <div className="pt-4 flex justify-end gap-3 h-[68px]">
              <button
                type="button"
                onClick={handleCancel}
                className={`px-6 py-3 rounded-lg font-medium text-text-muted hover:bg-bg-light border border-border-color transition-all duration-300 cursor-pointer ${isEditingAny ? "opacity-100 visible" : "opacity-0 invisible"}`}
              >
                Cancel
              </button>

              <button
                type="submit"
                className={`bg-primary text-white px-8 py-3 rounded-lg font-medium hover:bg-[#5b54e0] shadow-md hover:shadow-lg transition-all duration-300 w-full sm:w-auto cursor-pointer ${isEditingAny ? "opacity-100 visible" : "opacity-0 invisible pointer-events-none"}`}
              >
                Save Changes
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
