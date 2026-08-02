import React, { useState, useContext } from "react";
import { ATMContext } from "../context/ATMContext";

export default function Deposit() {
  const { setBalance, addTransaction, navigateTo } = useContext(ATMContext);
  const [amount, setAmount] = useState("");
  const [message, setMessage] = useState({ text: "", type: "" });

  const processDeposit = () => {
    const val = parseFloat(amount);
    if (isNaN(val) || val <= 0) {
      return setMessage({
        text: "Please enter a valid amount.",
        type: "error",
      });
    }

    setBalance((prev) => prev + val);
    addTransaction("Deposit", val);
    setMessage({ text: "Deposit Successful!", type: "success" });
    setTimeout(() => navigateTo("dashboard"), 1500);
  };

  return (
    <section className="screen active">
      <h2>Deposit Cash</h2>
      <p>Enter amount to deposit</p>
      <input
        type="number"
        placeholder="Enter Amount"
        value={amount}
        onChange={(e) => setAmount(e.target.value)}
      />
      <div className="action-buttons">
        <button className="btn-primary" onClick={processDeposit}>
          Deposit
        </button>
        <button
          className="btn-secondary"
          onClick={() => navigateTo("dashboard")}
        >
          Cancel
        </button>
      </div>
      <p className={message.type === "error" ? "error-msg" : "info-msg"}>
        {message.text}
      </p>
    </section>
  );
}
