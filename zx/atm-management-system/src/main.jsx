import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App.jsx";
import { ATMProvider } from "./context/ATMContext.jsx";
import "./index.css"; // Default vite styles, you can leave it empty or delete the import

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <ATMProvider>
      <App />
    </ATMProvider>
  </React.StrictMode>,
);
