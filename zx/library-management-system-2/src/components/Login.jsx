import { useState } from "react";
import { useLibrary } from "../context/LibraryContext";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const { login } = useLibrary();
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();
    if (login(email, password)) {
      navigate("/");
    } else {
      alert("Invalid credentials! Try admin@library.com / password123");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gradient-to-br from-bg-light to-[#e0eAFC] p-8">
      <div className="flex w-full max-w-4xl h-[600px] bg-bg-white rounded-[24px] shadow-lg overflow-hidden animate-fade-in">
        {/* Banner Side */}
        <div className="hidden md:flex flex-1 flex-col justify-center items-center p-12 text-center text-white bg-gradient-to-br from-primary to-secondary relative overflow-hidden">
          <div className="absolute w-[300px] h-[300px] bg-white/10 rounded-full -top-12 -left-12"></div>
          <div className="absolute w-[200px] h-[200px] bg-white/10 rounded-full -bottom-8 -right-8"></div>
          <h1 className="text-4xl font-bold mb-4 z-10">LibMaster</h1>
          <p className="text-lg opacity-90 z-10 leading-relaxed">
            Manage your library with style and efficiency. Access thousands of
            books, track members, and streamline operations.
          </p>
        </div>

        {/* Form Side */}
        <div className="flex-1 flex flex-col justify-center p-12 relative">
          <form onSubmit={handleLogin}>
            <div className="mb-8">
              <h2 className="text-3xl text-primary mb-2">Welcome Back</h2>
              <p className="text-text-muted">
                Please enter your details to sign in.
              </p>
            </div>

            <div className="mb-6">
              <label className="block font-medium mb-2">Email</label>
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="w-full p-3 rounded-lg border border-border-color bg-bg-light focus:border-primary focus:ring-3 focus:ring-primary/10 outline-none transition-all"
                placeholder="admin@library.com"
                required
              />
            </div>

            <div className="mb-8">
              <label className="block font-medium mb-2">Password</label>
              <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="w-full p-3 rounded-lg border border-border-color bg-bg-light focus:border-primary focus:ring-3 focus:ring-primary/10 outline-none transition-all"
                placeholder="••••••••"
                required
              />
            </div>

            <button
              type="submit"
              className="w-full flex justify-center items-center gap-2 bg-primary text-white p-3 rounded-lg font-medium hover:bg-[#5b54e0] shadow-md transition-all"
            >
              Sign In <i className="fas fa-arrow-right"></i>
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}
