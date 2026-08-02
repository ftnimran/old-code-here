import { useState, useEffect } from "react";
import { useLibrary } from "../context/LibraryContext";
import { useSearchParams, Link } from "react-router-dom";

export default function Books() {
  const { books, setBooks } = useLibrary();
  const [searchParams] = useSearchParams();

  // States for Filtering & Sorting
  const [search, setSearch] = useState("");
  const [category, setCategory] = useState("");
  const [sort, setSort] = useState("newest");

  // Header se aane wali query ko URL params se read karna
  useEffect(() => {
    const query = searchParams.get("search");
    if (query) {
      setSearch(query);
    }
  }, [searchParams]);

  // Handle Delete Book
  const handleDelete = (id) => {
    if (window.confirm("Are you sure you want to delete this book?")) {
      const updatedBooks = books.filter((b) => b.id !== id);
      setBooks(updatedBooks);
    }
  };

  // Filter & Sort Logic
  let processedBooks = books.filter((b) => {
    const matchSearch =
      b.title.toLowerCase().includes(search.toLowerCase()) ||
      b.author.toLowerCase().includes(search.toLowerCase()) ||
      b.isbn.includes(search);
    const matchCategory = b.category
      .toLowerCase()
      .includes(category.toLowerCase());

    return matchSearch && matchCategory;
  });

  // Apply Sorting
  if (sort === "az") {
    processedBooks.sort((a, b) => a.title.localeCompare(b.title));
  } else if (sort === "za") {
    processedBooks.sort((a, b) => b.title.localeCompare(a.title));
  } else if (sort === "newest") {
    // Assuming original array order is chronological; reverse shows newest first
    processedBooks = [...processedBooks].reverse();
  }

  return (
    <div className="animate-fade-in">
      {/* Page Header */}
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4 mb-8">
        <div>
          <h1 className="text-3xl font-bold text-text-dark">Books Directory</h1>
          <p className="text-text-muted mt-1">Manage your library collection</p>
        </div>
        <Link
          to="/add-book"
          className="bg-primary text-white px-5 py-2.5 rounded-lg font-medium hover:bg-[#5b54e0] shadow-md transition-all flex items-center gap-2"
        >
          <i className="fas fa-plus"></i> Add New Book
        </Link>
      </div>

      {/* Filters & Search Controls */}
      <div className="bg-bg-white p-5 rounded-xl shadow-sm border border-border-color mb-8 flex flex-wrap gap-4 items-center">
        {/* Local Search */}
        <div className="relative flex-1 min-w-[200px]">
          <i className="fas fa-search absolute left-4 top-1/2 -translate-y-1/2 text-text-muted"></i>
          <input
            type="text"
            placeholder="Search title, author, ISBN..."
            className="w-full pl-11 pr-4 py-2.5 rounded-lg border border-border-color bg-bg-light focus:outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark"
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
        </div>

        {/* Category Filter */}
        <div className="flex-1 min-w-[200px]">
          <input
            type="text"
            placeholder="Filter by Category"
            className="w-full px-4 py-2.5 rounded-lg border border-border-color bg-bg-light focus:outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark"
            value={category}
            onChange={(e) => setCategory(e.target.value)}
          />
        </div>

        {/* Sort Select */}
        <div className="min-w-[150px]">
          <select
            className="w-full px-4 py-2.5 rounded-lg border border-border-color bg-bg-light focus:outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark cursor-pointer"
            value={sort}
            onChange={(e) => setSort(e.target.value)}
          >
            <option value="newest">Newest First</option>
            <option value="az">Title (A-Z)</option>
            <option value="za">Title (Z-A)</option>
          </select>
        </div>
      </div>

      {/* Books Grid */}
      {processedBooks.length === 0 ? (
        <div className="text-center py-16 bg-bg-white rounded-xl border border-border-color">
          <i className="fas fa-book-open text-5xl text-text-muted mb-4 opacity-50"></i>
          <h3 className="text-xl font-bold text-text-dark">No books found</h3>
          <p className="text-text-muted mt-2">
            Try adjusting your search or add a new book.
          </p>
        </div>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 xl:grid-cols-4 gap-6">
          {processedBooks.map((book) => (
            <div
              key={book.id}
              className="bg-bg-white rounded-xl shadow-sm border border-border-color overflow-hidden hover:-translate-y-1 hover:shadow-md transition-all duration-300 flex flex-col group"
            >
              {/* Cover Image & Badge */}
              <div className="h-56 bg-gray-100 relative overflow-hidden">
                <span
                  className={`absolute top-3 right-3 px-3 py-1 text-xs font-bold rounded-full z-10 backdrop-blur-sm shadow-sm ${book.available > 0 ? "bg-success/90 text-white" : "bg-danger/90 text-white"}`}
                >
                  {book.available > 0 ? "Available" : "Out of Stock"}
                </span>
                <img
                  src={
                    book.cover ||
                    "https://via.placeholder.com/200x300?text=No+Cover"
                  }
                  alt={book.title}
                  className="w-full h-full object-cover group-hover:scale-105 transition-transform duration-500"
                />
              </div>

              {/* Book Info */}
              <div className="p-5 flex flex-col flex-1">
                <h3 className="font-bold text-lg text-text-dark line-clamp-2 leading-tight mb-1">
                  {book.title}
                </h3>
                <p className="text-text-muted text-sm mb-3">by {book.author}</p>

                <div className="mb-4">
                  <span className="inline-block bg-primary/10 text-primary text-xs px-2.5 py-1 rounded-md font-medium">
                    {book.category}
                  </span>
                </div>

                <div className="mt-auto flex justify-between items-center text-sm text-text-muted border-t border-border-color pt-4 mb-4">
                  <span className="flex items-center gap-1">
                    <i className="fas fa-layer-group"></i> {book.available}/
                    {book.quantity}
                  </span>
                  <span className="text-xs">ISBN: {book.isbn}</span>
                </div>

                {/* Action Buttons */}
                <div className="flex gap-2">
                  <Link
                    to={`/issued?bookId=${book.id}`}
                    className="flex-1 bg-secondary hover:bg-[#00a38d] text-white text-center py-2 rounded-lg text-sm font-medium transition-colors"
                  >
                    Issue
                  </Link>
                  <Link
                    to={`/edit-book/${book.id}`}
                    className="flex items-center justify-center w-10 border border-border-color text-text-muted hover:text-primary hover:border-primary rounded-lg transition-colors"
                    title="Edit Book"
                  >
                    <i className="fas fa-edit"></i>
                  </Link>
                  <button
                    onClick={() => handleDelete(book.id)}
                    className="flex items-center justify-center w-10 border border-border-color text-text-muted hover:text-danger hover:border-danger rounded-lg transition-colors"
                    title="Delete Book"
                  >
                    <i className="fas fa-trash"></i>
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
