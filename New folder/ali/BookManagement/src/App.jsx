import React from "react";
import  Home from "./pages/Home";
import  About from"./pages/About";
import  Contact from"./pages/Contact";
import  Login from "./pages/Login";
import {BrowserRouter, Routes,Route} from "react-router-dom";
import Navbar from "./Components/Navbar";
import "./App.css";
import Signup from "./Components/Signup";
import Addbooks from "./pages/AddBooks";



function App() {
  return(
        <div>
             <BrowserRouter>
             <Navbar />
             <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/about" element={<About />} />
              <Route path="/contact" element={<Contact />}/> 
              <Route path="/login" element={<Login />} />
              <Route path="/signup" element={<Signup/>} />
              <Route path="/add-books" element={<Addbooks/>}/>
               
             </Routes>
             </BrowserRouter>
        </div>
  );
};
export default App;