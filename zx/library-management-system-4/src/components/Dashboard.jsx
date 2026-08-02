import { useState, useEffect, useRef, useMemo } from "react";
import { useLibrary } from "../context/LibraryContext";
import { Link } from "react-router-dom";
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

// IMPORT REACT-PDF COMPONENTS & REQUIRED CSS
import { Document, Page, pdfjs } from "react-pdf";
import "react-pdf/dist/Page/TextLayer.css";
import "react-pdf/dist/Page/AnnotationLayer.css";

// SET PDF.JS WORKER URL
pdfjs.GlobalWorkerOptions.workerSrc = `https://unpkg.com/pdfjs-dist@${pdfjs.version}/build/pdf.worker.min.mjs`;

ChartJS.register(
  ArcElement,
  Tooltip,
  Legend,
  CategoryScale,
  LinearScale,
  BarElement,
);

// VIRTUALIZED PAGE COMPONENT
const VirtualPdfPage = ({
  pageNumber,
  zoomScale,
  setPageNumber,
  setInputPage,
}) => {
  const [isVisible, setIsVisible] = useState(false);
  const [originalSize, setOriginalSize] = useState({ width: 600, height: 848 });
  const containerRef = useRef(null);

  const BASE_SCALE = 1.5;
  const displayWidth = originalSize.width * zoomScale * BASE_SCALE;
  const displayHeight = originalSize.height * zoomScale * BASE_SCALE;

  useEffect(() => {
    if (!containerRef.current) return;

    const mountObserver = new IntersectionObserver(
      ([entry]) => setIsVisible(entry.isIntersecting),
      { rootMargin: "150% 0px" },
    );

    const activeObserver = new IntersectionObserver(
      ([entry]) => {
        if (entry.isIntersecting) {
          setPageNumber(pageNumber);
          setInputPage(pageNumber);
        }
      },
      { rootMargin: "-40% 0px -40% 0px" },
    );

    mountObserver.observe(containerRef.current);
    activeObserver.observe(containerRef.current);

    return () => {
      mountObserver.disconnect();
      activeObserver.disconnect();
    };
  }, [pageNumber, setPageNumber, setInputPage]);

  return (
    <div
      ref={containerRef}
      id={`pdf-page-${pageNumber}`}
      className="pdf-page-wrapper shadow-2xl bg-white relative mb-8 transition-all"
      style={{ minHeight: displayHeight, minWidth: displayWidth }}
    >
      {isVisible ? (
        <Page
          pageNumber={pageNumber}
          renderTextLayer={true}
          renderAnnotationLayer={false}
          scale={zoomScale * BASE_SCALE}
          onLoadSuccess={(page) => {
            setOriginalSize({
              width: page.originalWidth,
              height: page.originalHeight,
            });
          }}
        />
      ) : (
        <div className="w-full h-full flex items-center justify-center text-text-muted bg-bg-light/30">
          <i className="fa-solid fa-spinner fa-spin mr-2"></i> Page {pageNumber}
        </div>
      )}
    </div>
  );
};

export default function Dashboard() {
  const { user, books = [], members = [], issued = [] } = useLibrary();
  const today = new Date().toISOString().split("T")[0];
  const isAdmin = user?.role === "admin";

  const [pdfViewerOpen, setPdfViewerOpen] = useState(false);
  const [currentPdf, setCurrentPdf] = useState(null);

  const [isFullScreen, setIsFullScreen] = useState(false);
  const [zoomScale, setZoomScale] = useState(1.0);

  const [numPages, setNumPages] = useState(null);
  const [pageNumber, setPageNumber] = useState(1);
  const [inputPage, setInputPage] = useState(1);

  const onDocumentLoadSuccess = ({ numPages }) => {
    setNumPages(numPages);
    setPageNumber(1);
    setInputPage(1);
  };

  const openPdf = (pdfBase64) => {
    setCurrentPdf(pdfBase64);
    setPdfViewerOpen(true);
    setIsFullScreen(false);
    setZoomScale(1.0);
    setPageNumber(1);
    setInputPage(1);
  };

  const handlePageInput = (e) => setInputPage(e.target.value);

  const handlePageSubmit = (e) => {
    if (e.key === "Enter" || e.type === "blur") {
      let p = parseInt(inputPage);
      if (isNaN(p) || p < 1) p = 1;
      if (p > numPages) p = numPages;
      setInputPage(p);
      if (p !== pageNumber) scrollToPage(p);
    }
  };

  const scrollToPage = (page) => {
    setPageNumber(page);
    setInputPage(page);
    const pageElement = document.getElementById(`pdf-page-${page}`);
    if (pageElement)
      pageElement.scrollIntoView({ behavior: "smooth", block: "start" });
  };

  const handlePrev = () => {
    const newPage = Math.max(pageNumber - 1, 1);
    scrollToPage(newPage);
  };
  const handleNext = () => {
    const newPage = Math.min(pageNumber + 1, numPages);
    scrollToPage(newPage);
  };

  const toggleFullScreen = () => setIsFullScreen(!isFullScreen);

  const handleZoomIn = () => setZoomScale((prev) => Math.min(prev + 0.1, 3.0));
  const handleZoomOut = () => setZoomScale((prev) => Math.max(prev - 0.1, 0.5));

  const memoizedPdfPages = useMemo(() => {
    if (!numPages) return null;
    return Array.from(new Array(numPages), (el, index) => (
      <VirtualPdfPage
        key={`page_${index + 1}`}
        pageNumber={index + 1}
        zoomScale={zoomScale}
        setPageNumber={setPageNumber}
        setInputPage={setInputPage}
      />
    ));
  }, [numPages, zoomScale]);

  if (!isAdmin) {
    const currentMember = members.find((m) => m.email === user?.email);
    const myTransactions = issued
      .filter((tx) => tx.memberId === currentMember?.id)
      .sort((a, b) => new Date(b.issueDate) - new Date(a.issueDate));
    const activeTransactions = myTransactions.filter(
      (tx) => tx.status === "Issued",
    );

    const currentlyReading = activeTransactions.length;
    const overdueCount = activeTransactions.filter(
      (tx) => tx.returnDate < today,
    ).length;
    const totalBorrowed = myTransactions.length;

    return (
      <div className="animate-fade-in relative">
        <style>{`
          .react-pdf__Page__textContent { user-select: text !important; cursor: text !important; }
          .react-pdf__Page__textContent > span { color: transparent !important; line-height: 1 !important; }
          .react-pdf__Page__textContent ::selection { background: rgba(0, 123, 255, 0.25) !important; color: transparent !important; }
        `}</style>

        <div className="mb-8">
          <h1 className="text-3xl font-bold text-text-dark">
            Hello, {(user?.name || "Student").split(" ")[0]}! 👋
          </h1>
          <p className="text-text-muted mt-1">
            Here is the overview of your library activity.
          </p>
        </div>

        <div className="grid grid-cols-1 sm:grid-cols-3 gap-6 mb-8">
          <div className="bg-bg-white p-6 sm:p-8 rounded-2xl shadow-sm border border-border-color flex items-center gap-5 hover:-translate-y-1 transition-all duration-300">
            <div className="w-14 h-14 rounded-full flex items-center justify-center text-white text-xl bg-gradient-to-br from-[#00BFA6] to-[#4ae0c8]">
              <i className="fas fa-book-open"></i>
            </div>
            <div>
              <h3 className="text-2xl font-bold text-text-dark">
                {currentlyReading}
              </h3>
              <p className="text-sm text-text-muted">Currently Reading</p>
            </div>
          </div>
          <div className="bg-bg-white p-6 sm:p-8 rounded-2xl shadow-sm border border-border-color flex items-center gap-5 hover:-translate-y-1 transition-all duration-300">
            <div className="w-14 h-14 rounded-full flex items-center justify-center text-white text-xl bg-gradient-to-br from-[#FF5252] to-[#ff8a80]">
              <i className="fas fa-clock"></i>
            </div>
            <div>
              <h3 className="text-2xl font-bold text-text-dark">
                {overdueCount}
              </h3>
              <p className="text-sm text-text-muted">Overdue Books</p>
            </div>
          </div>
          <div className="bg-bg-white p-6 sm:p-8 rounded-2xl shadow-sm border border-border-color flex items-center gap-5 hover:-translate-y-1 transition-all duration-300">
            <div className="w-14 h-14 rounded-full flex items-center justify-center text-white text-xl bg-gradient-to-br from-[#6C63FF] to-[#8f88ff]">
              <i className="fas fa-layer-group"></i>
            </div>
            <div>
              <h3 className="text-2xl font-bold text-text-dark">
                {totalBorrowed}
              </h3>
              <p className="text-sm text-text-muted">Total Borrowed</p>
            </div>
          </div>
        </div>

        <div className="bg-bg-white rounded-2xl shadow-sm border border-border-color overflow-hidden">
          <div className="p-6 border-b border-border-color bg-primary/5 flex justify-between items-center gap-4">
            <h3 className="text-xl font-bold text-text-dark">
              My Issued Books
            </h3>
            <Link
              to="/books"
              className="text-sm text-primary font-semibold hover:underline"
            >
              Browse Books <i className="fas fa-arrow-right ml-1"></i>
            </Link>
          </div>
          <div className="p-6">
            {activeTransactions.length === 0 ? (
              <div className="text-center py-10">
                <i className="fas fa-check-circle text-5xl text-success opacity-80 mb-3"></i>
                <h4 className="text-lg font-medium text-text-dark">
                  All Clear!
                </h4>
                <p className="text-text-muted text-sm mt-1">
                  You don't have any pending books to return.
                </p>
              </div>
            ) : (
              <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                {activeTransactions.map((tx) => {
                  const book = books.find((b) => b.id === tx.bookId) || {
                    title: "Unknown",
                    author: "",
                    cover: "",
                  };
                  const isOverdue = tx.returnDate < today;
                  const isDeactivated = currentMember?.status === "Deactivate";
                  const canRead = !isOverdue && !isDeactivated && book.pdf;

                  return (
                    <div
                      key={tx.id}
                      className="flex gap-4 p-4 border border-border-color rounded-xl hover:bg-black/5 transition-colors"
                    >
                      <div className="w-20 h-28 flex-shrink-0 bg-gray-200 rounded-md overflow-hidden border border-border-color">
                        <img
                          src={
                            book.cover ||
                            "https://via.placeholder.com/150x200?text=No+Cover"
                          }
                          alt={book.title}
                          className="w-full h-full object-cover"
                        />
                      </div>
                      <div className="flex flex-col flex-1 justify-center relative">
                        <h4 className="font-bold text-text-dark text-base line-clamp-2 leading-tight">
                          {book.title}
                        </h4>
                        <p className="text-xs text-text-muted mt-1">
                          {book.author} | {book.category || "General"}
                        </p>

                        <div className="mt-3 flex items-center gap-2">
                          <span
                            className={`px-2 py-0.5 rounded text-[10px] font-bold uppercase tracking-wider ${isOverdue ? "bg-danger/20 text-danger" : "bg-warning/20 text-warning"}`}
                          >
                            {isOverdue ? "Overdue" : "Issued"}
                          </span>
                        </div>
                        <p
                          className={`text-xs mt-2 font-medium ${isOverdue ? "text-danger" : "text-text-muted"}`}
                        >
                          <i className="far fa-calendar-alt mr-1"></i> Due:{" "}
                          {tx.returnDate}
                        </p>

                        <div className="mt-4">
                          <button
                            onClick={() => openPdf(book.pdf)}
                            disabled={!canRead}
                            className={`w-full py-1.5 rounded-lg text-xs font-bold transition-all flex items-center justify-center gap-2 ${canRead ? "bg-primary text-white hover:bg-[#5b54e0] cursor-pointer shadow-sm" : "bg-bg-light text-text-muted cursor-not-allowed border border-border-color"}`}
                          >
                            <i className="fa-solid fa-book-open"></i>{" "}
                            {isOverdue
                              ? "Return Book to Read"
                              : book.pdf
                                ? "View Book"
                                : "No PDF Available"}
                          </button>
                        </div>
                      </div>
                    </div>
                  );
                })}
              </div>
            )}
          </div>
        </div>

        {/* PDF Modal */}
        {pdfViewerOpen && (
          <div
            className={`fixed inset-0 z-50 flex items-center justify-center bg-black/80 backdrop-blur-sm animate-fade-in ${isFullScreen ? "p-0" : "p-4"}`}
          >
            <div
              className={`bg-bg-white flex flex-col shadow-2xl border border-border-color relative overflow-hidden transition-all duration-300 ${isFullScreen ? "w-screen h-screen rounded-none" : "w-full max-w-5xl h-[90vh] rounded-2xl"}`}
            >
              {/* Top Bar */}
              <div className="bg-bg-light p-4 flex flex-col xl:flex-row justify-between items-center gap-4 border-b border-border-color shadow-sm z-10">
                {/* Left: Title */}
                <h3 className="font-bold text-lg text-text-dark flex items-center gap-2">
                  <i className="fa-solid fa-file-pdf text-danger"></i> PDF
                  Viewer
                </h3>

                {/* Center: Pagination + Zoom Controls Merged */}
                {numPages && (
                  <div className="flex flex-wrap items-center justify-center gap-1 sm:gap-2 bg-bg-white px-2 sm:px-3 py-1.5 rounded-xl border border-border-color shadow-sm">
                    {/* Pagination Prev */}
                    <button
                      onClick={handlePrev}
                      disabled={pageNumber <= 1}
                      className="w-10 sm:w-auto flex items-center justify-center gap-1.5 text-text-muted hover:bg-primary/10 hover:text-primary px-2 sm:px-3 py-1.5 rounded-lg disabled:opacity-40 disabled:hover:bg-transparent disabled:cursor-not-allowed transition-all cursor-pointer font-bold text-sm"
                    >
                      <i className="fa-solid fa-chevron-left text-xs"></i>{" "}
                      <span className="hidden sm:inline">Prev</span>
                    </button>

                    {/* Pagination Input */}
                    <div className="text-sm font-bold text-text-dark border-x border-border-color px-2 sm:px-4 flex items-center justify-center">
                      Page
                      <input
                        type="number"
                        value={inputPage}
                        onChange={handlePageInput}
                        onKeyDown={handlePageSubmit}
                        onBlur={handlePageSubmit}
                        className="w-12 text-center mx-1 sm:mx-2 py-1 bg-bg-light border border-border-color rounded focus:outline-none focus:border-primary text-primary font-bold transition-all appearance-none [&::-webkit-inner-spin-button]:appearance-none [&::-webkit-outer-spin-button]:appearance-none"
                      />
                      of {numPages}
                    </div>

                    {/* Pagination Next */}
                    <button
                      onClick={handleNext}
                      disabled={pageNumber >= numPages}
                      className="w-10 sm:w-auto flex items-center justify-center gap-1.5 text-text-muted hover:bg-primary/10 hover:text-primary px-2 sm:px-3 py-1.5 rounded-lg disabled:opacity-40 disabled:hover:bg-transparent disabled:cursor-not-allowed transition-all cursor-pointer font-bold text-sm"
                    >
                      <span className="hidden sm:inline">Next</span>{" "}
                      <i className="fa-solid fa-chevron-right text-xs"></i>
                    </button>

                    {/* Divider Line */}
                    <div className="w-px h-6 bg-border-color mx-1 sm:mx-2 hidden sm:block"></div>

                    {/* Zoom Controls */}
                    <div className="flex items-center">
                      <button
                        onClick={handleZoomOut}
                        className="w-8 h-8 flex items-center justify-center text-text-muted hover:bg-primary/10 hover:text-primary rounded-lg transition-colors cursor-pointer"
                        title="Zoom Out"
                      >
                        <i className="fas fa-search-minus"></i>
                      </button>
                      <span className="text-xs font-bold text-text-dark w-10 sm:w-12 text-center select-none">
                        {Math.round(zoomScale * 100)}%
                      </span>
                      <button
                        onClick={handleZoomIn}
                        className="w-8 h-8 flex items-center justify-center text-text-muted hover:bg-primary/10 hover:text-primary rounded-lg transition-colors cursor-pointer"
                        title="Zoom In"
                      >
                        <i className="fas fa-search-plus"></i>
                      </button>
                    </div>
                  </div>
                )}

                {/* Right: Actions */}
                <div className="flex items-center gap-3">
                  <button
                    onClick={toggleFullScreen}
                    className="w-10 h-10 flex items-center justify-center bg-primary/10 text-primary rounded-xl hover:bg-primary hover:text-white transition-colors cursor-pointer"
                    title={isFullScreen ? "Exit Full Screen" : "Full Screen"}
                  >
                    <i
                      className={`fas ${isFullScreen ? "fa-compress" : "fa-expand"} text-lg`}
                    ></i>
                  </button>

                  <button
                    onClick={() => setPdfViewerOpen(false)}
                    className="w-10 h-10 flex items-center justify-center bg-danger/10 text-danger rounded-xl hover:bg-danger hover:text-white transition-colors cursor-pointer"
                    title="Close Viewer"
                  >
                    <i className="fas fa-times text-lg"></i>
                  </button>
                </div>
              </div>

              {/* Document Render Area */}
              <div
                id="pdf-scroll-container"
                className="flex-1 bg-[#525659] relative overflow-y-auto flex flex-col items-center p-6 custom-scrollbar gap-8 select-text"
              >
                <Document
                  file={currentPdf}
                  onLoadSuccess={onDocumentLoadSuccess}
                  className="flex flex-col items-center gap-8 w-full"
                  loading={
                    <div className="flex flex-col items-center justify-center mt-40 text-white opacity-80">
                      <i className="fa-solid fa-spinner fa-spin text-4xl mb-4"></i>
                      <span className="font-bold tracking-widest text-sm bg-black/50 px-4 py-2 rounded-lg">
                        LOADING PDF FILE...
                      </span>
                    </div>
                  }
                >
                  {memoizedPdfPages}
                </Document>
              </div>
            </div>
          </div>
        )}
      </div>
    );
  }

  // Admin Dashboard Component Data
  const totalBooks = books.length;
  const totalMembers = members.length;
  const issuedBooks = issued.filter((i) => i.status === "Issued").length;
  const overdueBooks = issued.filter(
    (i) => i.status === "Issued" && i.returnDate < today,
  ).length;

  const categories = books.reduce((acc, book) => {
    if (book.category) acc[book.category] = (acc[book.category] || 0) + 1;
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
        ],
        borderWidth: 0,
      },
    ],
  };
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

  return (
    <div className="animate-fade-in">
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-text-dark">
          Hello, {(user?.name || "Admin").split(" ")[0]}! 👋
        </h1>
        <p className="text-text-muted mt-1">
          Here is the overview of your library activity.
        </p>
      </div>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
        <div className="bg-bg-white p-6 sm:p-8 rounded-2xl shadow-sm border border-border-color flex items-center gap-5 hover:-translate-y-1 transition-all duration-300">
          <div className="w-14 h-14 flex-shrink-0 rounded-full flex items-center justify-center text-white text-xl bg-gradient-to-br from-[#6C63FF] to-[#8f88ff]">
            <i className="fas fa-book"></i>
          </div>
          <div>
            <h3 className="text-2xl font-bold text-text-dark">{totalBooks}</h3>
            <p className="text-sm text-text-muted">Total Books</p>
          </div>
        </div>
        <div className="bg-bg-white p-6 sm:p-8 rounded-2xl shadow-sm border border-border-color flex items-center gap-5 hover:-translate-y-1 transition-all duration-300">
          <div className="w-14 h-14 flex-shrink-0 rounded-full flex items-center justify-center text-white text-xl bg-gradient-to-br from-[#00BFA6] to-[#4ae0c8]">
            <i className="fas fa-users"></i>
          </div>
          <div>
            <h3 className="text-2xl font-bold text-text-dark">
              {totalMembers}
            </h3>
            <p className="text-sm text-text-muted">Total Members</p>
          </div>
        </div>
        <div className="bg-bg-white p-6 sm:p-8 rounded-2xl shadow-sm border border-border-color flex items-center gap-5 hover:-translate-y-1 transition-all duration-300">
          <div className="w-14 h-14 flex-shrink-0 rounded-full flex items-center justify-center text-white text-xl bg-gradient-to-br from-[#FFC107] to-[#ffe082]">
            <i className="fas fa-book-reader"></i>
          </div>
          <div>
            <h3 className="text-2xl font-bold text-text-dark">{issuedBooks}</h3>
            <p className="text-sm text-text-muted">Issued Books</p>
          </div>
        </div>
        <div className="bg-bg-white p-6 sm:p-8 rounded-2xl shadow-sm border border-border-color flex items-center gap-5 hover:-translate-y-1 transition-all duration-300">
          <div className="w-14 h-14 flex-shrink-0 rounded-full flex items-center justify-center text-white text-xl bg-gradient-to-br from-[#FF5252] to-[#ff8a80]">
            <i className="fas fa-clock"></i>
          </div>
          <div>
            <h3 className="text-2xl font-bold text-text-dark">
              {overdueBooks}
            </h3>
            <p className="text-sm text-text-muted">Overdue</p>
          </div>
        </div>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <div className="bg-bg-white p-6 sm:p-8 rounded-2xl shadow-sm border border-border-color">
          <h3 className="text-lg font-bold text-text-dark mb-6">
            Books by Category
          </h3>
          <div className="w-full max-w-[300px] mx-auto flex justify-center">
            <Doughnut
              data={doughnutData}
              options={{
                responsive: true,
                plugins: { legend: { position: "bottom" } },
              }}
            />
          </div>
        </div>
        <div className="bg-bg-white p-6 sm:p-8 rounded-2xl shadow-sm border border-border-color">
          <h3 className="text-lg font-bold text-text-dark mb-6">
            Issue vs Return
          </h3>
          <div className="w-full h-[300px] flex justify-center">
            <Bar
              data={barData}
              options={{
                responsive: true,
                maintainAspectRatio: false,
                plugins: { legend: { display: false } },
                scales: { y: { beginAtZero: true } },
              }}
            />
          </div>
        </div>
      </div>
    </div>
  );
}
