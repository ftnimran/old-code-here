import React from "react";
import {
  Chart as ChartJS,
  ArcElement,
  Tooltip,
  Legend,
  CategoryScale,
  LinearScale,
  BarElement,
} from "chart.js";
import { Doughnut, Bar } from "react-chartjs-2";

ChartJS.register(
  ArcElement,
  Tooltip,
  Legend,
  CategoryScale,
  LinearScale,
  BarElement,
);

export default function Dashboard({ books, issued }) {
  // Math Analytics engine
  const totalBooks = books.reduce(
    (acc, b) => acc + parseInt(b.quantity || 0),
    0,
  );
  const availableBooks = books.reduce(
    (acc, b) => acc + parseInt(b.available || 0),
    0,
  );
  const activeIssued = issued.filter((i) => i.status === "Issued").length;

  // Build Category breakdown map
  const categoryMap = {};
  books.forEach((b) => {
    categoryMap[b.category] = (categoryMap[b.category] || 0) + 1;
  });

  const doughnutData = {
    labels: Object.keys(categoryMap),
    datasets: [
      {
        data: Object.values(categoryMap),
        backgroundColor: [
          "#6C63FF",
          "#00BFA6",
          "#FFC107",
          "#FF5252",
          "#3498db",
        ],
        borderWidth: 0,
      },
    ],
  };

  const barData = {
    labels: ["Available", "Issued"],
    datasets: [
      {
        label: "Books Status",
        data: [availableBooks, activeIssued],
        backgroundColor: ["#00BFA6", "#FF5252"],
        borderRadius: 8,
      },
    ],
  };

  return (
    <div style={{ display: "flex", flexDirection: "column", gap: "2rem" }}>
      <div>
        <h1>Dashboard</h1>
        <p style={{ color: "var(--text-muted)" }}>
          Welcome to LibMaster Overview center.
        </p>
      </div>

      {/* Top Cards row */}
      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(auto-fit, minmax(220px, 1fr))",
          gap: "1.5rem",
        }}
      >
        <div
          className="card"
          style={{ display: "flex", alignItems: "center", gap: "1rem" }}
        >
          <div
            style={{
              background: "rgba(108, 99, 255, 0.1)",
              padding: "1rem",
              borderRadius: "50%",
              color: "var(--primary)",
            }}
          >
            <i className="fas fa-book-open fa-2x"></i>
          </div>
          <div>
            <h3 style={{ fontSize: "1.8rem" }}>{totalBooks}</h3>
            <p style={{ color: "var(--text-muted)" }}>Total Inventory Copies</p>
          </div>
        </div>
        <div
          className="card"
          style={{ display: "flex", alignItems: "center", gap: "1rem" }}
        >
          <div
            style={{
              background: "rgba(0, 191, 166, 0.1)",
              padding: "1rem",
              borderRadius: "50%",
              color: "var(--secondary)",
            }}
          >
            <i className="fas fa-check-circle fa-2x"></i>
          </div>
          <div>
            <h3 style={{ fontSize: "1.8rem" }}>{availableBooks}</h3>
            <p style={{ color: "var(--text-muted)" }}>Available Copies</p>
          </div>
        </div>
        <div
          className="card"
          style={{ display: "flex", alignItems: "center", gap: "1rem" }}
        >
          <div
            style={{
              background: "rgba(255, 82, 82, 0.1)",
              padding: "1rem",
              borderRadius: "50%",
              color: "var(--danger)",
            }}
          >
            <i className="fas fa-bookmark fa-2x"></i>
          </div>
          <div>
            <h3 style={{ fontSize: "1.8rem" }}>{activeIssued}</h3>
            <p style={{ color: "var(--text-muted)" }}>Currently On Loan</p>
          </div>
        </div>
      </div>

      {/* Analytics Section */}
      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(auto-fit, minmax(350px, 1fr))",
          gap: "2rem",
        }}
      >
        <div className="card">
          <h3 style={{ marginBottom: "1rem" }}>Books by Category</h3>
          <div
            style={{
              maxHeight: "250px",
              display: "flex",
              justifyContent: "center",
            }}
          >
            {Object.keys(categoryMap).length > 0 ? (
              <Doughnut
                data={doughnutData}
                options={{
                  responsive: true,
                  plugins: { legend: { position: "bottom" } },
                }}
              />
            ) : (
              <p style={{ color: "var(--text-muted)" }}>
                No Categories Available
              </p>
            )}
          </div>
        </div>
        <div className="card">
          <h3 style={{ marginBottom: "1rem" }}>Circulation Status Summary</h3>
          <div style={{ maxHeight: "250px" }}>
            <Bar
              data={barData}
              options={{
                responsive: true,
                plugins: { legend: { display: false } },
              }}
            />
          </div>
        </div>
      </div>
    </div>
  );
}
