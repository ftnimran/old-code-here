import React, { useContext } from "react";
import { ATMContext } from "../context/ATMContext";

export default function Welcome() {
  const { navigateTo } = useContext(ATMContext);

  return (
    <section className="screen active">
      <h1>Welcome to BankPro ATM</h1>
      <p>Please insert your card to continue</p>
      <div className="card-slot-area">
        <button
          className="btn-primary pulse"
          onClick={() => navigateTo("login")}
        >
          Insert Card
        </button>
      </div>
    </section>
  );
}
