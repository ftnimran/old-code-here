// frontend/api/axios.js
import axios from "axios";

const api = axios.create({
  // Ye zaroori hai! VITE_BACKEND_URL hum Vercel me set karenge
  baseURL: `${import.meta.env.VITE_BACKEND_URL}/api/v1`,
});

// Har request me JWT token attach karne ke liye interceptor
api.interceptors.request.use((config) => {
  const token = localStorage.getItem("lib_token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;
