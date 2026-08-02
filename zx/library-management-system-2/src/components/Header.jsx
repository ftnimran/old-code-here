import { useState, useEffect, useRef } from "react";
import { useLibrary } from "../context/LibraryContext";
import { Link, useNavigate } from "react-router-dom";

export default function Header() {
  const {
    user,
    books,
    members,
    issued,
    notifications,
    addNotification,
    removeNotification,
    clearAllNotifications,
    hasUnread,
    setHasUnread,
  } = useLibrary();

  const navigate = useNavigate();

  const [theme, setTheme] = useState(
    () => localStorage.getItem("lib_theme") || "light",
  );
  useEffect(() => {
    document.documentElement.setAttribute("data-theme", theme);
    localStorage.setItem("lib_theme", theme);
  }, [theme]);

  const [searchQuery, setSearchQuery] = useState("");
  const handleSearchSubmit = (e) => {
    e.preventDefault();
    if (searchQuery.trim()) {
      navigate(`/books?search=${encodeURIComponent(searchQuery.trim())}`);
      setSearchQuery("");
    }
  };

  // --- SMART RELATIVE TIME FORMATTER ---
  const formatRelativeTime = (timestamp, fallbackTime) => {
    if (!timestamp) return fallbackTime;

    const now = new Date();
    const notifDate = new Date(timestamp);

    // Start of day calculations to ensure perfect day counting
    const startOfToday = new Date(
      now.getFullYear(),
      now.getMonth(),
      now.getDate(),
    ).getTime();
    const startOfNotif = new Date(
      notifDate.getFullYear(),
      notifDate.getMonth(),
      notifDate.getDate(),
    ).getTime();

    const diffDays = Math.floor(
      (startOfToday - startOfNotif) / (1000 * 60 * 60 * 24),
    );
    const timeStr = notifDate
      .toLocaleTimeString([], {
        hour: "2-digit",
        minute: "2-digit",
        hour12: true,
      })
      .toUpperCase();

    // 1. Today & Yesterday
    if (diffDays === 0) return `Today, ${timeStr}`;
    if (diffDays === 1) return `Yesterday, ${timeStr}`;

    // 2. 2 days to 6 days ago
    if (diffDays >= 2 && diffDays <= 6)
      return `${diffDays} days ago, ${timeStr}`;

    // 3. Exactly 1 week
    if (diffDays === 7) return `1 week ago`;

    // 4. More than 1 week (Show Date)
    if (diffDays > 7) {
      const day = notifDate.getDate().toString().padStart(2, "0");
      const month = (notifDate.getMonth() + 1).toString().padStart(2, "0");
      const year = notifDate.getFullYear();
      return `${day}-${month}-${year}`;
    }
  };

  // --- SMART BACKGROUND SCANNER ---
  useEffect(() => {
    const todayStr = new Date().toISOString().split("T")[0];
    const todayTime = new Date(todayStr).getTime();

    issued.forEach((tx) => {
      if (tx.status === "Issued") {
        const returnTime = new Date(tx.returnDate).getTime();
        const diffDays = Math.ceil(
          (returnTime - todayTime) / (1000 * 60 * 60 * 24),
        );
        const book = books.find((b) => b.id === tx.bookId) || {
          title: "A Book",
        };
        const member = members.find((m) => m.id === tx.memberId) || {
          name: "Someone",
        };

        // Short Transaction ID Format (Matches Table ID #XXXXXX)
        const shortTx = `#${tx.id.slice(-6)}`;

        if (diffDays < 0) {
          const overdueDays = Math.abs(diffDays);

          // Daily check logic (Din badhega, naya notification aayega)
          const isNotified = notifications.some(
            (n) =>
              n.title === "Book Overdue" &&
              n.desc.includes(`(Tx: ${shortTx})`) &&
              n.desc.includes(`by ${overdueDays} day`), // Exact match for that specific day
          );

          if (!isNotified) {
            addNotification(
              "Book Overdue",
              `"${book.title}" issued to ${member.name} is overdue by ${overdueDays} days. (Tx: ${shortTx})`,
              "danger",
              "fa-triangle-exclamation",
            );
          }
        } else if (diffDays <= 5) {
          const dueText = diffDays === 0 ? "today!" : `in ${diffDays} days.`;

          const isNotified = notifications.some(
            (n) =>
              n.title === "Book Due Soon" &&
              n.desc.includes(`(Tx: ${shortTx})`) &&
              n.desc.includes(dueText),
          );

          if (!isNotified) {
            addNotification(
              "Book Due Soon",
              `"${book.title}" issued to ${member.name} is due ${dueText} (Tx: ${shortTx})`,
              "warning",
              "fa-clock",
            );
          }
        }
      }
    });
  }, [issued, books, members, notifications, addNotification]);

  const [showNotifications, setShowNotifications] = useState(false);
  const notifRef = useRef(null);

  useEffect(() => {
    function handleClickOutside(event) {
      if (notifRef.current && !notifRef.current.contains(event.target)) {
        setShowNotifications(false);
      }
    }
    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  const handleBellClick = () => {
    setShowNotifications(!showNotifications);
    if (!showNotifications) setHasUnread(false);
  };

  return (
    <nav className="h-[70px] glass fixed top-0 right-0 left-[260px] z-40 flex items-center justify-between px-8 shadow-sm">
      <form onSubmit={handleSearchSubmit} className="relative w-[300px]">
        <i className="fa-solid fa-search absolute left-4 top-1/2 -translate-y-1/2 text-text-muted"></i>
        <input
          type="text"
          placeholder="Search books and press Enter..."
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          className="w-full py-2 pl-10 pr-4 rounded-full border border-border-color bg-bg-light focus:outline-none focus:border-primary text-text-dark"
        />
      </form>

      <div className="flex items-center gap-6">
        <div className="relative" ref={notifRef}>
          <div
            className="relative cursor-pointer p-2 rounded-full hover:bg-black/5 transition-colors"
            onClick={handleBellClick}
          >
            <i
              className={`fa-regular fa-bell text-xl ${showNotifications ? "text-primary" : "text-text-muted"} transition-colors`}
            ></i>
            {hasUnread && (
              <span className="absolute top-1 right-1 w-2.5 h-2.5 bg-danger rounded-full border-2 border-bg-white animate-pulse"></span>
            )}
          </div>

          {showNotifications && (
            <div className="absolute right-0 mt-3 w-[360px] bg-bg-white border border-border-color rounded-xl shadow-lg z-50 animate-fade-in overflow-hidden">
              <div className="p-4 border-b border-border-color flex justify-between items-center bg-bg-light/50">
                <h3 className="font-bold text-text-dark">Notifications</h3>
                {notifications.length > 0 && (
                  <button
                    onClick={clearAllNotifications}
                    className="text-xs text-primary font-medium hover:underline cursor-pointer"
                  >
                    Clear all
                  </button>
                )}
              </div>

              <div className="max-h-[26rem] overflow-y-auto">
                {notifications.length === 0 ? (
                  <div className="p-8 text-center text-text-muted text-sm">
                    <i className="fa-regular fa-bell-slash text-3xl mb-3 opacity-50"></i>
                    <p>No new notifications</p>
                  </div>
                ) : (
                  notifications.map((notif) => (
                    <div
                      key={notif.id}
                      className="p-4 border-b border-border-color hover:bg-bg-light/50 transition-colors flex gap-3 relative group"
                    >
                      <div
                        className={`w-10 h-10 rounded-full flex items-center justify-center flex-shrink-0 bg-${notif.type}/10 text-${notif.type}`}
                      >
                        <i className={`fa-solid ${notif.icon}`}></i>
                      </div>
                      <div className="flex-1 pr-6">
                        <h4 className="text-sm font-bold text-text-dark mb-0.5">
                          {notif.title}
                        </h4>
                        <p className="text-xs text-text-muted leading-relaxed">
                          {notif.desc}
                        </p>

                        {/* UPDATE: Smart Dynamic Time Formatting Applied Here */}
                        <span className="text-[10px] text-primary font-semibold mt-1.5 block">
                          {formatRelativeTime(notif.timestamp, notif.time)}
                        </span>
                      </div>
                      <button
                        onClick={(e) => {
                          e.stopPropagation();
                          removeNotification(notif.id);
                        }}
                        className="absolute right-3 top-4 text-text-muted hover:text-danger opacity-0 group-hover:opacity-100 transition-opacity cursor-pointer text-xs p-1 bg-bg-light rounded"
                        title="Dismiss"
                      >
                        <i className="fa-solid fa-xmark"></i>
                      </button>
                    </div>
                  ))
                )}
              </div>
            </div>
          )}
        </div>

        <button
          onClick={() => setTheme(theme === "light" ? "dark" : "light")}
          type="button"
          className="text-text-muted hover:text-warning transition-colors p-2 rounded-full hover:bg-black/5 flex items-center justify-center w-10 h-10 cursor-pointer"
        >
          <i
            className={`fa-solid ${theme === "dark" ? "fa-sun text-amber-400" : "fa-moon text-text-muted"} text-xl`}
          ></i>
        </button>

        <Link to="/profile" className="block group cursor-pointer">
          <div className="w-10 h-10 rounded-full border-2 border-primary overflow-hidden group-hover:opacity-80 transition-opacity bg-bg-light flex items-center justify-center flex-shrink-0">
            <img
              src={
                user?.avatar ||
                `https://ui-avatars.com/api/?name=${encodeURIComponent(user?.name || "Admin")}&background=6C63FF&color=fff`
              }
              alt="Profile"
              className="w-full h-full object-cover"
            />
          </div>
        </Link>
      </div>
    </nav>
  );
}
