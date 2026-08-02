import React, { useState, useContext } from "react";
import { ATMContext } from "../context/ATMContext";

export default function Login() {
  const { pin, navigateTo } = useContext(ATMContext);
  const [inputPin, setInputPin] = useState("");
  const [errorMsg, setErrorMsg] = useState("");

  const handlePinInput = (digit) => {
    if (inputPin.length < 4) {
      setInputPin((prev) => prev + digit);
      setErrorMsg("");
    }
  };

  const validateLogin = () => {
    if (inputPin === pin) {
      navigateTo("dashboard");
    } else {
      setErrorMsg("Incorrect PIN. Try again.");
      setInputPin("");
    }
  };

  return (
    <section className="screen active">
      <h2>Security Check</h2>
      <p>Please enter your 4-digit PIN</p>
      <div className="input-display">
        <input
          type="password"
          maxLength="4"
          placeholder="****"
          value={inputPin}
          readOnly
        />
      </div>
      <div className="keypad">
        {[1, 2, 3, 4, 5, 6, 7, 8, 9].map((num) => (
          <button key={num} onClick={() => handlePinInput(num.toString())}>
            {num}
          </button>
        ))}
        <button className="btn-clear" onClick={() => setInputPin("")}>
          C
        </button>
        <button onClick={() => handlePinInput("0")}>0</button>
        <button className="btn-enter" onClick={validateLogin}>
          OK
        </button>
      </div>
      <p className="error-msg">{errorMsg}</p>
    </section>
  );
}
