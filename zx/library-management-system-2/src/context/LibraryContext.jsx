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
    },
    {
      id: "102",
      title: "Clean Code",
      author: "Robert C. Martin",
      category: "Technology",
      isbn: "9780132350884",
      quantity: 10,
      available: 10,
      cover:
        "https://images.unsplash.com/photo-1532012197267-da84d127e765?auto=format&fit=crop&q=80&w=200",
    },
  ],
  members: [
    {
      id: "M001",
      name: "Jarvis Bell",
      email: "jarvis@example.com",
      phone: "7534125645",
      joinDate: "2026-07-09",
    },
  ],
  users: [
    {
      id: "U1",
      name: "Admin User",
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

  const [notifications, setNotifications] = useState(() => {
    return JSON.parse(localStorage.getItem("lib_notifications")) || [];
  });
  const [hasUnread, setHasUnread] = useState(() => {
    return JSON.parse(localStorage.getItem("lib_notif_unread")) || false;
  });

  useEffect(() => {
    localStorage.setItem("lib_books", JSON.stringify(books));
  }, [books]);
  useEffect(() => {
    localStorage.setItem("lib_members", JSON.stringify(members));
  }, [members]);
  useEffect(() => {
    localStorage.setItem("lib_issued", JSON.stringify(issued));
  }, [issued]);
  useEffect(() => {
    localStorage.setItem("lib_notifications", JSON.stringify(notifications));
  }, [notifications]);
  useEffect(() => {
    localStorage.setItem("lib_notif_unread", JSON.stringify(hasUnread));
  }, [hasUnread]);

  useEffect(() => {
    if (user) localStorage.setItem("lib_current_user", JSON.stringify(user));
    else localStorage.removeItem("lib_current_user");
  }, [user]);

  const addNotification = (title, desc, type = "primary", icon = "fa-bell") => {
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
      time: rawTimeString.toUpperCase(),
      timestamp: Date.now(), // NAYA LOGIC: Relative date calculations ke liye zaroori
    };

    setNotifications((prev) => [newNotif, ...prev]);
    setHasUnread(true);
  };

  const removeNotification = (id) => {
    setNotifications((prev) => prev.filter((n) => n.id !== id));
  };

  const clearAllNotifications = () => {
    setNotifications([]);
    setHasUnread(false);
  };

  const login = (email, password) => {
    const dbUsers =
      JSON.parse(localStorage.getItem("lib_users")) || DEFAULT_DATA.users;
    const found = dbUsers.find(
      (u) => u.email === email && u.password === password,
    );
    if (found) {
      setUser(found);
      return true;
    }
    return false;
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
