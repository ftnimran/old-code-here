import React, { useContext } from "react";
import { ATMContext } from "../context/ATMContext";

export default function Header() {
  const { time } = useContext(ATMContext);

  return (
    <header className="atm-header">
      <div className="logo">
        BANK<span>PRO</span>
      </div>
      <div className="time">
        {time.toLocaleTimeString("en-US", {
          hour: "2-digit",
          minute: "2-digit",
          second: "2-digit",
          hour12: true,
        })}
      </div>
    </header>
  );
}
