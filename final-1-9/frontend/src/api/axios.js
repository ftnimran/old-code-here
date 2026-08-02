import axios from "axios";

const backendURL = (
  import.meta.env.VITE_BACKEND_URL || "http://localhost:5000"
).replace(/\/$/, "");

const api = axios.create({
  baseURL: `${backendURL}/api/v1`,
});

api.interceptors.request.use((config) => {
  const token = localStorage.getItem("lib_token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      localStorage.removeItem("lib_token");
      localStorage.removeItem("lib_current_user");
      window.location.href = "/login";
    }
    return Promise.reject(error);
  },
);

export default api;
