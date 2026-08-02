
import React from 'react';
import "./Login.css";
import { Link} from 'react-router-dom';


function Login(){
  
   return (
      <form className="login-form">
        <h1>Login to Book-App</h1>
        <label htmlFor="email">Email: </label>
        <input type="email" placeholder="Enter Your Email" id="email" />
  
        <label htmlFor="pass">Password: </label>
        <input type="password" placeholder="Enter Your Password" id="pass" />
  
        <button>Login</button>
        <p>
          <Link to="/signup">Didn't Have an Account</Link>
        </p>
      </form>
    );
}
export default Login; 