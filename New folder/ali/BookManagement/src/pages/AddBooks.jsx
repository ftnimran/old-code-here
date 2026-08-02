import React, { useState } from "react";
import { ToastContainer, toast } from "react-toastify";
// import "./AddBooks.css";

const AddBooks = () => {
  const [book, setBook] = useState({
    image: "",
    name: "",
    author: "",
    description: "",
  });

  const changeHandler = (e) => {
    let { name, value } = e.target;
    setBook({
      ...book,
      [name]: value,
    });
  };

  const changeImageHandler = (e) => {
    let file = e.target.files[0];
    if (file) {
      let fileRead = new FileReader();

      fileRead.onloadend = () => {
        setBook((prev) => ({
          ...prev,
          image: fileRead.result,
        }));
      };
      fileRead.readAsDataURL(file);
    }
  };

  const submitHandler = (e) => {
    e.preventDefault();
    if (!book.image || !book.name || !book.author || !book.description) {
      toast.error("All Fields Are Required");

   


      

      return;
    }

    let newbooks = { ...book, id: Date.now() };
    let existedBooks = JSON.parse(localStorage.getItem("book-data")) || [];

    let updatedBooks = [...existedBooks, newbooks];

    localStorage.setItem("book-data", JSON.stringify(updatedBooks));
    toast.success("Book Added Successfully");
    setBook({
      image: "",
      name: "",
      author: "",
      description: "",
    });
  };

  let styles = {

    
    form: {
      width: "400px",
      border: "1px solid black",
      boxShadow: "0px 0px 5px black",
      margin:"  60px auto",
      padding: "20px",
      backgroundColor:"sky blue",
      
     
      
    },

    input: {
      width: "100%",
      padding: "6px",
      borderRadius: "5px",
      marginBottom: "8px",
      color:"blue",
      

    },
  

    textarea: {
      width: "100%",
      padding: "6px",
      borderRadius: "5px",
      marginBottom: "8px",
    
    },

    button: {
      width: "100%",
      padding: "6px",
      borderRadius: "5px",
      border: "none",
      backgroundColor: "blue",
      color: "white",
      fontSize: "18px",
      cursor: "pointer",
    },

    h2: {
      textAlign: "center",
      color: "blue",
    },
  };



  return (
  
    <form style={styles.form} onSubmit={submitHandler}  >
      <h2 style={styles.h2}>Add Books</h2>

      <label htmlFor="book-image " color="red">Book Image:</label>
      <input
        type="file"
        accept="image/*"
        style={styles.input}
        onChange={changeImageHandler}
      />

      <label htmlFor="book-name" >Book Name:</label>
      <input
        type="text"
        name="name"
        id="book-name"
        placeholder="Enter Book Name"
        style={styles.input}
        value={book.name}
        onChange={changeHandler}
      />

      <label htmlFor="author-name">Author Name:</label>
      <input
        type="text"
        name="author"
        id="author-name"
        placeholder="Enter Author Name"
        style={styles.input}
        value={book.author}
        onChange={changeHandler}
      />

      <label htmlFor="book-description">Book Description:</label>
      <textarea
        name="description"
        id="book-description"
        placeholder="Enter Book Description"
        style={styles.textarea}
        value={book.description}
        onChange={changeHandler}
      ></textarea>

      <button type="submit" style={styles.button}>
        Add Books
      </button>

      <ToastContainer autoClose={2500} position="top-center" />
    </form>
    
  );
};

export default AddBooks;
