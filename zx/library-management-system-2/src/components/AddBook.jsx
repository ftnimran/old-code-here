import { useState } from "react";
import { useLibrary } from "../context/LibraryContext";
import { useNavigate, Link } from "react-router-dom";

const PREDEFINED_CATEGORIES = [
  "Fiction",
  "Non-Fiction",
  "Science",
  "Technology",
  "History",
  "Biography",
];

export default function AddBook() {
  const { books, setBooks } = useLibrary();
  const navigate = useNavigate();

  const [isCustomCategory, setIsCustomCategory] = useState(false);

  const [formData, setFormData] = useState({
    title: "",
    author: "",
    category: "Fiction",
    isbn: "",
    quantity: 1,
    description: "",
    cover: "",
  });

  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData((prev) => ({ ...prev, [id]: value }));
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      if (file.size > 2 * 1024 * 1024) {
        alert("Image size should be less than 2MB");
        return;
      }
      const reader = new FileReader();
      reader.onloadend = () => {
        setFormData((prev) => ({ ...prev, cover: reader.result }));
      };
      reader.readAsDataURL(file);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    const newBook = {
      id: "B" + Date.now(),
      title: formData.title,
      author: formData.author,
      category: formData.category.trim(), // Extra spaces hata diye
      isbn: formData.isbn,
      quantity: parseInt(formData.quantity),
      available: parseInt(formData.quantity),
      description: formData.description,
      cover:
        formData.cover || "https://via.placeholder.com/200x300?text=No+Cover",
    };

    setBooks([...books, newBook]);
    alert("Book added successfully!");
    navigate("/books");
  };

  return (
    <div className="animate-fade-in max-w-3xl mx-auto">
      <div className="flex items-center gap-3 mb-8 text-text-muted">
        <Link
          to="/books"
          className="hover:text-primary transition-colors flex items-center gap-2"
        >
          <i className="fas fa-arrow-left"></i>
          <span className="font-medium">Back to Books</span>
        </Link>
      </div>

      <div className="mb-8 text-center">
        <h1 className="text-3xl font-bold text-text-dark">Add New Book</h1>
        <p className="text-text-muted mt-1">
          Fill in the details to add a new book to the library.
        </p>
      </div>

      <div className="bg-bg-white p-8 rounded-2xl shadow-sm border border-border-color">
        <form onSubmit={handleSubmit} className="space-y-6">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div>
              <label className="block text-sm font-medium text-text-dark mb-1.5">
                Book Title
              </label>
              <input
                type="text"
                id="title"
                className="w-full p-3 rounded-lg border border-border-color bg-bg-light focus:outline-none focus:border-primary text-text-dark"
                placeholder="e.g. The Great Gatsby"
                value={formData.title}
                onChange={handleChange}
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-text-dark mb-1.5">
                Author Name
              </label>
              <input
                type="text"
                id="author"
                className="w-full p-3 rounded-lg border border-border-color bg-bg-light focus:outline-none focus:border-primary text-text-dark"
                placeholder="e.g. F. Scott Fitzgerald"
                value={formData.author}
                onChange={handleChange}
                required
              />
            </div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
            {/* FIXED: Dynamic Category Box */}
            <div>
              <label className="block text-sm font-medium text-text-dark mb-1.5">
                Category
              </label>
              {isCustomCategory ? (
                <div className="flex items-center gap-2">
                  <input
                    type="text"
                    id="category"
                    className="w-full p-3 rounded-lg border border-border-color bg-bg-light focus:outline-none focus:border-primary text-text-dark"
                    placeholder="Type category..."
                    value={formData.category}
                    onChange={handleChange}
                    required
                    autoFocus
                  />
                  <button
                    type="button"
                    onClick={() => {
                      setIsCustomCategory(false);
                      setFormData((prev) => ({ ...prev, category: "Fiction" })); // Wapas default dropdown me le jayega
                    }}
                    className="w-[50px] h-[50px] flex-shrink-0 flex items-center justify-center rounded-lg border border-border-color bg-bg-light text-text-muted hover:text-danger hover:border-danger hover:bg-danger/10 transition-colors"
                    title="Select from list"
                  >
                    <i className="fas fa-times"></i>
                  </button>
                </div>
              ) : (
                <select
                  id="category"
                  className="w-full p-3 rounded-lg border border-border-color bg-bg-light focus:outline-none focus:border-primary text-text-dark cursor-pointer"
                  value={formData.category}
                  onChange={(e) => {
                    if (e.target.value === "Other") {
                      setIsCustomCategory(true);
                      setFormData((prev) => ({ ...prev, category: "" })); // Naya type karne ke liye khali kiya
                    } else {
                      handleChange(e);
                    }
                  }}
                  required
                >
                  {PREDEFINED_CATEGORIES.map((cat) => (
                    <option key={cat} value={cat}>
                      {cat}
                    </option>
                  ))}
                  <option value="Other" className="font-bold text-primary">
                    Other (Type custom)...
                  </option>
                </select>
              )}
            </div>

            <div>
              <label className="block text-sm font-medium text-text-dark mb-1.5">
                ISBN
              </label>
              <input
                type="text"
                id="isbn"
                className="w-full p-3 rounded-lg border border-border-color bg-bg-light focus:outline-none focus:border-primary text-text-dark"
                placeholder="978-..."
                value={formData.isbn}
                onChange={handleChange}
                required
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-text-dark mb-1.5">
                Quantity
              </label>
              <input
                type="number"
                id="quantity"
                min="1"
                className="w-full p-3 rounded-lg border border-border-color bg-bg-light focus:outline-none focus:border-primary text-text-dark"
                value={formData.quantity}
                onChange={handleChange}
                required
              />
            </div>
          </div>

          <div>
            <label className="block text-sm font-medium text-text-dark mb-2">
              Book Cover Image (Optional)
            </label>
            <div className="flex items-center gap-4 bg-bg-light p-3 rounded-lg border border-border-color">
              <div className="w-16 h-24 rounded border-2 border-primary/30 overflow-hidden bg-bg-white flex items-center justify-center flex-shrink-0 shadow-inner">
                {formData.cover &&
                !formData.cover.includes("placeholder.com") ? (
                  <img
                    src={formData.cover}
                    alt="Cover Preview"
                    className="w-full h-full object-cover"
                  />
                ) : (
                  <i className="fa-solid fa-book text-text-muted text-2xl"></i>
                )}
              </div>
              <div className="flex-1">
                <input
                  type="file"
                  accept="image/*"
                  onChange={handleFileChange}
                  className="block w-full text-xs text-text-muted file:mr-3 file:py-1.5 file:px-3 file:rounded-md file:border-0 file:text-xs file:font-semibold file:bg-primary/10 file:text-primary hover:file:bg-primary/20 file:cursor-pointer"
                />
                <p className="text-[10px] text-text-muted mt-2">
                  Upload a JPG or PNG under 2MB.
                </p>
              </div>
            </div>
          </div>

          <div>
            <label className="block text-sm font-medium text-text-dark mb-1.5">
              Description (Optional)
            </label>
            <textarea
              id="description"
              rows="4"
              className="w-full p-3 rounded-lg border border-border-color bg-bg-light focus:outline-none focus:border-primary text-text-dark"
              placeholder="Brief summary of the book..."
              value={formData.description}
              onChange={handleChange}
            ></textarea>
          </div>

          <div className="flex justify-end gap-4 border-t border-border-color pt-6">
            <Link
              to="/books"
              className="px-5 py-2.5 rounded-lg border border-border-color font-medium text-text-muted hover:bg-bg-light transition-all"
            >
              Cancel
            </Link>
            <button
              type="submit"
              className="bg-primary text-white px-6 py-2.5 rounded-lg font-medium hover:bg-[#5b54e0] shadow-md transition-all flex items-center gap-2"
            >
              <i className="fas fa-save"></i> Save Book
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
