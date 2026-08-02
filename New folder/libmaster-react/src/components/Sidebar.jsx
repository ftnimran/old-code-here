import React from "react";

export default function Sidebar({ currentPage, setCurrentPage, onLogout }) {
  const navItems = [
    { id: "dashboard", label: "Dashboard", icon: "fas fa-th-large" },
    { id: "books", label: "Books", icon: "fas fa-book" },
    { id: "issued", label: "Issue/Return", icon: "fas fa-exchange-alt" },
    { id: "members", label: "Members", icon: "fas fa-users" },
    { id: "profile", label: "Profile", icon: "fas fa-user-circle" },
  ];

  return (
    <aside
      className="sidebar"
      style={{
        width: "var(--sidebar-width)",
        height: "100vh",
        background: "var(--bg-white)",
        position: "fixed",
        top: 0,
        left: 0,
        padding: "1.5rem",
        display: "flex",
        flexDirection: "column",
        borderRight: "1px solid var(--border-color)",
        zIndex: 105,
      }}
    >
      <div
        className="brand"
        style={{
          display: "flex",
          alignItems: "center",
          gap: "0.75rem",
          marginBottom: "2.5rem",
        }}
      >
        <i
          className="fas fa-book-reader fa-2x"
          style={{ color: "var(--primary)" }}
        ></i>
        <h2>LibMaster</h2>
      </div>

      <ul
        className="nav-links"
        style={{
          listStyle: "none",
          display: "flex",
          flexDirection: "column",
          gap: "0.5rem",
          flex: 1,
        }}
      >
        {navItems.map((item) => (
          <li key={item.id} className="nav-item">
            <button
              onClick={() => setCurrentPage(item.id)}
              className={`nav-link ${currentPage === item.id || (item.id === "books" && currentPage === "add-book") ? "active" : ""}`}
              style={{
                width: "100%",
                display: "flex",
                alignItems: "center",
                gap: "1rem",
                padding: "12px 16px",
                borderRadius: "var(--radius-sm)",
                background:
                  currentPage === item.id ||
                  (item.id === "books" && currentPage === "add-book")
                    ? "rgba(108, 99, 255, 0.1)"
                    : "transparent",
                color:
                  currentPage === item.id ||
                  (item.id === "books" && currentPage === "add-book")
                    ? "var(--primary)"
                    : "var(--text-dark)",
                border: "none",
                textAlign: "left",
                fontSize: "1rem",
                fontWeight: "500",
                transition: "var(--transition)",
              }}
            >
              <i className={item.icon} style={{ width: "20px" }}></i>
              <span>{item.label}</span>
            </button>
          </li>
        ))}
      </ul>

      <div className="nav-item">
        <button
          onClick={onLogout}
          className="nav-link"
          style={{
            width: "100%",
            display: "flex",
            alignItems: "center",
            gap: "1rem",
            padding: "12px 16px",
            borderRadius: "var(--radius-sm)",
            background: "transparent",
            color: "var(--danger)",
            border: "none",
            textAlign: "left",
            fontSize: "1rem",
            fontWeight: "500",
          }}
        >
          <i className="fas fa-sign-out-alt"></i>
          <span>Logout</span>
        </button>
      </div>
    </aside>
  );
}
