import { useState } from "react";
import { useLibrary } from "../context/LibraryContext";

export default function Members() {
  const { user, members = [], setMembers, addNotification } = useLibrary();
  const [search, setSearch] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [editingId, setEditingId] = useState(null);
  const [originalEmail, setOriginalEmail] = useState("");

  const [formData, setFormData] = useState({
    name: "",
    username: "",
    email: "",
    phone: "",
    password: "",
    avatar: "",
    status: "Active",
  });

  const isAdmin = user?.role === "admin";
  const formatDate = (dateString) => {
    if (!dateString) return "";
    const [year, month, day] = dateString.split("-");
    return `${day}-${month}-${year}`;
  };

  const handleOpenModal = (member = null) => {
    if (!isAdmin) {
      alert("Only Admins can perform this action.");
      return;
    }
    if (member && member.id) {
      setEditingId(member.id);
      setOriginalEmail(member.email);
      const usersList = JSON.parse(localStorage.getItem("lib_users")) || [];
      const matchedUser = usersList.find((u) => u.email === member.email);
      setFormData({
        name: member.name || "",
        username: matchedUser?.username || "",
        email: member.email || "",
        phone: member.phone || "",
        password: matchedUser?.password || "",
        avatar: member.avatar || "",
        status: member.status || "Active",
      });
    } else {
      setEditingId(null);
      setOriginalEmail("");
      setFormData({
        name: "",
        username: "",
        email: "",
        phone: "",
        password: "",
        avatar: "",
        status: "Active",
      });
    }
    setIsModalOpen(true);
  };

  const handleCloseModal = () => setIsModalOpen(false);

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
      };
      reader.readAsDataURL(file);
    }
  };

  // UPDATED: Capital to Small conversion & strictly allowed characters
  const handleUsernameChange = (e) => {
    const val = e.target.value.toLowerCase();
    if (/^[a-z0-9_-]*$/.test(val)) {
      setFormData((prev) => ({ ...prev, username: val }));
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (
      !formData.name ||
      !formData.username ||
      !formData.email ||
      !formData.phone ||
      !formData.password
    ) {
      alert("Please fill all required fields!");
      return;
    }
    const usersList = JSON.parse(localStorage.getItem("lib_users")) || [];

    if (editingId) {
      const exists = usersList.find(
        (u) =>
          (u.email === formData.email || u.username === formData.username) &&
          u.email !== originalEmail,
      );
      if (exists) {
        alert("Email or Username is already taken!");
        return;
      }
      const updatedMembers = members.map((m) =>
        m.id === editingId
          ? {
              ...m,
              name: formData.name,
              email: formData.email,
              phone: formData.phone,
              avatar: formData.avatar,
              status: formData.status,
            }
          : m,
      );
      setMembers(updatedMembers);
      const userIndex = usersList.findIndex((u) => u.email === originalEmail);
      if (userIndex !== -1) {
        usersList[userIndex] = {
          ...usersList[userIndex],
          name: formData.name,
          username: formData.username,
          email: formData.email,
          phone: formData.phone,
          password: formData.password,
          avatar: formData.avatar,
        };
        localStorage.setItem("lib_users", JSON.stringify(usersList));
      }
      addNotification(
        "Member Updated",
        `${formData.name}'s details were updated.`,
        "primary",
        "fa-user-pen",
        "admin@library.com",
      );
      alert("Updated successfully!");
    } else {
      const exists = usersList.find(
        (u) => u.email === formData.email || u.username === formData.username,
      );
      if (exists) {
        alert("Email or Username already registered!");
        return;
      }
      const timestamp = Date.now();
      const newMember = {
        id: "M" + timestamp,
        name: formData.name,
        email: formData.email,
        phone: formData.phone,
        avatar: formData.avatar,
        status: formData.status,
        joinDate: new Date().toISOString().split("T")[0],
      };
      setMembers([...members, newMember]);
      const newUser = {
        id: "U" + timestamp,
        name: formData.name,
        username: formData.username,
        email: formData.email,
        phone: formData.phone,
        password: formData.password,
        role: "student",
        avatar: formData.avatar,
      };
      usersList.push(newUser);
      localStorage.setItem("lib_users", JSON.stringify(usersList));
      addNotification(
        "New Member",
        `${formData.name} added by Admin.`,
        "success",
        "fa-user-plus",
        "admin@library.com",
      );
    }
    handleCloseModal();
  };

  const handleDelete = (id, email) => {
    if (!isAdmin) return;
    if (window.confirm("Delete this member AND their account?")) {
      setMembers(members.filter((m) => m.id !== id));
      const usersList = JSON.parse(localStorage.getItem("lib_users")) || [];
      localStorage.setItem(
        "lib_users",
        JSON.stringify(usersList.filter((u) => u.email !== email)),
      );
      addNotification(
        "Member Removed",
        `Account removed.`,
        "danger",
        "fa-user-minus",
        "admin@library.com",
      );
    }
  };

  const filteredMembers = members.filter(
    (m) =>
      (m.name || "").toLowerCase().includes(search.toLowerCase()) ||
      (m.email || "").toLowerCase().includes(search.toLowerCase()),
  );

  return (
    <div className="animate-fade-in relative">
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4 mb-8">
        <div>
          <h1 className="text-3xl font-bold text-text-dark">
            Members Directory
          </h1>
          <p className="text-text-muted mt-1">
            Manage library members and their access accounts.
          </p>
        </div>
        {isAdmin && (
          <button
            onClick={() => handleOpenModal()}
            className="bg-primary text-white px-6 py-2.5 rounded-lg font-medium hover:bg-[#5b54e0] shadow-md transition-all flex items-center gap-2 cursor-pointer"
          >
            <i className="fa-solid fa-user-plus"></i> Add New Member
          </button>
        )}
      </div>

      <div className="bg-bg-white p-5 rounded-2xl shadow-sm border border-border-color mb-8">
        <div className="relative w-full max-w-md">
          <i className="fa-solid fa-search absolute left-4 top-1/2 -translate-y-1/2 text-text-muted"></i>
          <input
            type="text"
            placeholder="Search by name, email, or phone..."
            className="w-full pl-11 pr-4 py-2.5 rounded-lg border border-border-color bg-bg-light outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark"
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
        </div>
      </div>

      <div className="bg-bg-white rounded-2xl shadow-sm border border-border-color overflow-hidden">
        <div className="overflow-x-auto">
          <table className="w-full text-left border-collapse">
            <thead>
              <tr className="bg-bg-light/50 text-primary border-b border-border-color text-sm uppercase tracking-wider">
                <th className="p-4 font-semibold">Member</th>
                <th className="p-4 font-semibold">Contact</th>
                <th className="p-4 font-semibold">Joined Date</th>
                <th className="p-4 font-semibold">Status</th>
                {isAdmin && <th className="p-4 font-semibold">Actions</th>}
              </tr>
            </thead>
            <tbody>
              {filteredMembers.length === 0 ? (
                <tr>
                  <td
                    colSpan={isAdmin ? "5" : "4"}
                    className="p-8 text-center text-text-muted font-medium"
                  >
                    No members found.
                  </td>
                </tr>
              ) : (
                filteredMembers.map((member) => (
                  <tr
                    key={member.id}
                    className="border-b border-border-color hover:bg-black/5 transition-colors"
                  >
                    <td className="p-4 flex items-center gap-4">
                      <div className="w-12 h-12 rounded-full border border-border-color bg-bg-light overflow-hidden flex-shrink-0 shadow-inner">
                        <img
                          src={
                            member.avatar ||
                            `https://ui-avatars.com/api/?name=${encodeURIComponent(member.name)}&background=random`
                          }
                          alt={member.name}
                          className="w-full h-full object-cover"
                        />
                      </div>
                      <span className="font-medium text-text-dark text-base">
                        {member.name}
                      </span>
                    </td>
                    <td className="p-4">
                      <div className="text-sm font-medium text-text-dark">
                        {member.email}
                      </div>
                      <div className="text-xs text-text-muted mt-1">
                        {member.phone}
                      </div>
                    </td>
                    <td className="p-4 text-sm text-text-dark font-medium">
                      {formatDate(member.joinDate)}
                    </td>
                    <td className="p-4">
                      <span
                        className={`px-3 py-1 rounded-full text-xs font-bold uppercase tracking-wider ${member.status === "Active" ? "bg-success/20 text-success" : member.status === "Inactive" ? "bg-warning/20 text-warning" : "bg-danger/20 text-danger"}`}
                      >
                        {member.status || "Active"}
                      </span>
                    </td>
                    {isAdmin && (
                      <td className="p-4">
                        <div className="flex gap-2">
                          <button
                            onClick={() => handleOpenModal(member)}
                            className="w-8 h-8 rounded-lg flex items-center justify-center text-text-muted hover:bg-primary/10 hover:text-primary transition-all cursor-pointer"
                            title="Edit User"
                          >
                            <i className="fa-solid fa-edit"></i>
                          </button>
                          <button
                            onClick={() =>
                              handleDelete(member.id, member.email)
                            }
                            className="w-8 h-8 rounded-lg flex items-center justify-center text-text-muted hover:bg-danger/10 hover:text-danger transition-all cursor-pointer"
                            title="Delete User"
                          >
                            <i className="fa-solid fa-trash"></i>
                          </button>
                        </div>
                      </td>
                    )}
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </div>

      {isModalOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/60 backdrop-blur-sm p-4 animate-fade-in">
          <div className="bg-bg-white w-full max-w-xl p-8 rounded-2xl shadow-xl border border-border-color max-h-[90vh] overflow-y-auto">
            <h2 className="text-2xl font-bold text-text-dark mb-6">
              {editingId ? "Edit Member Account" : "Create Member Account"}
            </h2>
            <form onSubmit={handleSubmit} className="space-y-5">
              <div>
                <label className="block text-sm font-medium text-text-dark mb-2">
                  Profile Picture
                </label>
                <div className="flex items-center gap-4 bg-bg-light p-3 rounded-lg border border-border-color">
                  <div className="w-16 h-16 rounded-full border-2 border-primary/30 overflow-hidden bg-bg-white flex items-center justify-center flex-shrink-0 shadow-inner">
                    {formData.avatar ? (
                      <img
                        src={formData.avatar}
                        alt="Upload Preview"
                        className="w-full h-full object-cover"
                      />
                    ) : (
                      <i className="fa-solid fa-user text-text-muted text-2xl"></i>
                    )}
                  </div>
                  <div className="flex-1">
                    <input
                      type="file"
                      accept="image/*"
                      onChange={handleFileChange}
                      className="block w-full text-xs text-text-muted file:mr-3 file:py-1.5 file:px-3 file:rounded-md file:border-0 file:text-xs file:font-semibold file:bg-primary/10 file:text-primary hover:file:bg-primary/20 file:cursor-pointer transition-all"
                    />
                  </div>
                </div>
              </div>

              <div className="grid grid-cols-1 sm:grid-cols-2 gap-5">
                <div>
                  <label className="block text-sm font-medium text-text-dark mb-1.5">
                    Full Name
                  </label>
                  <input
                    type="text"
                    className="w-full p-3 rounded-lg border border-border-color bg-bg-light outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark"
                    value={formData.name}
                    onChange={(e) =>
                      setFormData((p) => ({ ...p, name: e.target.value }))
                    }
                    required
                  />
                </div>

                {/* UPDATED: Added handleUsernameChange here */}
                <div>
                  <label className="block text-sm font-medium text-text-dark mb-1.5">
                    Username
                  </label>
                  <input
                    type="text"
                    className="w-full p-3 rounded-lg border border-border-color bg-bg-light outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark"
                    value={formData.username}
                    onChange={handleUsernameChange}
                    required
                  />
                </div>

                <div>
                  <label className="block text-sm font-medium text-text-dark mb-1.5">
                    Email Address
                  </label>
                  <input
                    type="email"
                    className="w-full p-3 rounded-lg border border-border-color bg-bg-light outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark"
                    value={formData.email}
                    onChange={(e) =>
                      setFormData((p) => ({ ...p, email: e.target.value }))
                    }
                    required
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-text-dark mb-1.5">
                    Mobile Number
                  </label>
                  <input
                    type="tel"
                    maxLength="10"
                    className="w-full p-3 rounded-lg border border-border-color bg-bg-light outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark"
                    value={formData.phone}
                    onChange={(e) =>
                      setFormData((p) => ({ ...p, phone: e.target.value }))
                    }
                    required
                  />
                </div>
              </div>
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-5">
                <div>
                  <label className="block text-sm font-medium text-text-dark mb-1.5">
                    Account Status
                  </label>
                  <select
                    className="w-full p-3 rounded-lg border border-border-color bg-bg-light outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark cursor-pointer"
                    value={formData.status}
                    onChange={(e) =>
                      setFormData((p) => ({ ...p, status: e.target.value }))
                    }
                  >
                    <option value="Active">Active</option>
                    <option value="Inactive">Inactive</option>
                    <option value="Deactivate">Deactivate</option>
                  </select>
                </div>
                <div>
                  <label className="block text-sm font-medium text-text-dark mb-1.5">
                    Password
                  </label>
                  <input
                    type="password"
                    placeholder="Text, number and @ only"
                    className="w-full p-3 rounded-lg border border-border-color bg-bg-light outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark"
                    value={formData.password}
                    onChange={(e) =>
                      setFormData((p) => ({ ...p, password: e.target.value }))
                    }
                    required
                  />
                </div>
              </div>

              <div className="flex justify-end gap-3 mt-8 pt-6 border-t border-border-color">
                <button
                  type="button"
                  onClick={handleCloseModal}
                  className="px-5 py-2.5 rounded-lg font-medium text-text-muted hover:bg-bg-light border border-border-color transition-all cursor-pointer"
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="px-6 py-2.5 rounded-lg font-medium bg-primary text-white hover:bg-[#5b54e0] shadow-md transition-all flex items-center gap-2 cursor-pointer"
                >
                  {editingId ? "Update Account" : "Save & Create"}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}
