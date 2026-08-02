import { useLibrary } from "../context/LibraryContext";
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

// Register ChartJS components
ChartJS.register(
  ArcElement,
  Tooltip,
  Legend,
  CategoryScale,
  LinearScale,
  BarElement,
);

export default function Dashboard() {
  const { books = [], members = [], issued = [] } = useLibrary();

  // Aaj ki date string format (YYYY-MM-DD) me get karne ke liye
  const today = new Date().toISOString().split("T")[0];

  // 1. Stats Calculation (FIXED Overdue Logic)
  const totalBooks = books.length;
  const totalMembers = members.length;
  const issuedBooks = issued.filter((i) => i.status === "Issued").length;

  // Is line ko fix kiya gaya hai: status 'Issued' ho aur returnDate nikal chuki ho
  const overdueBooks = issued.filter(
    (i) => i.status === "Issued" && i.returnDate < today,
  ).length;

  // 2. Doughnut Chart Data (Books by Category)
  const categories = books.reduce((acc, book) => {
    if (book.category) {
      acc[book.category] = (acc[book.category] || 0) + 1;
    }
    return acc;
  }, {});

  const doughnutData = {
    labels:
      Object.keys(categories).length > 0
        ? Object.keys(categories)
        : ["No Data"],
    datasets: [
      {
        data:
          Object.values(categories).length > 0
            ? Object.values(categories)
            : [1],
        backgroundColor: [
          "#6C63FF",
          "#00BFA6",
          "#FFC107",
          "#FF5252",
          "#3498db",
          "#FF69B4",
          "#155938",
          "#63FFE5",
          "#A3275D",
          "#272DA3",
        ],
        borderWidth: 0,
      },
    ],
  };

  // 3. Bar Chart Data (Availability vs Issued)
  const availableBooks = books.reduce(
    (acc, b) => acc + (parseInt(b.available) || 0),
    0,
  );

  const barData = {
    labels: ["Available", "Issued"],
    datasets: [
      {
        label: "Books Status",
        data: [availableBooks, issuedBooks],
        backgroundColor: ["#00BFA6", "#FF5252"],
        borderRadius: 8,
      },
    ],
  };

  // 4. Stat Cards Configuration
  const statCards = [
    {
      title: "Total Books",
      value: totalBooks,
      icon: "fa-book",
      gradient: "from-[#6C63FF] to-[#8f88ff]",
    },
    {
      title: "Total Members",
      value: totalMembers,
      icon: "fa-users",
      gradient: "from-[#00BFA6] to-[#4ae0c8]",
    },
    {
      title: "Issued Books",
      value: issuedBooks,
      icon: "fa-book-reader",
      gradient: "from-[#FFC107] to-[#ffe082]",
    },
    {
      title: "Overdue",
      value: overdueBooks,
      icon: "fa-clock",
      gradient: "from-[#FF5252] to-[#ff8a80]",
    },
  ];

  return (
    <div className="animate-fade-in">
      {/* Header Section */}
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-text-dark">Overview</h1>
        <p className="text-text-muted mt-1">
          Welcome back, here's what's happening today.
        </p>
      </div>

      {/* Stats Grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
        {statCards.map((stat, idx) => (
          <div
            key={idx}
            className="bg-bg-white p-6 rounded-2xl shadow-sm border border-border-color flex items-center gap-5 hover:-translate-y-1 hover:shadow-md transition-all duration-300"
          >
            <div
              className={`w-14 h-14 rounded-full flex items-center justify-center text-white text-xl bg-gradient-to-br ${stat.gradient}`}
            >
              <i className={`fas ${stat.icon}`}></i>
            </div>
            <div>
              <h3 className="text-2xl font-bold text-text-dark">
                {stat.value}
              </h3>
              <p className="text-sm text-text-muted">{stat.title}</p>
            </div>
          </div>
        ))}
      </div>

      {/* Charts Section */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        {/* Doughnut Chart */}
        <div className="bg-bg-white p-6 rounded-2xl shadow-sm border border-border-color">
          <h3 className="text-lg font-bold text-text-dark mb-6">
            Books by Category
          </h3>
          <div className="w-full max-w-[300px] mx-auto flex justify-center">
            <Doughnut
              data={doughnutData}
              options={{
                responsive: true,
                maintainAspectRatio: true,
                plugins: {
                  legend: { position: "bottom", labels: { padding: 20 } },
                },
              }}
            />
          </div>
        </div>

        {/* Bar Chart */}
        <div className="bg-bg-white p-6 rounded-2xl shadow-sm border border-border-color">
          <h3 className="text-lg font-bold text-text-dark mb-6">
            Issue vs Return
          </h3>
          <div className="w-full h-[300px] flex items-center justify-center">
            <Bar
              data={barData}
              options={{
                responsive: true,
                maintainAspectRatio: false,
                plugins: { legend: { display: false } },
                scales: {
                  y: { beginAtZero: true, grid: { color: "rgba(0,0,0,0.05)" } },
                  x: { grid: { display: false } },
                },
              }}
            />
          </div>
        </div>
      </div>
    </div>
  );
}
