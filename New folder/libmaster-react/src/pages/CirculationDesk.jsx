import React, { useState } from "react";

export default function CirculationDesk({
  books,
  issued,
  members,
  updateBooks,
  updateIssued,
}) {
  const [selectBook, setSelectBook] = useState("");
  const [selectMember, setSelectMember] = useState("");
  const [returnDate, setReturnDate] = useState("");
  const [searchTx, setSearchTx] = useState("");

  const handleIssueBook = (e) => {
    e.preventDefault();
    if (!selectBook || !selectMember || !returnDate) {
      alert("Please specify all transaction inputs!");
      return;
    }

    const bIdx = books.findIndex((b) => b.id === selectBook);
    if (bIdx !== -1 && parseInt(books[bIdx].available) > 0) {
      // Decrement catalog availability metrics
      const copiedBooks = [...books];
      copiedBooks[bIdx].available = parseInt(copiedBooks[bIdx].available) - 1;
      updateBooks(copiedBooks);

      const record = {
        id: "I" + Date.now(),
        bookId: selectBook,
        memberId: selectMember,
        issueDate: new Date().toISOString().split("T")[0],
        returnDate: returnDate,
        status: "Issued",
      };

      updateIssued([...issued, record]);
      alert("Book assigned and checked out successfully!");
      setSelectBook("");
      setSelectMember("");
      setReturnDate("");
    } else {
      alert(
        "Requested material inventory current stack index out of bounds (Unavailable)!",
      );
    }
  };

  const handleReturnBook = (txId) => {
    if (
      window.confirm("Process inventory check-in status return configuration?")
    ) {
      const alteredTx = issued.map((item) => {
        if (item.id === txId) {
          // Replenish stock levels numbers counters matching ID
          const bIdx = books.findIndex((b) => b.id === item.bookId);
          if (bIdx !== -1) {
            const modifiedBooks = [...books];
            modifiedBooks[bIdx].available =
              parseInt(modifiedBooks[bIdx].available) + 1;
            updateBooks(modifiedBooks);
          }
          return { ...item, status: "Returned" };
        }
        return item;
      });
      updateIssued(alteredTx);
    }
  };

  const filteredTransactions = issued.filter((tx) => {
    const bkName =
      books.find((b) => b.id === tx.bookId)?.title.toLowerCase() || "";
    const mbName =
      members.find((m) => m.id === tx.memberId)?.name.toLowerCase() || "";
    const searchString = searchTx.toLowerCase();
    return (
      bkName.includes(searchString) ||
      mbName.includes(searchString) ||
      tx.status.toLowerCase().includes(searchString)
    );
  });

  return (
    <div>
      <div>
        <h1>Circulation Desk</h1>
        <p style={{ color: "var(--text-muted)" }}>
          Issue assets data mappings out or process check-in returns execution
          timelines.
        </p>
      </div>

      {/* Allocation Panel Section */}
      <div className="card" style={{ margin: "2rem 0" }}>
        <h3 style={{ color: "var(--primary)", marginBottom: "1rem" }}>
          Checkout Asset Allocation
        </h3>
        <form
          onSubmit={handleIssueBook}
          style={{
            display: "flex",
            gap: "1rem",
            alignItems: "flex-end",
            flexWrap: "wrap",
          }}
        >
          <div
            className="form-group"
            style={{ flex: 1, minWidth: "200px", marginBottom: 0 }}
          >
            <label className="form-label">Target Material Title</label>
            <select
              className="form-control"
              value={selectBook}
              onChange={(e) => setSelectBook(e.target.value)}
            >
              <option value="">-- Choose Book --</option>
              {books.map((b) => (
                <option
                  key={b.id}
                  value={b.id}
                  disabled={parseInt(b.available) <= 0}
                >
                  {b.title} {parseInt(b.available) <= 0 ? "(Out of stock)" : ""}
                </option>
              ))}
            </select>
          </div>
          <div
            className="form-group"
            style={{ flex: 1, minWidth: "200px", marginBottom: 0 }}
          >
            <label className="form-label">Beneficiary Member</label>
            <select
              className="form-control"
              value={selectMember}
              onChange={(e) => setSelectMember(e.target.value)}
            >
              <option value="">-- Choose Profile Member --</option>
              {members.length === 0 ? (
                <option disabled>No members logged yet</option>
              ) : (
                members.map((m) => (
                  <option key={m.id} value={m.id}>
                    {m.name}
                  </option>
                ))
              )}
            </select>
          </div>
          <div
            className="form-group"
            style={{ width: "180px", marginBottom: 0 }}
          >
            <label className="form-label">Expected Return Timeline</label>
            <input
              type="date"
              className="form-control"
              value={returnDate}
              onChange={(e) => setReturnDate(e.target.value)}
            />
          </div>
          <button
            type="submit"
            className="btn btn-primary"
            style={{ height: "45px" }}
          >
            <i className="fas fa-check"></i> Finalize Issue
          </button>
        </form>
      </div>

      {/* List Tracking Section */}
      <div className="card">
        <div
          style={{
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            marginBottom: "1rem",
          }}
        >
          <h3>Recent Circulation Invoices</h3>
          <input
            type="text"
            className="form-control"
            placeholder="Search parameters logs..."
            style={{ width: "250px" }}
            value={searchTx}
            onChange={(e) => setSearchTx(e.target.value)}
          />
        </div>
        <table
          style={{
            width: "100%",
            borderCollapse: "collapse",
            textAlign: "left",
          }}
        >
          <thead>
            <tr
              style={{
                borderBottom: "2px solid var(--border-color)",
                height: "40px",
                color: "var(--text-muted)",
              }}
            >
              <th>Book Title</th>
              <th>Issued To</th>
              <th>Assigned Date</th>
              <th>Deadline Date</th>
              <th>System Status</th>
              <th>Actions Control</th>
            </tr>
          </thead>
          <tbody>
            {filteredTransactions.map((tx) => {
              const book = books.find((b) => b.id === tx.bookId);
              const member = members.find((m) => m.id === tx.memberId);
              return (
                <tr
                  key={tx.id}
                  style={{
                    borderBottom: "1px solid var(--border-color)",
                    height: "55px",
                  }}
                >
                  <td style={{ fontWeight: "500" }}>
                    {book ? book.title : "Deleted reference"}
                  </td>
                  <td>{member ? member.name : "Unknown profile entity"}</td>
                  <td>{tx.issueDate}</td>
                  <td>{tx.returnDate}</td>
                  <td>
                    <span
                      className={`badge ${tx.status === "Issued" ? "badge-danger" : "badge-success"}`}
                    >
                      {tx.status}
                    </span>
                  </td>
                  <td>
                    {tx.status === "Issued" && (
                      <button
                        className="btn badge-success"
                        style={{
                          padding: "6px 12px",
                          fontSize: "0.8rem",
                          borderRadius: "4px",
                        }}
                        onClick={() => handleReturnBook(tx.id)}
                      >
                        Mark Checked-In
                      </button>
                    )}
                  </td>
                </tr>
              );
            })}
            {filteredTransactions.length === 0 && (
              <tr>
                <td
                  colSpan="6"
                  style={{
                    textAlign: "center",
                    padding: "2rem",
                    color: "var(--text-muted)",
                  }}
                >
                  No Active Transaction Invoices match parameters.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}
