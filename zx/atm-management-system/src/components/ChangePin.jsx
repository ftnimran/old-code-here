import React, { useState, useContext } from "react";
import { ATMContext } from "../context/ATMContext";

export default function ChangePin() {
  const { pin, setPin, navigateTo } = useContext(ATMContext);
  const [oldPin, setOldPin] = useState("");
  const [newPin, setNewPin] = useState("");
  const [confirmPin, setConfirmPin] = useState("");
  const [message, setMessage] = useState({ text: "", type: "" });

  const processChangePin = () => {
    if (oldPin !== pin) {
      return setMessage({ text: "Old PIN is incorrect.", type: "error" });
    }
    if (newPin.length !== 4 || isNaN(Number(newPin))) {
      return setMessage({ text: "New PIN must be 4 digits", type: "error" });
    }
    if (newPin !== confirmPin) {
      return setMessage({ text: "New PINs do not match.", type: "error" });
    }

    setPin(newPin);
    setMessage({ text: "PIN Changed Successfully!", type: "success" });
    setTimeout(() => navigateTo("dashboard"), 1500);
  };

  return (
    <section className="screen active">
      <h2>Change PIN</h2>
      <input
        type="password"
        maxLength="4"
        placeholder="Old PIN"
        value={oldPin}
        onChange={(e) => setOldPin(e.target.value)}
      />
      <input
        type="password"
        maxLength="4"
        placeholder="New PIN"
        value={newPin}
        onChange={(e) => setNewPin(e.target.value)}
      />
      <input
        type="password"
        maxLength="4"
        placeholder="Confirm New PIN"
        value={confirmPin}
        onChange={(e) => setConfirmPin(e.target.value)}
      />

      <div className="action-buttons">
        <button className="btn-primary" onClick={processChangePin}>
          Update
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
