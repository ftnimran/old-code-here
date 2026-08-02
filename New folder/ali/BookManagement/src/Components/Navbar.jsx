import React from "react";
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap.bundle.min.js"
import { Link } from "react-router-dom";
function Navbar(){
    return(
         <nav className="navbar navbar-expand-sm bg-dark navbar-dark p-3">
            <Link to="#" className="navbar-brand">
            Book Management
            </Link>

            <ul className="navbar-nav ms-auto">
                <li className="nav-item">
                  <Link to="/" className="nav-link fs-5">
                  Home
                  </Link>
                </li>
                  <li className="nav-item">
                          <Link to="/add-books" className="nav-link fs-5">
                            Add Books
                          </Link>
                        </li>


                <li className="nav-item">
                  <Link to="/about" className="nav-link fs-5">
                  About
                  
                </Link>
                </li>


                <li className="nav-item">
                  <Link to="/contact" className="nav-link fs-5">
                  Contact
                  </Link>
                </li>


                <li className="nav-item">
                  <Link to="/login" className="btn btn-danger fs-5 ms-3">
                  Login
                  </Link>
                </li>
            </ul>
         </nav>
         
           
         
    );
}
export default Navbar;