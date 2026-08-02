
import React, { useState } from "react";
import { ToastContainer, toast } from "react-toastify";

function Home() {
  let storedBooks = JSON.parse(localStorage.getItem("book-data")) || [];

  let favBooks = JSON.parse(localStorage.getItem("Fav-Books")) || [];

  let [bookData, setBookData] = useState(storedBooks);


   
  let styles = {
  
    cardBox: {
      display: "flex",
      gap: "10px",
        
      
    },

    card: {
      border: "2px solid black",
      width: "400px",
      height:"470px",
      padding:"40px",
      marginTop:"50px ",
      margin:" 50px auto",
        boxShadow: "0px 0px 5px black",
      
    },
    image: {
      padding:"2px",
      width: "100%",
      height:"70%",
      margginbottom:"60px"
    
    },
    
    
  };
 
//Remove Books
  const removeHandler=(book)=> {
    const updateBooks =bookData.filter ((b) => b.name !==book.name);
    setBookData(updateBooks);
    localStorage.setItem("book-data",
      JSON.stringify(updateBooks));
  };

  //Add To Fav
  const favHandler =(book)=>{
    const  isAlreadyFav = favBooks.find((b) => b.name === book.name);
    if(isAlreadyFav){
      toast.warning("Book  Added To Fav");
      return;
    }
    
       const updateBook =[...favBooks,book];
    localStorage.setItem("fav-books",JSON.stringify(updateBooks));
    toast.success("Book Added To Fav");
  }
  
  const updateHandler = (book) => {
    const updatedBook = bookData.filter((b) => b.name !== book.name);
    setBookData(updatedBook);
    localStorage.setItem("book-item", JSON.stringify(updatedBook));
  }
  return (
           
    <div 
  
    style={styles.cardBox}>
      { bookData.map((books) => (
          
        <div key={books.id} style={styles.card}    >
         

          <img src=    {books.image} alt={books.name} style={styles.image}   />
        
          <h3 className="text-dark text-center">{books.name}</h3>
          
            <h5 className="text-dark text-center border border-dark">{books.author}</h5>
          <p   className="border border-dark ">{books.description}</p>
          <button onClick={() =>updateHandler(books)}>Update Book</button>
          <button onClick={()  =>removeHandler(books)} > Remove Book</button>
          <button onClick={()  => favHandler(books)}> Add To Fav </button>
        </div>
        
      ))}

<ToastContainer autoClose={1000} position ="top-center"/>

    </div>
  );
}

export default Home;
