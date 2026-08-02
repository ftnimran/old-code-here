
import React from "react";
function Contact() {
    return (
        <div className="border border-dark rounded-4 w-50 d-grid gap-3 mt-5 text-center mx-auto shadow-lg p-5">
            <h1 className=" text-primary">Contact Us</h1>
            <div className="d-flex gap-5">
                <input type="text" placeholder="First Name" />
                <input type="text" placeholder="Last Name" />
            </div>
            <div className="d-flex gap-5">
                <input type="number" placeholder="Mobile Number" />
                <input type="email" placeholder="Email ID" />
            </div>
            <textarea placeholder="Message" rows={3} cols={40}></textarea>
            <button className="btn btn-outline-primary w-25 mx-auto">Submit</button>
        </div>
    )
}
export default Contact;