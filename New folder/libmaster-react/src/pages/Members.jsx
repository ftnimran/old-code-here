import React, { useState } from "react";

export default function Members({ members, updateMembers }) {
  const [searchVal, setSearchVal] = useState("");
  const [showModal, setShowModal] = useState(false);
  const [editId, setEditId] = useState(null);

  // Form Hooks State
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");

  const openModal = (m = null) => {
    if (m) {
      setEditId(m.id);
      setName(m.name);
      setEmail(m.email);
      setPhone(m.phone);
    } else {
      setEditId(null);
      setName("");
      setEmail("");
      setPhone("");
    }
    setShowModal(true);
  };

  const handleFormSubmit = (e) => {
    e.preventDefault();
    if (editId) {
      const amended = members.map((m) =>
        m.id === editId ? { ...m, name, email, phone } : m,
      );
      updateMembers(amended);
    } else {
      const added = {
        id: "M" + Date.now(),
        name,
        email,
        phone,
        joinDate: new Date().toISOString().split("T")[0],
      };
      updateMembers([...members, added]);
    }
    setShowModal(false);
  };

  const handleDelete = (id) => {
    if (
      window.confirm(
        "Delete this user account membership credentials profile record mapping?",
      )
    ) {
      updateMembers(members.filter((m) => m.id !== id));
    }
  };

  const searchedItems = members.filter(
    (m) =>
      m.name.toLowerCase().includes(searchVal.toLowerCase()) ||
      m.email.toLowerCase().includes(searchVal.toLowerCase()),
  );

  return (
    <div>
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          flexWrap: "wrap",
          gap: "1rem",
          marginBottom: "2rem",
        }}
      >
        <div>
          <h1>Membership Management</h1>
          <p style={{ color: "var(--text-muted)" }}>
            Configure credentials parameters logs profiles mapping metrics.
          </p>
        </div>
        <button className="btn btn-primary" onClick={() => openModal()}>
          <i className="fas fa-user-plus"></i> Register Member
        </button>
      </div>

      <div className="card" style={{ marginBottom: "1.5rem", padding: "1rem" }}>
        <input
          type="text"
          className="form-control"
          placeholder="Search profile tags name parameters..."
          value={searchVal}
          onChange={(e) => setSearchVal(e.target.value)}
          style={{ maxWidth: "400px" }}
        />
      </div>

      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(auto-fill, minmax(300px, 1fr))",
          gap: "1.5rem",
        }}
      >
        {searchedItems.map((m) => (
          <div className="member-card" key={m.id}>
            <div className="member-avatar">
              <img
                src={`https://ui-avatars.com/api/?name=${encodeURIComponent(m.name)}&background=00BFA6&color=fff`}
                alt={m.name}
              />
            </div>
            <div style={{ flex: 1 }}>
              <h4 style={{ margin: 0 }}>{m.name}</h4>
              <p
                style={{
                  fontSize: "0.85rem",
                  color: "var(--text-muted)",
                  margin: "2px 0",
                }}
              >
                {m.email}
              </p>
              <p style={{ fontSize: "0.85rem", color: "var(--text-dark)" }}>
                📞 {m.phone}
              </p>
              <span style={{ fontSize: "0.75rem", color: "var(--text-muted)" }}>
                Joined: {m.joinDate}
              </span>
            </div>
            <div
              style={{
                display: "flex",
                flexDirection: "column",
                gap: "0.25rem",
              }}
            >
              <button
                className="btn"
                style={{ padding: "4px 8px", background: "var(--bg-light)" }}
                onClick={() => openModal(m)}
              >
                <i
                  className="fas fa-edit"
                  style={{ color: "var(--accent)" }}
                ></i>
              </button>
              <button
                className="btn"
                style={{ padding: "4px 8px", background: "var(--bg-light)" }}
                onClick={() => handleDelete(m.id)}
              >
                <i
                  className="fas fa-trash"
                  style={{ color: "var(--danger)" }}
                ></i>
              </button>
            </div>
          </div>
        ))}
      </div>

      {/* Custom Modal overlay system matching native layout styles markup seamlessly */}
      {showModal && (
        <div className="react-modal-overlay">
          <div className="react-modal-content card">
            <h3 style={{ marginBottom: "1.5rem" }}>
              {editId
                ? "Modify Member Profile Configuration"
                : "Register New Library Member"}
            </h3>
            <form onSubmit={handleFormSubmit}>
              <div className="form-group">
                <label className="form-label">Full Account Identity Name</label>
                <input
                  type="text"
                  className="form-control"
                  required
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                />
              </div>
              <div className="form-group">
                <label className="form-label">
                  Email Communications Endpoint
                </label>
                <input
                  type="email"
                  className="form-control"
                  required
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
              </div>
              <div className="form-group">
                <label className="form-label">Phone Mobile Matrix</label>
                <input
                  type="tel"
                  className="form-control"
                  required
                  value={phone}
                  onChange={(e) => setPhone(e.target.value)}
                />
              </div>
              <div
                style={{
                  display: "flex",
                  gap: "0.5rem",
                  justifyContent: "flex-end",
                  marginTop: "2rem",
                }}
              >
                <button
                  type="button"
                  className="btn"
                  style={{ background: "var(--bg-light)" }}
                  onClick={() => setShowModal(false)}
                >
                  Cancel
                </button>
                <button type="submit" className="btn btn-primary">
                  Commit Changes
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
}
