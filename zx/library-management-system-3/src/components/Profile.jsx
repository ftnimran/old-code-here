import { useState, useEffect, useRef } from "react";
import { useLibrary } from "../context/LibraryContext";

export default function Profile() {
  const { user, setUser, members, setMembers } = useLibrary();

  const [formData, setFormData] = useState({
    name: "",
    username: "",
    email: "",
    phone: "",
    password: "",
    avatar: "",
  });
  const [editMode, setEditMode] = useState({
    name: false,
    username: false,
    password: false,
    avatar: false,
  });
  const nameRef = useRef(null);
  const usernameRef = useRef(null);
  const passwordRef = useRef(null);

  const currentMember = members.find((m) => m.email === user?.email);
  const memberStatus = currentMember?.status || "Active";

  useEffect(() => {
    if (user)
      setFormData({
        name: user.name || "",
        username: user.username || "",
        email: user.email || "",
        phone: user.phone || "",
        password: "",
        avatar: user.avatar || "",
      });
  }, [user]);

  // UPDATED: Centralized Input handler with specific username logic
  const handleInputChange = (e) => {
    const { id, value } = e.target;

    if (id === "username") {
      const val = value.toLowerCase();
      if (/^[a-z0-9_-]*$/.test(val)) {
        setFormData((prev) => ({ ...prev, username: val }));
      }
    } else {
      setFormData((prev) => ({ ...prev, [id]: value }));
    }
  };

  const enableEdit = (field, inputRef) => {
    setEditMode((prev) => ({ ...prev, [field]: true }));
    setTimeout(() => inputRef.current?.focus(), 10);
  };

  // Profile Picture Upload Handler
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
        setEditMode((prev) => ({ ...prev, avatar: true }));
      };
      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!formData.name || !formData.username) {
      alert("Name and Username required!");
      return;
    }

    const updatedUser = {
      ...user,
      name: formData.name,
      username: formData.username,
      avatar: formData.avatar,
    };
    if (formData.password) updatedUser.password = formData.password;

    const usersList = JSON.parse(localStorage.getItem("lib_users")) || [];
    const userIndex = usersList.findIndex((u) => u.id === user.id);
    if (userIndex !== -1) usersList[userIndex] = updatedUser;
    else usersList.push(updatedUser);
    localStorage.setItem("lib_users", JSON.stringify(usersList));

    if (currentMember) {
      const updatedMembers = members.map((m) =>
        m.id === currentMember.id
          ? { ...m, name: formData.name, avatar: formData.avatar }
          : m,
      );
      setMembers(updatedMembers);
    }
    setUser(updatedUser);
    alert("Profile saved!");
    setFormData((prev) => ({ ...prev, password: "" }));
    setEditMode({
      name: false,
      username: false,
      password: false,
      avatar: false,
    });
  };

  const isEditingAny =
    editMode.name || editMode.username || editMode.password || editMode.avatar;

  return (
    <div className="animate-fade-in max-w-5xl mx-auto">
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-text-dark">User Profile</h1>
        <p className="text-text-muted mt-1">
          Manage your account settings and preferences.
        </p>
      </div>

      <div className="flex flex-col md:flex-row gap-8">
        {/* Left Side: Display */}
        <div className="flex-1 bg-bg-white p-6 sm:p-8 rounded-2xl shadow-sm border border-border-color text-center h-fit">
          <div className="relative w-32 h-32 mx-auto mb-5 group">
            <div className="w-full h-full rounded-full border-4 border-primary overflow-hidden bg-bg-light shadow-inner flex items-center justify-center">
              <img
                src={
                  formData.avatar ||
                  `https://ui-avatars.com/api/?name=${encodeURIComponent(formData.name)}&size=120`
                }
                alt="Profile"
                className="w-full h-full object-cover transition-transform duration-300 group-hover:scale-105"
              />
            </div>

            {/* Camera Icon for Image Upload */}
            <label className="absolute bottom-0 right-0 bg-primary text-white w-10 h-10 rounded-full flex items-center justify-center cursor-pointer shadow-md hover:bg-[#5b54e0] transition-colors border-2 border-bg-white">
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
            {formData.name || "Library User"}
          </h2>
          <p className="text-text-muted uppercase text-sm font-bold tracking-wider mt-1 mb-6">
            {user?.role || "STUDENT"}
          </p>

          <div className="mt-8 pt-6 border-t border-border-color flex justify-around">
            <div>
              <h4 className="text-text-dark font-bold text-xl mb-1 uppercase tracking-wide">
                Username
              </h4>
              <p className="text-primary text-sm font-bold tracking-wider">
                @{formData.username || "username"}
              </p>
            </div>
            <div>
              <h4 className="text-text-dark font-bold text-xl mb-1 uppercase tracking-wide">
                Status
              </h4>
              <p
                className={`text-sm font-bold tracking-wider ${memberStatus === "Active" ? "text-success" : memberStatus === "Deactivate" ? "text-danger" : "text-warning"}`}
              >
                {memberStatus}
              </p>
            </div>
          </div>
        </div>

        {/* Right Side: Edit Form Fields */}
        <div className="flex-[2] bg-bg-white p-6 sm:p-8 rounded-2xl shadow-sm border border-border-color">
          <h3 className="text-xl font-bold text-text-dark mb-6">
            Profile Details
          </h3>
          <form onSubmit={handleSubmit} className="space-y-5">
            <div>
              <label className="text-sm font-medium text-text-dark block mb-1.5">
                Full Name
              </label>
              <div className="relative">
                <input
                  ref={nameRef}
                  type="text"
                  id="name"
                  className={`w-full p-3 pr-10 rounded-lg border border-border-color outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all font-medium ${!editMode.name ? "bg-bg-light/50 text-text-muted cursor-not-allowed" : "bg-bg-light text-text-dark"}`}
                  value={formData.name}
                  onChange={handleInputChange}
                  disabled={!editMode.name}
                />
                {!editMode.name && (
                  <button
                    type="button"
                    onClick={() => enableEdit("name", nameRef)}
                    className="absolute right-3 top-1/2 -translate-y-1/2 text-text-muted hover:text-primary transition-colors cursor-pointer"
                  >
                    <i className="fa-solid fa-pen"></i>
                  </button>
                )}
              </div>
            </div>

            <div>
              <label className="text-sm font-medium text-text-dark block mb-1.5">
                Username
              </label>
              <div className="relative">
                <input
                  ref={usernameRef}
                  type="text"
                  id="username"
                  className={`w-full p-3 pr-10 rounded-lg border border-border-color outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all font-medium ${!editMode.username ? "bg-bg-light/50 text-text-muted cursor-not-allowed" : "bg-bg-light text-text-dark"}`}
                  value={formData.username}
                  onChange={handleInputChange}
                  disabled={!editMode.username}
                />
                {!editMode.username && (
                  <button
                    type="button"
                    onClick={() => enableEdit("username", usernameRef)}
                    className="absolute right-3 top-1/2 -translate-y-1/2 text-text-muted hover:text-primary transition-colors cursor-pointer"
                  >
                    <i className="fa-solid fa-pen"></i>
                  </button>
                )}
              </div>
            </div>

            <div>
              <label className="block text-sm font-medium text-text-dark mb-1.5">
                Email Address
              </label>
              <input
                type="email"
                value={formData.email}
                className="w-full p-3 rounded-lg border border-border-color bg-bg-light text-text-muted cursor-not-allowed opacity-60 font-medium select-none"
                disabled
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-text-dark mb-1.5">
                Mobile Number
              </label>
              <input
                type="tel"
                value={formData.phone || "N/A"}
                className="w-full p-3 rounded-lg border border-border-color bg-bg-light text-text-muted cursor-not-allowed opacity-60 font-medium select-none"
                disabled
              />
            </div>

            <div>
              <label className="text-sm font-medium text-text-dark block mb-1.5">
                Change Password (Optional)
              </label>
              <div className="relative">
                <input
                  ref={passwordRef}
                  type="password"
                  id="password"
                  placeholder={!editMode.password ? "••••••••" : "Enter new"}
                  className={`w-full p-3 pr-10 rounded-lg border border-border-color outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all font-medium ${!editMode.password ? "bg-bg-light/50 text-text-muted cursor-not-allowed tracking-widest" : "bg-bg-light text-text-dark"}`}
                  value={formData.password}
                  onChange={handleInputChange}
                  disabled={!editMode.password}
                />
                {!editMode.password && (
                  <button
                    type="button"
                    onClick={() => enableEdit("password", passwordRef)}
                    className="absolute right-3 top-1/2 -translate-y-1/2 text-text-muted hover:text-primary transition-colors cursor-pointer"
                  >
                    <i className="fa-solid fa-pen"></i>
                  </button>
                )}
              </div>
            </div>

            <div className="pt-4 flex justify-end gap-3 h-[68px]">
              <button
                type="submit"
                className={`bg-primary text-white px-8 py-3 rounded-lg font-medium hover:bg-[#5b54e0] shadow-md transition-all duration-300 w-full sm:w-auto cursor-pointer ${isEditingAny ? "opacity-100 visible" : "opacity-0 invisible pointer-events-none"}`}
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
