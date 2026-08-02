import React, { useState, useEffect } from "react";

export default function AddBook({
  books,
  updateBooks,
  editBookId,
  setEditBookId,
  setCurrentPage,
}) {
  const [title, setTitle] = useState("");
  const [author, setAuthor] = useState("");
  const [category, setCategory] = useState("Fiction");
  const [isbn, setIsbn] = useState("");
  const [quantity, setQuantity] = useState(1);
  const [desc, setDesc] = useState("");
  const [cover, setCover] = useState("");

  useEffect(() => {
    if (editBookId) {
      const tgt = books.find((b) => b.id === editBookId);
      if (tgt) {
        setTitle(tgt.title);
        setAuthor(tgt.author);
        setCategory(tgt.category);
        setIsbn(tgt.isbn);
        setQuantity(parseInt(tgt.quantity));
        setDesc(tgt.description || "");
        setCover(tgt.cover || "");
      }
    }
  }, [editBookId, books]);

  const handleFormSubmit = (e) => {
    e.preventDefault();

    if (editBookId) {
      // Processing operational updates
      const altered = books.map((b) => {
        if (b.id === editBookId) {
          const diff = parseInt(quantity) - parseInt(b.quantity);
          const recalculatedAvail = parseInt(b.available) + diff;
          return {
            ...b,
            title,
            author,
            category,
            isbn,
            quantity: parseInt(quantity),
            available: recalculatedAvail < 0 ? 0 : recalculatedAvail,
            description: desc,
            cover: cover || b.cover,
          };
        }
        return b;
      });
      updateBooks(altered);
      alert("Book details updated successfully!");
    } else {
      // New record insertion mapping
      const newB = {
        id: "B" + Date.now(),
        title,
        author,
        category,
        isbn,
        quantity: parseInt(quantity),
        available: parseInt(quantity),
        description: desc,
        cover:
          cover ||
          "https://images.unsplash.com/photo-1543002588-bfa74002ed7e?w=200",
      };
      updateBooks([...books, newB]);
      alert("Book logged successfully into dynamic inventory database!");
    }
    setEditBookId(null);
    setCurrentPage("books");
  };

  return (
    <div style={{ maxWidth: "700px", margin: "0 auto" }}>
      <div
        style={{
          display: "flex",
          alignItems: "center",
          gap: "1rem",
          marginBottom: "2rem",
        }}
      >
        <button
          className="btn"
          style={{ background: "var(--bg-white)", padding: "10px" }}
          onClick={() => setCurrentPage("books")}
        >
          <i className="fas fa-arrow-left"></i>
        </button>
        <h2>
          {editBookId ? "Modify System Book Record" : "Add New Inventory Book"}
        </h2>
      </div>

      <form className="card" onSubmit={handleFormSubmit}>
        <div className="form-group">
          <label className="form-label">Book Title</label>
          <input
            type="text"
            className="form-control"
            required
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>
        <div
          style={{
            display: "grid",
            gridTemplateColumns: "1fr 1fr",
            gap: "1rem",
          }}
        >
          <div className="form-group">
            <label className="form-label">Author Name</label>
            <input
              type="text"
              className="form-control"
              required
              value={author}
              onChange={(e) => setAuthor(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label className="form-label">Category</label>
            <select
              className="form-control"
              value={category}
              onChange={(e) => setCategory(e.target.value)}
            >
              <option value="Fiction">Fiction</option>
              <option value="Technology">Technology</option>
              <option value="Science">Science</option>
              <option value="Biography">Biography</option>
            </select>
          </div>
        </div>
        <div
          style={{
            display: "grid",
            gridTemplateColumns: "1fr 1fr",
            gap: "1rem",
          }}
        >
          <div className="form-group">
            <label className="form-label">ISBN Reference Number</label>
            <input
              type="text"
              className="form-control"
              required
              value={isbn}
              onChange={(e) => setIsbn(e.target.value)}
            />
          </div>
          <div className="form-group">
            <label className="form-label">Stock Quantity Copies</label>
            <input
              type="number"
              min="1"
              className="form-control"
              required
              value={quantity}
              onChange={(e) => setQuantity(e.target.value)}
            />
          </div>
        </div>
        <div className="form-group">
          <label className="form-label">Cover Image URL (Optional)</label>
          <input
            type="url"
            className="form-control"
            placeholder="https://example.com/cover.jpg"
            value={cover}
            onChange={(e) => setCover(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label className="form-label">Overview Description</label>
          <textarea
            className="form-control"
            rows="4"
            value={desc}
            onChange={(e) => setDesc(e.target.value)}
          ></textarea>
        </div>
        <button
          type="submit"
          className="btn btn-primary"
          style={{ width: "100%", justifyContent: "center" }}
        >
          <i className="fas fa-save"></i> Save Book Record
        </button>
      </form>
    </div>
  );
}
