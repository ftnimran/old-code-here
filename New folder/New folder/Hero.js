import React from 'react';
import './Hero.css';

function Hero() {
  return (
    <section className="hero">
      <div className="hero-content">
        <h2 className="hero-greeting">Hello, I'm</h2>
        <h1 className="hero-name">Your Name</h1>
        <p className="hero-subtitle">Full Stack Developer | Creative Problem Solver</p>
        <p className="hero-description">
          I create beautiful and functional websites that help businesses grow and users succeed.
        </p>
        <div className="hero-buttons">
          <button className="btn-primary">View My Work</button>
          <button className="btn-secondary">Get In Touch</button>
        </div>
      </div>
      <div className="hero-animation">
        <div className="floating-box"></div>
      </div>
    </section>
  );
}

export default Hero;
