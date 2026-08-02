import { createContext, useState, useEffect, useContext } from "react";

const LibraryContext = createContext();

const DEFAULT_DATA = {
  books: [
    {
      id: "101",
      title: "The Great Gatsby",
      author: "F. Scott Fitzgerald",
      category: "Fiction",
      isbn: "9780743273565",
      quantity: 5,
      available: 5,
      cover:
        "https://images.unsplash.com/photo-1544947950-fa07a98d237f?auto=format&fit=crop&q=80&w=200",
      pdf: "",
    },
  ],
  members: [],
  users: [
    {
      id: "U1",
      name: "Admin User",
      username: "admin",
      email: "admin@library.com",
      password: "password123",
      role: "admin",
    },
  ],
  issued: [],
};

export const LibraryProvider = ({ children }) => {
  const loadData = (key) =>
    JSON.parse(localStorage.getItem(`lib_${key}`)) || DEFAULT_DATA[key];

  const [books, setBooks] = useState(() => loadData("books"));
  const [members, setMembers] = useState(() => loadData("members"));
  const [issued, setIssued] = useState(() => loadData("issued"));
  const [user, setUser] = useState(() =>
    JSON.parse(localStorage.getItem("lib_current_user")),
  );

  const [allNotifications, setAllNotifications] = useState(() => {
    return JSON.parse(localStorage.getItem("lib_notifications")) || [];
  });

  const [hasUnread, setHasUnread] = useState(() => {
    return JSON.parse(localStorage.getItem("lib_notif_unread")) || false;
  });

  const [isSidebarOpen, setIsSidebarOpen] = useState(false);

  // ===============================================
  // GLOBAL CUSTOM ALERT / CONFIRM DIALOG STATE
  // ===============================================
  const [dialogConfig, setDialogConfig] = useState({
    isOpen: false,
    type: "alert", // 'alert' | 'confirm'
    iconType: "info", // 'success' | 'error' | 'warning' | 'info'
    title: "",
    message: "",
    onConfirm: null,
  });

  const showAlert = (message, title = "Notice", iconType = "info") => {
    setDialogConfig({
      isOpen: true,
      type: "alert",
      iconType,
      title,
      message,
      onConfirm: null,
    });
  };

  const showConfirm = (message, onConfirm, title = "Confirm Action") => {
    setDialogConfig({
      isOpen: true,
      type: "confirm",
      iconType: "confirm",
      title,
      message,
      onConfirm,
    });
  };

  const closeDialog = () => {
    setDialogConfig((prev) => ({ ...prev, isOpen: false }));
  };
  // ===============================================

  useEffect(
    () => localStorage.setItem("lib_books", JSON.stringify(books)),
    [books],
  );
  useEffect(
    () => localStorage.setItem("lib_members", JSON.stringify(members)),
    [members],
  );
  useEffect(
    () => localStorage.setItem("lib_issued", JSON.stringify(issued)),
    [issued],
  );
  useEffect(
    () =>
      localStorage.setItem(
        "lib_notifications",
        JSON.stringify(allNotifications),
      ),
    [allNotifications],
  );
  useEffect(
    () => localStorage.setItem("lib_notif_unread", JSON.stringify(hasUnread)),
    [hasUnread],
  );

  useEffect(() => {
    if (user) localStorage.setItem("lib_current_user", JSON.stringify(user));
    else localStorage.removeItem("lib_current_user");
  }, [user]);

  useEffect(() => {
    const todayStr = new Date().toISOString().split("T")[0];
    let updated = false;

    const newMembers = members.map((m) => {
      const hasOverdue = issued.some(
        (tx) =>
          tx.memberId === m.id &&
          tx.status === "Issued" &&
          tx.returnDate < todayStr,
      );
      let newStatus = m.status;

      if (hasOverdue && m.status !== "Deactivate") {
        newStatus = "Deactivate";
        updated = true;
      } else if (!hasOverdue && m.status === "Deactivate") {
        newStatus = "Active";
        updated = true;
      }
      return { ...m, status: newStatus };
    });

    if (updated) setMembers(newMembers);
  }, [issued]);

  const notifications =
    user?.role === "admin"
      ? allNotifications
      : allNotifications.filter(
          (n) => n.target === "all" || n.target === user?.email,
        );

  const addNotification = (
    title,
    desc,
    type = "primary",
    icon = "fa-bell",
    target = "all",
  ) => {
    const rawTimeString = new Date().toLocaleTimeString([], {
      hour: "2-digit",
      minute: "2-digit",
      hour12: true,
    });
    const newNotif = {
      id: "N" + Date.now(),
      title,
      desc,
      type,
      icon,
      target,
      time: rawTimeString.toUpperCase(),
      timestamp: Date.now(),
    };
    setAllNotifications((prev) => [newNotif, ...prev]);

    if (user?.role === "admin" || target === "all" || target === user?.email) {
      setHasUnread(true);
    }
  };

  const removeNotification = (id) => {
    setAllNotifications((prev) => prev.filter((n) => n.id !== id));
  };

  const clearAllNotifications = () => {
    if (user?.role === "admin") {
      setAllNotifications([]);
    } else {
      setAllNotifications((prev) =>
        prev.filter((n) => n.target !== user?.email && n.target !== "all"),
      );
    }
    setHasUnread(false);
  };

  const login = (identifier, password) => {
    const dbUsers =
      JSON.parse(localStorage.getItem("lib_users")) || DEFAULT_DATA.users;
    const found = dbUsers.find(
      (u) =>
        (u.email === identifier || u.username === identifier) &&
        u.password === password,
    );

    if (!found)
      return {
        success: false,
        message:
          "Invalid credentials! Please check your username/email and password.",
      };

    if (found.role === "student") {
      const dbMembers = JSON.parse(localStorage.getItem("lib_members")) || [];
      const memberIndex = dbMembers.findIndex((m) => m.email === found.email);

      if (memberIndex !== -1) {
        let member = dbMembers[memberIndex];
        const now = new Date();

        if (member.lastLogin) {
          const lastLoginDate = new Date(member.lastLogin);
          const diffDays = Math.floor(
            (now - lastLoginDate) / (1000 * 60 * 60 * 24),
          );
          if (diffDays >= 15 && member.status !== "Deactivate") {
            member.status = "Inactive";
          }
        }

        if (member.status === "Inactive") {
          if (!member.inactiveTimestamp) {
            member.inactiveTimestamp = now.toISOString();
            dbMembers[memberIndex] = member;
            setMembers(dbMembers);
            return {
              success: false,
              message:
                "Your account is Inactive due to 15 days of inactivity. A 15-minute wait timer has started. Please try again after 15 minutes.",
            };
          } else {
            const inactiveStart = new Date(member.inactiveTimestamp);
            const diffMins = Math.floor((now - inactiveStart) / (1000 * 60));
            if (diffMins < 15) {
              return {
                success: false,
                message: `Account is still inactive. Please wait ${15 - diffMins} more minutes to sign in.`,
              };
            } else {
              member.status = "Active";
              member.inactiveTimestamp = null;
            }
          }
        }

        member.lastLogin = now.toISOString();
        dbMembers[memberIndex] = member;
        setMembers([...dbMembers]);
      }
    }

    setUser(found);
    return { success: true };
  };

  const signup = (userData) => {
    const dbUsers =
      JSON.parse(localStorage.getItem("lib_users")) || DEFAULT_DATA.users;
    const exists = dbUsers.find(
      (u) => u.email === userData.email || u.username === userData.username,
    );

    if (exists)
      return { success: false, message: "Username or Email already exists!" };

    const timestamp = Date.now();
    const newUser = {
      id: "U" + timestamp,
      ...userData,
      role: "student",
      avatar: "",
    };

    dbUsers.push(newUser);
    localStorage.setItem("lib_users", JSON.stringify(dbUsers));

    const newMember = {
      id: "M" + timestamp,
      name: userData.name,
      email: userData.email,
      phone: userData.phone,
      avatar: "",
      status: "Active",
      joinDate: new Date().toISOString().split("T")[0],
      lastLogin: new Date().toISOString(),
    };
    setMembers((prev) => [...prev, newMember]);

    addNotification(
      "New Member",
      `${userData.name} joined the library via Sign Up.`,
      "success",
      "fa-user-plus",
      "admin@library.com",
    );

    setUser(newUser);
    return { success: true };
  };

  const logout = () => setUser(null);

  // Helper variables for dialog rendering
  const dialogIcons = {
    success: {
      icon: "fa-circle-check",
      color: "text-success",
      bg: "bg-success/10",
    },
    error: {
      icon: "fa-circle-xmark",
      color: "text-danger",
      bg: "bg-danger/10",
    },
    warning: {
      icon: "fa-triangle-exclamation",
      color: "text-warning",
      bg: "bg-warning/10",
    },
    info: {
      icon: "fa-circle-info",
      color: "text-primary",
      bg: "bg-primary/10",
    },
    confirm: {
      icon: "fa-circle-question",
      color: "text-primary",
      bg: "bg-primary/10",
    },
  };

  const currentIcon = dialogIcons[dialogConfig.iconType] || dialogIcons.info;

  return (
    <LibraryContext.Provider
      value={{
        books,
        setBooks,
        members,
        setMembers,
        issued,
        setIssued,
        user,
        setUser,
        login,
        signup,
        logout,
        notifications,
        addNotification,
        removeNotification,
        clearAllNotifications,
        hasUnread,
        setHasUnread,
        isSidebarOpen,
        setIsSidebarOpen,
        showAlert,
        showConfirm,
      }}
    >
      {children}

      {/* GLOBAL CUSTOM MODAL UI */}
      {dialogConfig.isOpen && (
        <div className="fixed inset-0 z-[100] flex items-center justify-center bg-black/60 backdrop-blur-sm p-4 animate-fade-in">
          <div className="bg-bg-white rounded-2xl shadow-2xl max-w-sm w-full p-6 text-center border border-border-color transform transition-all scale-100">
            <div
              className={`w-16 h-16 ${currentIcon.bg} ${currentIcon.color} rounded-full flex items-center justify-center mx-auto mb-4`}
            >
              <i className={`fas ${currentIcon.icon} text-3xl`}></i>
            </div>
            <h3 className="text-xl font-bold text-text-dark mb-2">
              {dialogConfig.title}
            </h3>
            <p className="text-text-muted mb-8">{dialogConfig.message}</p>
            <div className="flex flex-col sm:flex-row justify-center gap-3">
              {dialogConfig.type === "confirm" && (
                <button
                  onClick={closeDialog}
                  className="px-5 py-2.5 rounded-lg font-bold text-text-muted hover:bg-bg-light border border-border-color transition-all w-full"
                >
                  Cancel
                </button>
              )}
              <button
                onClick={() => {
                  if (dialogConfig.onConfirm) dialogConfig.onConfirm();
                  closeDialog();
                }}
                className={`px-5 py-2.5 rounded-lg font-bold text-white transition-all w-full shadow-md ${
                  dialogConfig.iconType === "error"
                    ? "bg-danger hover:bg-[#e04040]"
                    : dialogConfig.iconType === "warning"
                      ? "bg-warning hover:bg-[#d4ac0d]"
                      : "bg-primary hover:bg-[#5b54e0]"
                }`}
              >
                {dialogConfig.type === "confirm" ? "Confirm" : "OK"}
              </button>
            </div>
          </div>
        </div>
      )}
    </LibraryContext.Provider>
  );
};

export const useLibrary = () => useContext(LibraryContext);
