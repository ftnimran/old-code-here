import React, { useState, useContext } from "react";
import { ATMContext } from "../context/ATMContext";

export default function Withdraw() {
  const { balance, setBalance, addTransaction, navigateTo } =
    useContext(ATMContext);
  const [amount, setAmount] = useState("");
  const [message, setMessage] = useState({ text: "", type: "" });

  const processWithdraw = () => {
    const val = parseFloat(amount);
    if (isNaN(val) || val <= 0) {
      return setMessage({
        text: "Please enter a valid amount.",
        type: "error",
      });
    }
    if (val > balance) {
      return setMessage({ text: "Insufficient Funds!", type: "error" });
    }

    setBalance((prev) => prev - val);
    addTransaction("Withdraw", val);
    setMessage({ text: "Withdrawal Successful!", type: "success" });
    setTimeout(() => navigateTo("dashboard"), 1500);
  };

  return (
    <section className="screen active">
      <h2>Withdraw Cash</h2>
      <p>Enter amount to withdraw</p>
      <input
        type="number"
        placeholder="Enter Amount"
        value={amount}
        onChange={(e) => setAmount(e.target.value)}
      />
      <div className="quick-cash">
        {[20, 50, 100].map((amt) => (
          <button key={amt} onClick={() => setAmount(amt.toString())}>
            ${amt}
          </button>
        ))}
      </div>
      <div className="action-buttons">
        <button className="btn-primary" onClick={processWithdraw}>
          Confirm
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
