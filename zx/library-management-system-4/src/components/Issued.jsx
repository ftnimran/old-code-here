import { useState, useEffect, useRef } from "react";
import { useLibrary } from "../context/LibraryContext";
import { useSearchParams } from "react-router-dom";

function SearchableSelect({
  options,
  value,
  onChange,
  placeholder,
  isDisabled,
}) {
  const [isOpen, setIsOpen] = useState(false);
  const [search, setSearch] = useState("");
  const wrapperRef = useRef(null);
  const selectedOption = options.find((opt) => opt.value === value);

  useEffect(() => {
    function handleClickOutside(event) {
      if (wrapperRef.current && !wrapperRef.current.contains(event.target)) {
        setIsOpen(false);
        setSearch("");
      }
    }
    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  const filteredOptions = options.filter((opt) =>
    opt.label.toLowerCase().includes(search.toLowerCase()),
  );

  return (
    <div className="relative w-full" ref={wrapperRef}>
      <div
        className={`w-full p-3 border border-border-color rounded-lg bg-bg-light flex justify-between items-center transition-all focus-within:border-primary focus-within:ring-3 focus-within:ring-primary/10 ${isDisabled ? "opacity-70 cursor-not-allowed select-none" : "cursor-pointer"}`}
        onClick={() => !isDisabled && setIsOpen(!isOpen)}
      >
        <span
          className={`truncate ${selectedOption ? "text-text-dark font-medium" : "text-text-muted"}`}
        >
          {selectedOption ? selectedOption.label : placeholder}
        </span>
        <i
          className={`fa-solid fa-chevron-down text-text-muted transition-transform duration-300 ${isOpen ? "rotate-180" : ""}`}
        ></i>
      </div>
      {isOpen && !isDisabled && (
        <div className="absolute z-50 w-full mt-2 bg-bg-white border border-border-color rounded-xl shadow-lg max-h-64 overflow-y-auto animate-fade-in">
          <div className="sticky top-0 bg-bg-white p-3 border-b border-border-color z-10">
            <input
              type="text"
              className="w-full pl-9 pr-3 py-2 border border-border-color rounded-lg bg-bg-light outline-none text-sm text-text-dark focus:border-primary"
              placeholder="Search..."
              value={search}
              onChange={(e) => setSearch(e.target.value)}
              onClick={(e) => e.stopPropagation()}
              autoFocus
            />
          </div>
          <div className="p-1">
            {filteredOptions.length === 0 ? (
              <div className="p-3 text-sm text-text-muted text-center font-medium">
                No results
              </div>
            ) : (
              filteredOptions.map((opt) => (
                <div
                  key={opt.value}
                  className={`p-3 text-sm rounded-lg cursor-pointer ${opt.disabled ? "opacity-50 cursor-not-allowed bg-bg-light/50 text-danger font-medium" : "hover:bg-primary/10 text-text-dark font-medium"}`}
                  onClick={() => {
                    if (!opt.disabled) {
                      onChange(opt.value);
                      setIsOpen(false);
                      setSearch("");
                    }
                  }}
                >
                  {opt.label}
                </div>
              ))
            )}
          </div>
        </div>
      )}
    </div>
  );
}

export default function Issued() {
  const { user, books, setBooks, members, issued, setIssued, addNotification } =
    useLibrary();
  const [searchParams] = useSearchParams();

  const defaultDate = new Date();
  defaultDate.setDate(defaultDate.getDate() + 7);
  const defaultDateString = defaultDate.toISOString().split("T")[0];
  const isAdmin = user?.role === "admin";
  const currentMember = members.find((m) => m.email === user?.email);

  const [bookId, setBookId] = useState(searchParams.get("bookId") || "");
  const [memberId, setMemberId] = useState("");
  const [returnDate, setReturnDate] = useState(defaultDateString);
  const [txSearch, setTxSearch] = useState("");

  useEffect(() => {
    if (!isAdmin && currentMember) setMemberId(currentMember.id);
  }, [isAdmin, currentMember]);

  const bookOptions = books.map((b) => ({
    value: b.id,
    label: `${b.title} (Qty: ${b.available})`,
    disabled: b.available === 0 && b.id !== bookId,
  }));
  const memberOptions = members.map((m) => {
    const isBadStatus = m.status === "Inactive" || m.status === "Deactivate";
    return {
      value: m.id,
      label: `${m.name} (${m.phone})${isBadStatus ? ` [${m.status}]` : ""}`,
      disabled: isBadStatus,
    };
  });

  const handleIssue = (e) => {
    e.preventDefault();
    if (!bookId || !memberId || !returnDate) {
      alert("Please fill all fields");
      return;
    }

    const bookIndex = books.findIndex((b) => b.id === bookId);
    const member = members.find((m) => m.id === memberId);
    const book = books[bookIndex];

    if (
      member &&
      (member.status === "Inactive" || member.status === "Deactivate")
    ) {
      addNotification(
        "Issue Failed",
        `Account is ${member.status}.`,
        "danger",
        "fa-circle-xmark",
        member.email,
      );
      alert(`${member.status} members cannot issue new books.`);
      return;
    }

    if (bookIndex !== -1 && book.available > 0) {
      const updatedBooks = [...books];
      updatedBooks[bookIndex].available -= 1;
      const newTransaction = {
        id: "T" + Date.now(),
        bookId,
        memberId,
        issueDate: new Date().toISOString().split("T")[0],
        returnDate,
        status: "Issued",
      };
      setBooks(updatedBooks);
      setIssued([...issued, newTransaction]);
      addNotification(
        "Book Issued Successfully",
        `"${book.title}" was issued to ${member.name}.`,
        "success",
        "fa-circle-check",
        member.email,
      );
      alert("Book issued successfully!");
      setBookId("");
      if (isAdmin) setMemberId("");
      setReturnDate(defaultDateString);
    } else alert("This book is currently out of stock!");
  };

  const handleReturn = (transactionId) => {
    if (window.confirm("Mark this book as returned?")) {
      const transIndex = issued.findIndex((i) => i.id === transactionId);
      if (transIndex !== -1) {
        const transaction = issued[transIndex];
        const member = members.find((m) => m.id === transaction.memberId) || {
          name: "Unknown",
        };
        const bookIndex = books.findIndex((b) => b.id === transaction.bookId);

        if (bookIndex !== -1) {
          const updatedBooks = [...books];
          updatedBooks[bookIndex].available += 1;
          setBooks(updatedBooks);
        }

        const updatedIssued = [...issued];
        updatedIssued[transIndex].status = "Returned";
        setIssued(updatedIssued);
        addNotification(
          "Book Returned",
          `"${books[bookIndex]?.title}" returned by ${member.name}.`,
          "success",
          "fa-arrow-rotate-left",
          member.email,
        );
      }
    }
  };

  const today = new Date().toISOString().split("T")[0];
  const filteredTransactions = issued
    .filter((item) => {
      if (!isAdmin && item.memberId !== currentMember?.id) return false;
      const searchLower = txSearch.toLowerCase();
      const book = books.find((b) => b.id === item.bookId) || { title: "" };
      const member = members.find((m) => m.id === item.memberId) || {
        name: "",
        email: "",
      };
      return (
        item.id.toLowerCase().includes(searchLower) ||
        book.title.toLowerCase().includes(searchLower) ||
        member.name.toLowerCase().includes(searchLower) ||
        member.email.toLowerCase().includes(searchLower)
      );
    })
    .sort((a, b) => new Date(b.issueDate) - new Date(a.issueDate));

  return (
    <div className="animate-fade-in">
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-text-dark">Circulation Desk</h1>
        <p className="text-text-muted mt-1">
          Issue books to members and manage returns.
        </p>
      </div>

      <div className="bg-bg-white p-6 sm:p-8 rounded-2xl shadow-sm border border-border-color mb-8">
        <h3 className="text-xl font-bold text-primary mb-6">
          Issue a New Book
        </h3>
        <form onSubmit={handleIssue} className="flex flex-wrap items-end gap-5">
          <div className="flex-1 min-w-[250px]">
            <label className="block text-sm font-medium text-text-dark mb-1.5">
              Select Book
            </label>
            <SearchableSelect
              options={bookOptions}
              value={bookId}
              onChange={setBookId}
              placeholder="-- Search & Choose Book --"
            />
          </div>
          <div className="flex-1 min-w-[250px]">
            <label className="block text-sm font-medium text-text-dark mb-1.5">
              Select Member {isAdmin ? "" : "(Auto-filled)"}
            </label>
            <SearchableSelect
              options={memberOptions}
              value={memberId}
              onChange={setMemberId}
              placeholder="-- Search & Choose Member --"
              isDisabled={!isAdmin}
            />
          </div>
          <div className="w-[160px]">
            <label className="block text-sm font-medium text-text-dark mb-1.5">
              Return Date
            </label>
            <input
              type="date"
              className="w-full p-3 rounded-lg border border-border-color bg-bg-light outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-text-dark"
              value={returnDate}
              onChange={(e) => setReturnDate(e.target.value)}
              required
            />
          </div>
          <button
            type="submit"
            className="bg-primary text-white px-6 h-[50px] rounded-lg font-medium hover:bg-[#5b54e0] shadow-md transition-all flex items-center justify-center gap-2 cursor-pointer w-full sm:w-auto"
          >
            Confirm Issue
          </button>
        </form>
      </div>

      <div className="bg-bg-white rounded-2xl shadow-sm border border-border-color overflow-hidden">
        <div className="p-6 border-b border-border-color bg-primary/5 flex flex-col sm:flex-row sm:items-center justify-between gap-4">
          <h3 className="text-xl font-bold text-text-dark">
            {isAdmin ? "Recent Transactions" : "My Issued Books"}
          </h3>
          <div className="relative w-full sm:w-72">
            <i className="fa-solid fa-search absolute left-4 top-1/2 -translate-y-1/2 text-text-muted text-sm"></i>
            <input
              type="text"
              placeholder="Search tx id, book, member..."
              className="w-full pl-10 pr-4 py-2 border border-border-color rounded-lg bg-bg-light outline-none focus:border-primary focus:ring-3 focus:ring-primary/10 transition-all text-sm text-text-dark"
              value={txSearch}
              onChange={(e) => setTxSearch(e.target.value)}
            />
          </div>
        </div>
        <div className="overflow-x-auto">
          <table className="w-full text-left border-collapse">
            <thead>
              <tr className="bg-bg-light/50 text-primary border-b border-border-color text-sm uppercase tracking-wider">
                <th className="p-4 font-semibold">Tx ID</th>
                <th className="p-4 font-semibold">Book Info</th>
                <th className="p-4 font-semibold">Member Info</th>
                <th className="p-4 font-semibold">Dates</th>
                <th className="p-4 font-semibold">Status</th>
                <th className="p-4 font-semibold">Action</th>
              </tr>
            </thead>
            <tbody>
              {filteredTransactions.length === 0 ? (
                <tr>
                  <td
                    colSpan="6"
                    className="p-8 text-center text-text-muted font-medium"
                  >
                    No transactions found.
                  </td>
                </tr>
              ) : (
                filteredTransactions.map((item) => {
                  const book = books.find((b) => b.id === item.bookId) || {
                    title: "Unknown",
                  };
                  const member = members.find(
                    (m) => m.id === item.memberId,
                  ) || { name: "Unknown", email: "" };
                  let displayStatus = item.status;
                  let statusClass =
                    item.status === "Returned"
                      ? "bg-success/20 text-success"
                      : "bg-warning/20 text-warning";
                  if (item.status === "Issued" && item.returnDate < today) {
                    displayStatus = "Overdue";
                    statusClass = "bg-danger/20 text-danger";
                  }

                  return (
                    <tr
                      key={item.id}
                      className="border-b border-border-color hover:bg-black/5 transition-colors"
                    >
                      <td className="p-4 font-mono text-sm text-text-muted">
                        #{item.id.slice(-6)}
                      </td>
                      <td className="p-4 font-medium text-text-dark">
                        {book.title}
                      </td>
                      <td className="p-4">
                        <div className="font-medium text-text-dark">
                          {member.name}
                        </div>
                        <div className="text-xs text-text-muted mt-1">
                          {member.email}
                        </div>
                      </td>
                      <td className="p-4 text-sm text-text-dark">
                        <div>Issued: {item.issueDate}</div>
                        <div className="text-danger mt-1">
                          Return: {item.returnDate}
                        </div>
                      </td>
                      <td className="p-4">
                        <span
                          className={`${statusClass} px-3 py-1 rounded-full text-xs font-bold uppercase tracking-wider inline-block`}
                        >
                          {displayStatus}
                        </span>
                      </td>
                      <td className="p-4">
                        {item.status !== "Returned" ? (
                          <button
                            onClick={() => handleReturn(item.id)}
                            className="bg-secondary text-white px-4 py-2 rounded-lg text-sm font-medium hover:bg-[#00a38d] shadow-sm transition-all cursor-pointer"
                          >
                            Return
                          </button>
                        ) : (
                          <i className="fa-solid fa-check-circle text-success text-2xl ml-2 drop-shadow-sm"></i>
                        )}
                      </td>
                    </tr>
                  );
                })
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
