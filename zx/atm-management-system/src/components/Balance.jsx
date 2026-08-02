import React, { useContext } from "react";
import { ATMContext } from "../context/ATMContext";

export default function Balance() {
  const { balance, navigateTo } = useContext(ATMContext);

  return (
    <section className="screen active">
      <h2>Available Balance</h2>
      <div className="balance-display">
        <span className="currency">$</span>
        <span>{balance.toFixed(2)}</span>
      </div>
      <button className="btn-secondary" onClick={() => navigateTo("dashboard")}>
        Back to Menu
      </button>
    </section>
  );
}
