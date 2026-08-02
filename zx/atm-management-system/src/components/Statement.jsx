import React, { useContext } from "react";
import { ATMContext } from "../context/ATMContext";

export default function Statement() {
  const { transactions, navigateTo } = useContext(ATMContext);

  return (
    <section className="screen active">
      <h2>Mini Statement</h2>
      <div className="statement-container">
        <table className="statement-table">
          <thead>
            <tr>
              <th>Date</th>
              <th>Type</th>
              <th>Amount</th>
            </tr>
          </thead>
          <tbody>
            {transactions.slice(0, 5).map((t, idx) => (
              <tr key={idx}>
                <td>{t.date}</td>
                <td>{t.type}</td>
                <td
                  style={{
                    color:
                      t.type === "Deposit" ? "var(--success)" : "var(--danger)",
                  }}
                >
                  {t.type === "Withdraw" ? "-" : "+"}${t.amount}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <button className="btn-secondary" onClick={() => navigateTo("dashboard")}>
        Back
      </button>
    </section>
  );
}
