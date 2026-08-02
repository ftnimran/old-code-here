import React, { useState } from "react";

export default function Login({ onLogin, DB_KEYS }) {
  const [isSignUp, setIsSignUp] = useState(false);
  const [loginEmail, setLoginEmail] = useState("");
  const [loginPassword, setLoginPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  // Signup specific fields
  const [regName, setRegName] = useState("");
  const [regEmail, setRegEmail] = useState("");
  const [regPassword, setRegPassword] = useState("");

  const handleSignInSubmit = (e) => {
    e.preventDefault();
    const users = JSON.parse(localStorage.getItem(DB_KEYS.USERS)) || [];
    const matched = users.find(
      (u) => u.email === loginEmail && u.password === loginPassword,
    );

    if (matched) {
      onLogin(matched);
    } else {
      alert("Invalid credentials!");
    }
  };

  const handleSignUpSubmit = (e) => {
    e.preventDefault();
    const users = JSON.parse(localStorage.getItem(DB_KEYS.USERS)) || [];
    if (users.find((u) => u.email === regEmail)) {
      alert("User already exists!");
      return;
    }
    const newUser = {
      id: "U" + Date.now(),
      name: regName,
      email: regEmail,
      password: regPassword,
      role: "student",
    };
    users.push(newUser);
    localStorage.setItem(DB_KEYS.USERS, JSON.stringify(users));
    alert("Account created! Please login.");
    setIsSignUp(false);
  };

  return (
    <div
      style={{
        background: "linear-gradient(135deg, #F5F7FB 0%, #e0eAFC 100%)",
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        height: "100vh",
        width: "100vw",
        padding: "2rem",
      }}
    >
      <div
        className="auth-container"
        style={{
          width: "100%",
          maxWidth: "900px",
          height: "600px",
          display: "flex",
          background: "var(--bg-white)",
          borderRadius: "var(--radius-lg)",
          boxShadow: "var(--shadow-lg)",
          overflow: "hidden",
        }}
      >
        {/* Banner Side */}
        <div
          className="auth-banner"
          style={{
            flex: 1,
            background:
              "linear-gradient(135deg, var(--primary) 0%, var(--secondary) 100%)",
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
            alignItems: "center",
            color: "white",
            padding: "3rem",
            textAlign: "center",
          }}
        >
          <h1
            style={{ color: "white", fontSize: "2.5rem", marginBottom: "1rem" }}
          >
            LibMaster
          </h1>
          <p style={{ opacity: 0.9, lineHeight: 1.6 }}>
            Your next-generation interactive gateway for exploring library
            management resources smoothly.
          </p>
        </div>

        {/* Forms Controller Side */}
        <div
          className="auth-form-wrapper"
          style={{
            flex: 1,
            padding: "3rem",
            display: "flex",
            flexDirection: "column",
            justifyContent: "center",
          }}
        >
          {!isSignUp ? (
            <form className="auth-form" onSubmit={handleSignInSubmit}>
              <div className="form-header" style={{ marginBottom: "2rem" }}>
                <h2>Welcome Back</h2>
                <p style={{ color: "var(--text-muted)" }}>
                  Login to keep track of catalog access.
                </p>
              </div>
              <div className="form-group">
                <label className="form-label">Email Address</label>
                <input
                  type="email"
                  className="form-control"
                  required
                  value={loginEmail}
                  onChange={(e) => setLoginEmail(e.target.value)}
                />
              </div>
              <div className="form-group" style={{ position: "relative" }}>
                <label className="form-label">Password</label>
                <input
                  type={showPassword ? "text" : "password"}
                  className="form-control"
                  required
                  value={loginPassword}
                  onChange={(e) => setLoginPassword(e.target.value)}
                />
                <i
                  className={`fas ${showPassword ? "fa-eye-slash" : "fa-eye"}`}
                  onClick={() => setShowPassword(!showPassword)}
                  style={{
                    position: "absolute",
                    right: "15px",
                    bottom: "15px",
                    cursor: "pointer",
                    color: "var(--text-muted)",
                  }}
                ></i>
              </div>
              <button
                type="submit"
                className="btn btn-primary"
                style={{
                  width: "100%",
                  justifyContent: "center",
                  marginTop: "1rem",
                }}
              >
                Sign In
              </button>
              <p
                style={{
                  textAlign: "center",
                  marginTop: "1.5rem",
                  color: "var(--text-muted)",
                }}
              >
                New here?{" "}
                <span
                  style={{
                    color: "var(--primary)",
                    cursor: "pointer",
                    fontWeight: "600",
                  }}
                  onClick={() => setIsSignUp(true)}
                >
                  Create Account
                </span>
              </p>
            </form>
          ) : (
            <form className="auth-form" onSubmit={handleSignUpSubmit}>
              <div className="form-header" style={{ marginBottom: "2rem" }}>
                <h2>Create Account</h2>
                <p style={{ color: "var(--text-muted)" }}>
                  Join now to request physical inventory allocations.
                </p>
              </div>
              <div className="form-group">
                <label className="form-label">Full Name</label>
                <input
                  type="text"
                  className="form-control"
                  required
                  value={regName}
                  onChange={(e) => setRegName(e.target.value)}
                />
              </div>
              <div className="form-group">
                <label className="form-label">Email Address</label>
                <input
                  type="email"
                  className="form-control"
                  required
                  value={regEmail}
                  onChange={(e) => setRegEmail(e.target.value)}
                />
              </div>
              <div className="form-group">
                <label className="form-label">Password</label>
                <input
                  type="password"
                  className="form-control"
                  required
                  value={regPassword}
                  onChange={(e) => setRegPassword(e.target.value)}
                />
              </div>
              <button
                type="submit"
                className="btn btn-primary"
                style={{
                  width: "100%",
                  justifyContent: "center",
                  marginTop: "1rem",
                }}
              >
                Sign Up
              </button>
              <p
                style={{
                  textAlign: "center",
                  marginTop: "1.5rem",
                  color: "var(--text-muted)",
                }}
              >
                Already registered?{" "}
                <span
                  style={{
                    color: "var(--primary)",
                    cursor: "pointer",
                    fontWeight: "600",
                  }}
                  onClick={() => setIsSignUp(false)}
                >
                  Login Here
                </span>
              </p>
            </form>
          )}
        </div>
      </div>
    </div>
  );
}
