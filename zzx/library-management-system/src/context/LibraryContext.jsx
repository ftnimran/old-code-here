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

  const [dialogConfig, setDialogConfig] = useState({
    isOpen: false,
    type: "alert",
    iconType: "info",
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

  const closeDialog = () =>
    setDialogConfig((prev) => ({ ...prev, isOpen: false }));

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

  const notifications = allNotifications.filter((n) => {
    if (user?.role === "admin") {
      return n.target === "admin" || n.target === "all";
    } else {
      return n.target === user?.email || n.target === "all";
    }
  });

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

    setAllNotifications((prev) => {
      // FIXED: Duplicate prevention will only block if exact same notification was generated within the last 2 seconds.
      // This prevents double-clicks but allows recurring notifications for the same member.
      if (
        prev.some(
          (n) =>
            n.title === title &&
            n.desc === desc &&
            n.target === target &&
            Date.now() - n.timestamp < 2000,
        )
      ) {
        return prev;
      }

      const newNotif = {
        id: "N" + Date.now() + Math.floor(Math.random() * 1000),
        title,
        desc,
        type,
        icon,
        target,
        time: rawTimeString.toUpperCase(),
        timestamp: Date.now(),
      };

      const currentUserStr = localStorage.getItem("lib_current_user");
      if (currentUserStr) {
        const currentUser = JSON.parse(currentUserStr);
        if (
          target === "all" ||
          (currentUser.role === "admin" && target === "admin") ||
          (currentUser.role !== "admin" && target === currentUser.email)
        ) {
          setTimeout(() => setHasUnread(true), 0);
        }
      }

      return [newNotif, ...prev];
    });
  };

  const removeNotification = (id) =>
    setAllNotifications((prev) => prev.filter((n) => n.id !== id));

  const clearAllNotifications = () => {
    if (user?.role === "admin") {
      setAllNotifications((prev) =>
        prev.filter((n) => n.target !== "admin" && n.target !== "all"),
      );
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

    if (!found) return { success: false, message: "Invalid credentials!" };

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
          if (diffDays >= 15 && member.status !== "Deactivate")
            member.status = "Inactive";
        }

        if (member.status === "Inactive") {
          if (!member.inactiveTimestamp) {
            member.inactiveTimestamp = now.toISOString();
            dbMembers[memberIndex] = member;
            setMembers(dbMembers);
            return {
              success: false,
              message: "Account inactive. A 15-minute wait timer has started.",
            };
          } else {
            const inactiveStart = new Date(member.inactiveTimestamp);
            const diffMins = Math.floor((now - inactiveStart) / (1000 * 60));
            if (diffMins < 15)
              return {
                success: false,
                message: `Please wait ${15 - diffMins} more minutes.`,
              };
            else {
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
      "Account Created",
      `Hello ${userData.name}, your account is ready!`,
      "success",
      "fa-user",
      userData.email,
    );
    addNotification(
      "New Registration",
      `${userData.name} signed up as a new student.`,
      "primary",
      "fa-user-plus",
      "admin",
    );

    setUser(newUser);
    return { success: true };
  };

  const logout = () => setUser(null);

  const acceptIssueRequest = (transactionId) => {
    setIssued((prev) =>
      prev.map((tx) =>
        tx.id === transactionId ? { ...tx, status: "Issued" } : tx,
      ),
    );
    const tx = issued.find((t) => t.id === transactionId);

    const member = members.find((m) => m.id === tx?.memberId) || {
      name: "Unknown Student",
      email: "all",
    };
    const book = books.find((b) => b.id === tx?.bookId) || {
      title: "Unknown Book",
    };

    addNotification(
      "Request Accepted",
      `Your request for "${book.title}" was approved. Happy reading!`,
      "success",
      "fa-circle-check",
      member.email,
    );
    addNotification(
      "Issue Confirmed",
      `You approved ${member.name}'s request for "${book.title}".`,
      "info",
      "fa-check",
      "admin",
    );
  };

  const rejectIssueRequest = (transactionId) => {
    const tx = issued.find((t) => t.id === transactionId);
    if (!tx) return;
    setIssued((prev) =>
      prev.map((t) =>
        t.id === transactionId ? { ...t, status: "Rejected" } : t,
      ),
    );
    setBooks((prev) =>
      prev.map((b) =>
        b.id === tx.bookId ? { ...b, available: b.available + 1 } : b,
      ),
    );

    const member = members.find((m) => m.id === tx.memberId) || {
      name: "Unknown Student",
      email: "all",
    };
    const book = books.find((b) => b.id === tx.bookId) || {
      title: "Unknown Book",
    };

    addNotification(
      "Request Declined",
      `Your request for "${book.title}" was rejected by the Admin.`,
      "danger",
      "fa-circle-xmark",
      member.email,
    );
    addNotification(
      "Issue Rejected",
      `You rejected ${member.name}'s request for "${book.title}".`,
      "info",
      "fa-xmark",
      "admin",
    );
  };

  const returnBook = (transactionId) => {
    const tx = issued.find((t) => t.id === transactionId);
    if (!tx) return;
    setIssued((prev) =>
      prev.map((t) =>
        t.id === transactionId ? { ...t, status: "Returned" } : t,
      ),
    );
    setBooks((prev) =>
      prev.map((b) =>
        b.id === tx.bookId ? { ...b, available: b.available + 1 } : b,
      ),
    );

    const member = members.find((m) => m.id === tx.memberId) || {
      name: "Unknown Student",
      email: "all",
    };
    const book = books.find((b) => b.id === tx.bookId) || {
      title: "Unknown Book",
    };

    if (user?.role === "admin") {
      addNotification(
        "Book Returned",
        `Admin marked "${book.title}" as returned.`,
        "info",
        "fa-arrow-rotate-left",
        member.email,
      );
      addNotification(
        "Return Logged",
        `You successfully processed the return of "${book.title}" from ${member.name}.`,
        "success",
        "fa-check",
        "admin",
      );
    } else {
      addNotification(
        "Book Returned",
        `${member.name} has returned "${book.title}".`,
        "primary",
        "fa-arrow-rotate-left",
        "admin",
      );
      addNotification(
        "Return Successful",
        `You successfully returned "${book.title}".`,
        "success",
        "fa-arrow-rotate-left",
        member.email,
      );
    }
  };

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
        acceptIssueRequest,
        rejectIssueRequest,
        returnBook,
      }}
    >
      {children}
      {dialogConfig.isOpen && (
        <div className="fixed inset-0 z-[100] flex items-center justify-center bg-black/60 backdrop-blur-sm p-4 animate-fade-in">
          <div className="bg-bg-white rounded-2xl shadow-2xl max-w-sm w-full p-6 text-center border border-border-color">
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
