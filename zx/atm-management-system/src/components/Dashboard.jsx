import React, { useContext } from "react";
import { ATMContext } from "../context/ATMContext";

export default function Dashboard() {
  const { navigateTo } = useContext(ATMContext);

  return (
    <section className="screen active">
      <h2>Main Menu</h2>
      <p>Select a transaction</p>
      <div className="grid-menu">
        <button onClick={() => navigateTo("balance")}>Check Balance</button>
        <button onClick={() => navigateTo("withdraw")}>Withdraw Cash</button>
        <button onClick={() => navigateTo("deposit")}>Deposit Cash</button>
        <button onClick={() => navigateTo("statement")}>Mini Statement</button>
        <button onClick={() => navigateTo("changepin")}>Change PIN</button>
        <button className="btn-exit" onClick={() => navigateTo("exit")}>
          Exit
        </button>
      </div>
    </section>
  );
}
