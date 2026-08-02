import React, { useState } from "react";

export default function BooksManager({
  books,
  updateBooks,
  setCurrentPage,
  setEditBookId,
  globalSearch,
}) {
  const [categoryFilter, setCategoryFilter] = useState("");
  const [sortOrder, setSortOrder] = useState("az");

  const handleDelete = (id) => {
    if (window.confirm("Are you sure you want to delete this book?")) {
      const filtered = books.filter((b) => b.id !== id);
      updateBooks(filtered);
    }
  };

  // Filter application pipeline merging local headers controls + shared global navbar inputs
  const filteredBooks = books.filter((b) => {
    const query = globalSearch.toLowerCase();
    const matchesSearch =
      b.title.toLowerCase().includes(query) ||
      b.author.toLowerCase().includes(query) ||
      b.isbn.includes(query);
    const matchesCategory =
      categoryFilter === "" ||
      b.category.toLowerCase() === categoryFilter.toLowerCase();
    return matchesSearch && matchesCategory;
  });

  if (sortOrder === "az")
    filteredBooks.sort((a, b) => a.title.localeCompare(b.title));
  if (sortOrder === "za")
    filteredBooks.sort((a, b) => b.title.localeCompare(a.title));
  if (sortOrder === "newest") filteredBooks.reverse();

  return (
    <div>
      <div
        style={{
          display: "flex",
          justifyContent: "between",
          alignItems: "center",
          flexWrap: "wrap",
          gap: "1rem",
          marginBottom: "2rem",
        }}
      >
        <div>
          <h1>Books Catalog</h1>
          <p style={{ color: "var(--text-muted)" }}>
            Manage your catalog, stock metrics and parameters.
          </p>
        </div>
        <button
          className="btn btn-primary"
          style={{ marginLeft: "auto" }}
          onClick={() => {
            setEditBookId(null);
            setCurrentPage("add-book");
          }}
        >
          <i className="fas fa-plus"></i> Add New Book
        </button>
      </div>

      {/* Filter controls row */}
      <div
        className="card"
        style={{
          display: "flex",
          gap: "1rem",
          flexWrap: "wrap",
          marginBottom: "2rem",
          padding: "1rem",
        }}
      >
        <select
          className="form-control"
          style={{ width: "200px" }}
          value={categoryFilter}
          onChange={(e) => setCategoryFilter(e.target.value)}
        >
          <option value="">All Categories</option>
          <option value="Fiction">Fiction</option>
          <option value="Technology">Technology</option>
          <option value="Science">Science</option>
          <option value="Biography">Biography</option>
        </select>
        <select
          className="form-control"
          style={{ width: "200px" }}
          value={sortOrder}
          onChange={(e) => setSortOrder(e.target.value)}
        >
          <option value="az">Alphabetical (A-Z)</option>
          <option value="za">Alphabetical (Z-A)</option>
          <option value="newest">Chronological ID Order</option>
        </select>
      </div>

      {/* Grid rendering cards */}
      <div className="books-grid">
        {filteredBooks.map((book) => (
          <div className="card book-card" key={book.id}>
            <div className="book-cover">
              <img
                src={
                  book.cover ||
                  "https://images.unsplash.com/photo-1543002588-bfa74002ed7e?w=200"
                }
                alt={book.title}
              />
              <span
                className={`book-badge badge ${parseInt(book.available) > 0 ? "badge-success" : "badge-danger"}`}
              >
                {parseInt(book.available) > 0
                  ? `${book.available} In Stock`
                  : "Out of Stock"}
              </span>
            </div>
            <div
              className="book-info"
              style={{
                display: "flex",
                flexDirection: "column",
                flex: 1,
                paddingTop: "1rem",
              }}
            >
              <span
                style={{
                  fontSize: "0.8rem",
                  color: "var(--primary)",
                  fontWeight: "600",
                  textTransform: "uppercase",
                }}
              >
                {book.category}
              </span>
              <h4 className="book-title" style={{ margin: "0.25rem 0" }}>
                {book.title}
              </h4>
              <p className="book-author">{book.author}</p>
              <span style={{ fontSize: "0.85rem", color: "var(--text-muted)" }}>
                ISBN: {book.isbn}
              </span>

              <div
                className="book-actions"
                style={{
                  display: "flex",
                  gap: "0.5rem",
                  marginTop: "auto",
                  paddingTop: "1rem",
                }}
              >
                <button
                  className="btn form-control"
                  style={{ padding: "6px", background: "var(--bg-light)" }}
                  title="Issue Hook"
                  onClick={() => setCurrentPage("issued")}
                >
                  <i
                    className="fas fa-exchange-alt"
                    style={{ color: "var(--secondary)" }}
                  ></i>
                </button>
                <button
                  className="btn form-control"
                  style={{ padding: "6px", background: "var(--bg-light)" }}
                  title="Edit Details"
                  onClick={() => {
                    setEditBookId(book.id);
                    setCurrentPage("add-book");
                  }}
                >
                  <i
                    className="fas fa-edit"
                    style={{ color: "var(--accent)" }}
                  ></i>
                </button>
                <button
                  className="btn form-control"
                  style={{ padding: "6px", background: "var(--bg-light)" }}
                  title="Delete"
                  onClick={() => handleDelete(book.id)}
                >
                  <i
                    className="fas fa-trash"
                    style={{ color: "var(--danger)" }}
                  ></i>
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
