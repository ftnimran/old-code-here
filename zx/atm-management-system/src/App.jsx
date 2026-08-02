import React, { useContext } from "react";
import { ATMContext } from "./context/ATMContext";

import Header from "./components/Header";
import Footer from "./components/Footer";
import Welcome from "./components/Welcome";
import Login from "./components/Login";
import Dashboard from "./components/Dashboard";
import Balance from "./components/Balance";
import Withdraw from "./components/Withdraw";
import Deposit from "./components/Deposit";
import ChangePin from "./components/ChangePin";
import Statement from "./components/Statement";
import Exit from "./components/Exit";

const SCREEN_COMPONENTS = {
  welcome: Welcome,
  login: Login,
  dashboard: Dashboard,
  balance: Balance,
  withdraw: Withdraw,
  deposit: Deposit,
  changepin: ChangePin,
  statement: Statement,
  exit: Exit,
};

export default function App() {
  const { currentScreen } = useContext(ATMContext);

  const ActiveScreen =
    SCREEN_COMPONENTS[currentScreen] || SCREEN_COMPONENTS.welcome;

  return (
    <div className="atm-container">
      <Header />
      <main>
        <ActiveScreen />
      </main>
      <Footer />
    </div>
  );
}
