import React, { useState, useEffect } from "react";
import Sidebar from "./components/Sidebar";
import TopNavbar from "./components/TopNavbar";
import Dashboard from "./pages/Dashboard";
import Login from "./pages/Login";
import BooksManager from "./pages/BooksManager";
import AddBook from "./pages/AddBook";
import CirculationDesk from "./pages/CirculationDesk";
import Members from "./pages/Members";
import Profile from "./pages/Profile";

const DB_KEYS = {
  BOOKS: "lib_books",
  USERS: "lib_users",
  MEMBERS: "lib_members",
  ISSUED: "lib_issued",
  CURRENT_USER: "lib_current_user",
  THEME: "lib_theme",
};

const DEFAULT_BOOKS = [
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
  {
    id: "103",
    title: "To Kill a Mockingbird",
    author: "Harper Lee",
    category: "Fiction",
    isbn: "9780061120084",
    quantity: 7,
    available: 2,
    cover:
      "https://images.unsplash.com/photo-1509021436665-8f07dbf5bf1d?auto=format&fit=crop&q=80&w=200",
  },
];

export default function App() {
  const [currentPage, setCurrentPage] = useState("dashboard");
  const [currentUser, setCurrentUser] = useState(null);
  const [theme, setTheme] = useState("light");
  const [books, setBooks] = useState([]);
  const [members, setMembers] = useState([]);
  const [issued, setIssued] = useState([]);
  const [editBookId, setEditBookId] = useState(null);
  const [globalSearch, setGlobalSearch] = useState("");

  useEffect(() => {
    // Initialize Core Data structures
    if (!localStorage.getItem(DB_KEYS.BOOKS))
      localStorage.setItem(DB_KEYS.BOOKS, JSON.stringify(DEFAULT_BOOKS));
    if (!localStorage.getItem(DB_KEYS.USERS)) {
      localStorage.setItem(
        DB_KEYS.USERS,
        JSON.stringify([
          {
            id: "admin",
            name: "Admin User",
            email: "admin@lib.com",
            password: "password",
            role: "admin",
          },
        ]),
      );
    }
    if (!localStorage.getItem(DB_KEYS.MEMBERS))
      localStorage.setItem(DB_KEYS.MEMBERS, JSON.stringify([]));
    if (!localStorage.getItem(DB_KEYS.ISSUED))
      localStorage.setItem(DB_KEYS.ISSUED, JSON.stringify([]));

    // Load into memory
    setBooks(JSON.parse(localStorage.getItem(DB_KEYS.BOOKS)));
    setMembers(JSON.parse(localStorage.getItem(DB_KEYS.MEMBERS)) || []);
    setIssued(JSON.parse(localStorage.getItem(DB_KEYS.ISSUED)) || []);

    const loggedUser = localStorage.getItem(DB_KEYS.CURRENT_USER);
    if (loggedUser) {
      setCurrentUser(JSON.parse(loggedUser));
    } else {
      setCurrentPage("login");
    }

    const savedTheme = localStorage.getItem(DB_KEYS.THEME) || "light";
    setTheme(savedTheme);
    document.body.setAttribute("data-theme", savedTheme);
  }, []);

  const toggleTheme = () => {
    const nextTheme = theme === "light" ? "dark" : "light";
    setTheme(nextTheme);
    localStorage.setItem(DB_KEYS.THEME, nextTheme);
    document.body.setAttribute("data-theme", nextTheme);
  };

  const handleLogin = (user) => {
    localStorage.setItem(DB_KEYS.CURRENT_USER, JSON.stringify(user));
    setCurrentUser(user);
    setCurrentPage("dashboard");
  };

  const handleLogout = () => {
    localStorage.removeItem(DB_KEYS.CURRENT_USER);
    setCurrentUser(null);
    setCurrentPage("login");
  };

  // State updaters to sync memory back to localStorage sync points
  const updateBooksState = (newBooks) => {
    setBooks(newBooks);
    localStorage.setItem(DB_KEYS.BOOKS, JSON.stringify(newBooks));
  };

  const updateMembersState = (newMembers) => {
    setMembers(newMembers);
    localStorage.setItem(DB_KEYS.MEMBERS, JSON.stringify(newMembers));
  };

  const updateIssuedState = (newIssued) => {
    setIssued(newIssued);
    localStorage.setItem(DB_KEYS.ISSUED, JSON.stringify(newIssued));
  };

  if (!currentUser && currentPage !== "login") {
    return (
      <div style={{ padding: "2rem", textAlign: "center" }}>
        Loading Session...
      </div>
    );
  }

  return (
    <div className="app-container">
      {currentPage !== "login" && (
        <Sidebar
          currentPage={currentPage}
          setCurrentPage={setCurrentPage}
          onLogout={handleLogout}
        />
      )}

      <div style={{ width: "100%" }}>
        {currentPage !== "login" && (
          <TopNavbar
            theme={theme}
            toggleTheme={toggleTheme}
            user={currentUser}
            globalSearch={globalSearch}
            setGlobalSearch={setGlobalSearch}
            setCurrentPage={setCurrentPage}
          />
        )}

        <main className={currentPage !== "login" ? "main-content" : ""}>
          {currentPage === "login" && (
            <onLogin>
              <Login onLogin={handleLogin} DB_KEYS={DB_KEYS} />
            </onLogin>
          )}
          {currentPage === "dashboard" && (
            <Dashboard books={books} issued={issued} />
          )}
          {currentPage === "books" && (
            <BooksManager
              books={books}
              updateBooks={updateBooksState}
              setCurrentPage={setCurrentPage}
              setEditBookId={setEditBookId}
              globalSearch={globalSearch}
            />
          )}
          {currentPage === "add-book" && (
            <AddBook
              books={books}
              updateBooks={updateBooksState}
              editBookId={editBookId}
              setEditBookId={setEditBookId}
              setCurrentPage={setCurrentPage}
            />
          )}
          {currentPage === "issued" && (
            <CirculationDesk
              books={books}
              issued={issued}
              members={members}
              updateBooks={updateBooksState}
              updateIssued={updateIssuedState}
            />
          )}
          {currentPage === "members" && (
            <Members members={members} updateMembers={updateMembersState} />
          )}
          {currentPage === "profile" && (
            <Profile
              currentUser={currentUser}
              setCurrentUser={setCurrentUser}
              DB_KEYS={DB_KEYS}
            />
          )}
        </main>
      </div>
    </div>
  );
}
