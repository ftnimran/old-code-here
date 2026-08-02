import React, { createContext, useState, useEffect } from "react";

export const ATMContext = createContext();

export const ATMProvider = ({ children }) => {
  const [currentScreen, setCurrentScreen] = useState("welcome");
  const [balance, setBalance] = useState(5000.0);
  const [pin, setPin] = useState("1234");
  const [transactions, setTransactions] = useState([
    {
      date: new Date().toLocaleDateString(),
      type: "Deposit",
      amount: "5000.00",
    },
  ]);
  const [time, setTime] = useState(new Date());

  // Update clock every second
  useEffect(() => {
    const timer = setInterval(() => setTime(new Date()), 1000);
    return () => clearInterval(timer);
  }, []);

  // Handle auto-exit logic
  useEffect(() => {
    if (currentScreen === "exit") {
      const timer = setTimeout(() => {
        setCurrentScreen("welcome");
      }, 3000);
      return () => clearTimeout(timer);
    }
  }, [currentScreen]);

  const navigateTo = (screenId) => {
    setCurrentScreen(screenId);
  };

  const addTransaction = (type, amount) => {
    const date = new Date().toLocaleDateString();
    setTransactions((prev) => [
      { date, type, amount: amount.toFixed(2) },
      ...prev,
    ]);
  };

  return (
    <ATMContext.Provider
      value={{
        currentScreen,
        navigateTo,
        balance,
        setBalance,
        pin,
        setPin,
        transactions,
        addTransaction,
        time,
      }}
    >
      {children}
    </ATMContext.Provider>
  );
};
