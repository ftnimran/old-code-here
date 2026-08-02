import { useState } from "react";
import { useLibrary } from "../context/LibraryContext";

export default function Members() {
  const { members = [], setMembers, addNotification } = useLibrary();
  const [search, setSearch] = useState("");

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [editingId, setEditingId] = useState(null);
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    phone: "",
    avatar: "",
  });

  const formatDate = (dateString) => {
    if (!dateString) return "";
    const [year, month, day] = dateString.split("-");
    return `${day}-${month}-${year}`;
  };

  const handleOpenModal = (member = null) => {
    if (member && member.id) {
      setEditingId(member.id);
      setFormData({
        name: member.name || "",
        email: member.email || "",
        phone: member.phone || "",
        avatar: member.avatar || "",
      });
    } else {
      setEditingId(null);
      setFormData({ name: "", email: "", phone: "", avatar: "" });
    }
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setEditingId(null);
    setFormData({ name: "", email: "", phone: "", avatar: "" });
  };

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
      };
      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!formData.name || !formData.email || !formData.phone) {
      alert("Please fill all required fields");
      return;
    }

    if (editingId) {
      const updatedMembers = members.map((m) =>
        m.id === editingId ? { ...m, ...formData } : m,
      );
      setMembers(updatedMembers);

      addNotification(
        "Member Updated",
        `${formData.name}'s details were updated.`,
        "primary",
        "fa-user-pen",
      );
      alert("Member updated successfully!");
    } else {
      const newMember = {
        id: "M" + Date.now(),
        name: formData.name,
        email: formData.email,
        phone: formData.phone,
        avatar: formData.avatar,
        joinDate: new Date().toISOString().split("T")[0],
      };
      setMembers([...members, newMember]);

      // --- NEW MEMBER REGISTERED EXACT MATCH TITLE ---
      addNotification(
        "New Member Registered",
        `${formData.name} joined the library.`,
        "success",
        "fa-user-plus",
      );

      alert("New member added successfully!");
    }
    handleCloseModal();
  };

  const handleDelete = (id) => {
    const memberToDelete = members.find((m) => m.id === id);
    if (window.confirm("Are you sure you want to delete this member?")) {
      setMembers(members.filter((m) => m.id !== id));
      addNotification(
        "Member Removed",
        `${memberToDelete?.name} was removed from the directory.`,
        "danger",
        "fa-user-minus",
      );
    }
  };

  const filteredMembers = members.filter(
    (m) =>
      (m.name || "").toLowerCase().includes(search.toLowerCase()) ||
      (m.email || "").toLowerCase().includes(search.toLowerCase()) ||
      (m.phone || "").includes(search),
  );

  return (
    <div className="animate-fade-in relative">
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4 mb-8">
        <div>
          <h1 className="text-3xl font-bold text-text-dark">
            Members Directory
          </h1>
          <p className="text-text-muted mt-1">
            Manage library members and students.
          </p>
        </div>
        <button
          onClick={() => handleOpenModal()}
          className="bg-primary text-white px-5 py-2.5 rounded-lg font-medium hover:bg-[#5b54e0] shadow-md transition-all flex items-center gap-2 cursor-pointer"
        >
          <i className="fa-solid fa-user-plus"></i> Add New Member
        </button>
      </div>

      <div className="bg-bg-white p-5 rounded-xl shadow-sm border border-border-color mb-8">
        <div className="relative w-full max-w-md">
          <i className="fa-solid fa-search absolute left-4 top-1/2 -translate-y-1/2 text-text-muted"></i>
          <input
            type="text"
            placeholder="Search by name, email, or phone..."
            className="w-full pl-11 pr-4 py-2.5 rounded-lg border border-border-color bg-bg-light focus:outline-none focus:border-primary text-text-dark"
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
        </div>
      </div>

      <div className="bg-bg-white rounded-2xl shadow-sm border border-border-color overflow-hidden">
        <div className="overflow-x-auto">
          <table className="w-full text-left border-collapse">
            <thead>
              <tr className="bg-primary/5 text-primary border-b border-border-color text-sm uppercase tracking-wider">
                <th className="p-4 font-semibold">Member</th>
                <th className="p-4 font-semibold">Contact</th>
                <th className="p-4 font-semibold">Joined Date</th>
                <th className="p-4 font-semibold">Status</th>
                <th className="p-4 font-semibold">Actions</th>
              </tr>
            </thead>
            <tbody>
              {filteredMembers.length === 0 ? (
                <tr>
                  <td colSpan="5" className="p-8 text-center text-text-muted">
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
                      <div className="w-12 h-12 rounded-full border-2 border-border-color overflow-hidden flex-shrink-0 bg-gray-100">
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
                    <td className="p-4 text-text-dark text-sm">
                      {formatDate(member.joinDate)}
                    </td>
                    <td className="p-4">
                      <span className="bg-success/20 text-success px-3 py-1 rounded-full text-xs font-bold">
                        Active
                      </span>
                    </td>
                    <td className="p-4">
                      <div className="flex gap-2">
                        <button
                          onClick={() => handleOpenModal(member)}
                          className="w-8 h-8 rounded-lg flex items-center justify-center text-text-muted hover:text-primary hover:bg-primary/10 transition-colors cursor-pointer"
                        >
                          <i className="fa-solid fa-edit"></i>
                        </button>
                        <button
                          onClick={() => handleDelete(member.id)}
                          className="w-8 h-8 rounded-lg flex items-center justify-center text-text-muted hover:text-danger hover:bg-danger/10 transition-colors cursor-pointer"
                        >
                          <i className="fa-solid fa-trash"></i>
                        </button>
                      </div>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </div>

      {isModalOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/60 backdrop-blur-sm">
          <div className="bg-bg-white w-full max-w-md p-8 rounded-2xl shadow-xl border border-border-color m-4 animate-fade-in max-h-[90vh] overflow-y-auto">
            <h2 className="text-2xl font-bold text-text-dark mb-6">
              {editingId ? "Edit Member Details" : "Add New Member"}
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
                      className="block w-full text-xs text-text-muted file:mr-3 file:py-1.5 file:px-3 file:rounded-md file:border-0 file:text-xs file:font-semibold file:bg-primary/10 file:text-primary hover:file:bg-primary/20 file:cursor-pointer"
                    />
                  </div>
                </div>
              </div>
              <div>
                <label className="block text-sm font-medium text-text-dark mb-1.5">
                  Full Name
                </label>
                <input
                  type="text"
                  id="name"
                  className="w-full p-3 rounded-lg border border-border-color bg-bg-light focus:outline-none focus:border-primary text-text-dark"
                  value={formData.name}
                  onChange={handleInputChange}
                  required
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-text-dark mb-1.5">
                  Email Address
                </label>
                <input
                  type="email"
                  id="email"
                  className="w-full p-3 rounded-lg border border-border-color bg-bg-light focus:outline-none focus:border-primary text-text-dark"
                  value={formData.email}
                  onChange={handleInputChange}
                  required
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-text-dark mb-1.5">
                  Phone Number
                </label>
                <input
                  type="tel"
                  id="phone"
                  className="w-full p-3 rounded-lg border border-border-color bg-bg-light focus:outline-none focus:border-primary text-text-dark"
                  value={formData.phone}
                  onChange={handleInputChange}
                  required
                />
              </div>
              <div className="flex justify-end gap-3 mt-8 pt-6 border-t border-border-color">
                <button
                  type="button"
                  onClick={handleCloseModal}
                  className="px-5 py-2.5 rounded-lg font-medium text-text-muted hover:bg-bg-light border border-border-color cursor-pointer"
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="px-6 py-2.5 rounded-lg font-medium bg-primary text-white hover:bg-[#5b54e0] shadow-md cursor-pointer"
                >
                  {editingId ? "Update" : "Save Member"}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}
