import { NavLink } from "react-router-dom";
import { useLibrary } from "../context/LibraryContext";

export default function Sidebar() {
  const { user, logout, isSidebarOpen, setIsSidebarOpen } = useLibrary();

  // Dynamic Navigation Items
  const navItems = [
    { name: "Dashboard", path: "/", icon: "fa-th-large" },
    { name: "Books", path: "/books", icon: "fa-book" },
    { name: "Issue/Return", path: "/issued", icon: "fa-exchange-alt" },
  ];

  if (user?.role === "admin") {
    navItems.push({ name: "Members", path: "/members", icon: "fa-users" });
  }

  navItems.push({ name: "Profile", path: "/profile", icon: "fa-user-circle" });

  return (
    <>
      {/* Mobile/Tablet Overlay - Changed md:hidden to lg:hidden */}
      {isSidebarOpen && (
        <div
          className="fixed inset-0 bg-black/50 z-40 lg:hidden backdrop-blur-sm animate-fade-in"
          onClick={() => setIsSidebarOpen(false)}
        ></div>
      )}

      {/* Changed md:translate-x-0 and md:shadow-sm to lg: */}
      <aside
        className={`w-[260px] h-screen bg-bg-white fixed top-0 left-0 z-50 shadow-xl lg:shadow-sm flex flex-col p-6 transition-transform duration-300 ${
          isSidebarOpen ? "translate-x-0" : "-translate-x-full"
        } lg:translate-x-0`}
      >
        <div className="flex items-center gap-3 mb-10 text-primary">
          <i className="fas fa-book-reader fa-2x"></i>
          <h2 className="text-2xl font-bold font-main">LibMaster</h2>
        </div>

        <ul className="flex-1 list-none space-y-2">
          {navItems.map((item) => (
            <li key={item.name}>
              <NavLink
                to={item.path}
                onClick={() => setIsSidebarOpen(false)}
                className={({ isActive }) =>
                  `flex items-center gap-3 p-3 rounded-lg font-medium transition-all ${
                    isActive
                      ? "bg-primary/10 text-primary"
                      : "text-text-muted hover:bg-primary/10 hover:text-primary"
                  }`
                }
              >
                <i className={`fas ${item.icon} w-5 text-center`}></i>
                <span>{item.name}</span>
              </NavLink>
            </li>
          ))}
        </ul>

        <div>
          <button
            onClick={logout}
            className="flex items-center gap-3 p-3 rounded-lg font-medium text-danger hover:bg-danger/10 w-full transition-all cursor-pointer"
          >
            <i className="fas fa-sign-out-alt w-5 text-center"></i>
            <span>Logout</span>
          </button>
        </div>
      </aside>
    </>
  );
}
