import React from "react";

export default function TopNavbar({
  theme,
  toggleTheme,
  user,
  globalSearch,
  setGlobalSearch,
  setCurrentPage,
}) {
  return (
    <nav className="top-navbar">
      <div className="search-bar">
        <i
          className="fas fa-search search-icon"
          style={{ color: "var(--text-muted)" }}
        ></i>
        <input
          type="text"
          placeholder="Search titles, authors, categories..."
          value={globalSearch}
          onChange={(e) => {
            setGlobalSearch(e.target.value);
            setCurrentPage("books"); // Fall to books catalog view automatically on query entry
          }}
        />
      </div>
      <div className="nav-actions">
        <button className="theme-toggle" onClick={toggleTheme}>
          <i className={theme === "dark" ? "fas fa-sun" : "fas fa-moon"}></i>
        </button>
        <div style={{ display: "flex", alignItems: "center", gap: "0.5rem" }}>
          <span id="nav-user-name" style={{ fontWeight: "500" }}>
            {user ? user.name : "User"}
          </span>
          <div className="profile-avatar">
            <img
              src={`https://ui-avatars.com/api/?name=${encodeURIComponent(user?.name || "Admin")}&background=6C63FF&color=fff`}
              alt="Profile"
            />
          </div>
        </div>
      </div>
    </nav>
  );
}
