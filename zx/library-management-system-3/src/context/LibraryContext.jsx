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
      available: 3,
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

  // Persist Data
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

  // --- REQUIREMENT 3: AUTO-DEACTIVATE LOGIC (JAB TAK OVERDUE RHEGA) ---
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

  // --- REQUIREMENT 5: NOTIFICATIONS FILTER ---
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

    // Only set unread if it's meant for the logged-in user
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

  // --- REQUIREMENT 3: 15-DAYS INACTIVE & 15-MIN WAIT LOGIC ---
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

  // --- REQUIREMENT 1: DEFAULT STUDENT ROLE ---
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
      role: "student", // Forced to student
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
      }}
    >
      {children}
    </LibraryContext.Provider>
  );
};

export const useLibrary = () => useContext(LibraryContext);
