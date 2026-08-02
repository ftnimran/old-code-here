import { useState } from "react";
import { useLibrary } from "../../context/LibraryContext";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const [isLoginMode, setIsLoginMode] = useState(true);
  const [showPassword, setShowPassword] = useState(false);

  const [identifier, setIdentifier] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");

  const { login, signup, showAlert } = useLibrary();
  const navigate = useNavigate();

  // Smooth typing without cursor jumping. Phone only accepts numbers.
  const handlePhoneChange = (e) => {
    const value = e.target.value.replace(/\D/g, ""); // Remove non-numeric characters
    setPhone(value);
  };

  // YAHAN PAR 'async' ADD KIYA GAYA HAI 👇
  const handleSubmit = async (e) => {
    e.preventDefault();

    if (isLoginMode) {
      // YAHAN PAR 'await' ADD KIYA GAYA HAI 👇
      const res = await login(identifier, password);
      if (res.success) {
        navigate("/");
      } else {
        showAlert(res.message, "Login Failed", "error");
      }
    } else {
      // Validations on Submit for better User Experience
      if (!/^[a-zA-Z\s]+$/.test(name)) {
        showAlert(
          "Name can only contain letters and spaces.",
          "Invalid Name",
          "warning",
        );
        return;
      }

      if (!/^[a-z0-9_-]+$/.test(username.toLowerCase())) {
        showAlert(
          "Username can only contain letters, numbers, underscores (_), and hyphens (-).",
          "Invalid Username",
          "warning",
        );
        return;
      }

      if (phone.length < 10) {
        showAlert(
          "Mobile Number must be at least 10 digits.",
          "Invalid Input",
          "warning",
        );
        return;
      }

      const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*@)/;
      if (!passwordRegex.test(password)) {
        showAlert(
          "Password must contain at least one letter, one number, and the '@' symbol.",
          "Weak Password",
          "warning",
        );
        return;
      }

      // Automatically convert username to lowercase before saving
      const finalUsername = username.toLowerCase();

      // YAHAN PAR 'await' ADD KIYA GAYA HAI 👇
      const res = await signup({
        name,
        username: finalUsername,
        email,
        phone,
        password,
      });
      if (res.success) {
        navigate("/");
      } else {
        showAlert(res.message, "Signup Failed", "error");
      }
    }
  };

  const toggleMode = () => {
    setIsLoginMode(!isLoginMode);
    setShowPassword(false);
    setIdentifier("");
    setPassword("");
    setName("");
    setUsername("");
    setEmail("");
    setPhone("");
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gradient-to-br from-bg-light to-[#e0eAFC] p-4 sm:p-8">
      <div className="flex w-full max-w-4xl min-h-[600px] h-fit bg-bg-white rounded-2xl shadow-xl border border-border-color overflow-hidden animate-fade-in">
        {/* Left Side Branding */}
        <div className="hidden md:flex flex-1 flex-col justify-center items-center p-12 text-center text-white bg-gradient-to-br from-primary to-secondary relative overflow-hidden">
          <div className="absolute w-[300px] h-[300px] bg-white/10 rounded-full -top-12 -left-12"></div>
          <div className="absolute w-[200px] h-[200px] bg-white/10 rounded-full -bottom-8 -right-8"></div>
          <i className="fas fa-book-reader text-6xl mb-6 z-10 drop-shadow-md"></i>
          <h1 className="text-4xl font-bold mb-4 z-10 tracking-wide">
            LibMaster
          </h1>
          <p className="text-lg opacity-90 z-10 leading-relaxed font-medium">
            Manage your library with style and efficiency.
          </p>
        </div>

        {/* Right Side Form */}
        <div className="flex-[1.2] flex flex-col justify-center p-8 sm:p-12 relative overflow-y-auto">
          <form onSubmit={handleSubmit} className="w-full max-w-sm mx-auto">
            <div className="mb-8">
              <h2 className="text-3xl font-bold text-text-dark mb-2">
                {isLoginMode ? "Welcome Back" : "Create Account"}
              </h2>
              <p className="text-text-muted">
                {isLoginMode
                  ? "Please enter your details to sign in."
                  : "Fill in the form below to register as a Student."}
              </p>
            </div>

            {!isLoginMode && (
              <>
                <div className="mb-5">
                  <label className="block font-medium mb-1.5 text-sm text-text-dark">
                    Full Name
                  </label>
                  <input
                    type="text"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    className="w-full p-3 rounded-lg border border-border-color bg-bg-light outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark"
                    placeholder="John Doe"
                    required
                  />
                </div>
                <div className="mb-5">
                  <label className="block font-medium mb-1.5 text-sm text-text-dark">
                    Username
                  </label>
                  <input
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    className="w-full p-3 rounded-lg border border-border-color bg-bg-light outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark"
                    placeholder="johndoe_123"
                    required
                  />
                </div>
                <div className="mb-5">
                  <label className="block font-medium mb-1.5 text-sm text-text-dark">
                    Email Address
                  </label>
                  <input
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    className="w-full p-3 rounded-lg border border-border-color bg-bg-light outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark"
                    placeholder="john@example.com"
                    required
                  />
                </div>
                <div className="mb-5">
                  <label className="block font-medium mb-1.5 text-sm text-text-dark">
                    Mobile Number
                  </label>
                  <input
                    type="tel"
                    value={phone}
                    onChange={handlePhoneChange}
                    maxLength="10"
                    className="w-full p-3 rounded-lg border border-border-color bg-bg-light outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark"
                    placeholder="9876543210"
                    required
                  />
                </div>
              </>
            )}

            {isLoginMode && (
              <div className="mb-5">
                <label className="block font-medium mb-1.5 text-sm text-text-dark">
                  Email or Username
                </label>
                <input
                  type="text"
                  value={identifier}
                  onChange={(e) => setIdentifier(e.target.value)}
                  className="w-full p-3 rounded-lg border border-border-color bg-bg-light outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark"
                  placeholder="admin@library.com or admin"
                  required
                />
              </div>
            )}

            <div className="mb-8">
              <label className="block font-medium mb-1.5 text-sm text-text-dark">
                Password
              </label>
              <div className="relative">
                <input
                  type={showPassword ? "text" : "password"}
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  className="w-full p-3 pr-10 rounded-lg border border-border-color bg-bg-light outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark"
                  placeholder={
                    isLoginMode ? "••••••••" : "Text, number and @ only"
                  }
                  required
                />
                <button
                  type="button"
                  onClick={() => setShowPassword(!showPassword)}
                  className="absolute right-3 top-1/2 -translate-y-1/2 text-text-muted hover:text-primary transition-colors cursor-pointer"
                >
                  <i
                    className={`fas ${showPassword ? "fa-eye-slash" : "fa-eye"}`}
                  ></i>
                </button>
              </div>

              {isLoginMode && (
                <div className="flex justify-end mt-2">
                  <button
                    type="button"
                    onClick={() =>
                      identifier
                        ? showAlert(
                            `Password reset link sent to '${identifier}'.`,
                            "Reset Link Sent",
                            "success",
                          )
                        : showAlert(
                            "Enter email/username first.",
                            "Missing Information",
                            "warning",
                          )
                    }
                    className="text-sm text-primary font-medium hover:underline cursor-pointer transition-all"
                  >
                    Forgot Password?
                  </button>
                </div>
              )}
            </div>

            <button
              type="submit"
              className="w-full flex justify-center items-center gap-2 bg-primary text-white p-3 rounded-lg font-medium hover:bg-[#5b54e0] shadow-md transition-all cursor-pointer"
            >
              {isLoginMode ? "Sign In" : "Sign Up"}{" "}
              <i className="fas fa-arrow-right"></i>
            </button>
          </form>

          <div className="mt-8 text-center text-sm">
            <span className="text-text-muted">
              {isLoginMode
                ? "Don't have an account? "
                : "Already have an account? "}
            </span>
            <button
              onClick={toggleMode}
              className="text-primary font-bold hover:underline cursor-pointer transition-all"
            >
              {isLoginMode ? "Sign Up" : "Sign In"}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
