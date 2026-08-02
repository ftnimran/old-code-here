
import React from 'react';
function About(){


    const style={ backgroundImage:"url('/back.jpg')",
              backgroundSize: 'cover',
              height:'55vh',
            //   weight:'80vw',
              
    };
    return(
        <div style={style } >
            <h1 style={{color:'blue',padding:'20px' , textAlign:'center'}}> About Us</h1>
                 {/* <img src="images.jpg"  width="350px" alt="load..." /> */}
             <p  style={{color:'Black ', fontSize: '18px', padding:'10px', lineheigth:'1.2', border:'2px',  width:'750px',margin:'auto' }}>
                Books are a treasure trove of knowledge, imagination, and inspiration. 
                They open up new worlds, teach us valuable lessons, 
                and allow us to explore the thoughts of great minds. 
                Whether it's a storybook that takes us on magical adventures or a textbook that helps us learn new subjects,
                 books play a crucial role in shaping our understanding of the world. Reading books also improves vocabulary,
                  concentration, and creativity, making them an essential part of personal growth and education.

 </p>
                   

        </div>

        
    )
}
export default About;