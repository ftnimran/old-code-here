import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { useLibrary } from "./context/LibraryContext";

import Sidebar from "./components/Sidebar";
import Header from "./components/Header";
import Dashboard from "./components/Dashboard";
import Login from "./components/Login";
import Books from "./components/Books";
import Members from "./components/Members";
import Issued from "./components/Issued";
import Profile from "./components/Profile";
import AddBook from "./components/AddBook";
import EditBook from "./components/EditBook";

const ProtectedRoute = ({ children }) => {
  const { user } = useLibrary();
  if (!user) return <Navigate to="/login" replace />;

  return (
    <div className="flex min-h-screen relative">
      <Sidebar />
      {/* Changed md:ml-[260px] to lg:ml-[260px] for Tablet support */}
      <div className="flex-1 w-full lg:ml-[260px] p-4 sm:p-8 pt-[90px] sm:pt-[102px] transition-all duration-300">
        <Header />
        {children}
      </div>
    </div>
  );
};

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login />} />

        <Route
          path="/"
          element={
            <ProtectedRoute>
              <Dashboard />
            </ProtectedRoute>
          }
        />
        <Route
          path="/books"
          element={
            <ProtectedRoute>
              <Books />
            </ProtectedRoute>
          }
        />
        <Route
          path="/add-book"
          element={
            <ProtectedRoute>
              <AddBook />
            </ProtectedRoute>
          }
        />
        <Route
          path="/edit-book/:id"
          element={
            <ProtectedRoute>
              <EditBook />
            </ProtectedRoute>
          }
        />
        <Route
          path="/issued"
          element={
            <ProtectedRoute>
              <Issued />
            </ProtectedRoute>
          }
        />
        <Route
          path="/members"
          element={
            <ProtectedRoute>
              <Members />
            </ProtectedRoute>
          }
        />
        <Route
          path="/profile"
          element={
            <ProtectedRoute>
              <Profile />
            </ProtectedRoute>
          }
        />
      </Routes>
    </BrowserRouter>
  );
}
