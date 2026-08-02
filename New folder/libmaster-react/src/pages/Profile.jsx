import React, { useState } from "react";

export default function Profile({ currentUser, setCurrentUser, DB_KEYS }) {
  const [name, setName] = useState(currentUser?.name || "");
  const [email, setEmail] = useState(currentUser?.email || "");
  const [password, setPassword] = useState("");

  const handleProfileUpdate = (e) => {
    e.preventDefault();
    const users = JSON.parse(localStorage.getItem(DB_KEYS.USERS)) || [];
    const index = users.findIndex((u) => u.id === currentUser.id);

    if (index !== -1) {
      users[index].name = name;
      users[index].email = email;
      if (password) users[index].password = password;

      const updatedUser = users[index];
      localStorage.setItem(DB_KEYS.USERS, JSON.stringify(users));
      localStorage.setItem(DB_KEYS.CURRENT_USER, JSON.stringify(updatedUser));
      setCurrentUser(updatedUser);
      alert("Profile properties committed and saved successfully!");
      setPassword("");
    }
  };

  return (
    <div style={{ maxWidth: "600px", margin: "0 auto" }}>
      <div>
        <h1>Profile Settings</h1>
        <p style={{ color: "var(--text-muted)" }}>
          Manage internal administrative credentials accounts layout tags
          context.
        </p>
      </div>

      <div
        className="card"
        style={{ marginTop: "2rem", textAlign: "center", padding: "2rem" }}
      >
        <div style={{ marginBottom: "1.5rem" }}>
          <img
            src={`https://ui-avatars.com/api/?name=${encodeURIComponent(name)}&size=120&background=6C63FF&color=fff`}
            alt="Avatar"
            style={{
              borderRadius: "50%",
              width: "120px",
              height: "120px",
              border: "3px solid var(--primary)",
            }}
          />
          <h3 style={{ marginTop: "1rem" }}>{currentUser?.name}</h3>
          <span
            className="badge badge-primary"
            style={{ textTransform: "uppercase" }}
          >
            {currentUser?.role || "User"}
          </span>
        </div>

        <form onSubmit={handleProfileUpdate} style={{ textAlign: "left" }}>
          <div className="form-group">
            <label className="form-label">Full Display Profile Name</label>
            <input
              type="text"
              className="form-control"
              required
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label className="form-label">Account Authorized Email</label>
            <input
              type="email"
              className="form-control"
              required
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label className="form-label">System Role Type Indicator</label>
            <input
              type="text"
              className="form-control"
              value={currentUser?.role || "student"}
              disabled
              style={{ cursor: "not-allowed", opacity: 0.7 }}
            />
          </div>
          <div className="form-group">
            <label className="form-label">
              New Password Selection (Leave empty to keep current)
            </label>
            <input
              type="password"
              className="form-control"
              placeholder="••••••••"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>
          <button
            type="submit"
            className="btn btn-primary"
            style={{
              width: "100%",
              justifyContent: "center",
              marginTop: "1rem",
            }}
          >
            <i className="fas fa-save"></i> Save Profile Modifications
          </button>
        </form>
      </div>
    </div>
  );
}
