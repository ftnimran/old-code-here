import React from 'react';
import './About.css';

function About() {
  return (
    <section id="about" className="about">
      <h2 className="section-title">About Me</h2>
      <div className="about-content">
        <div className="about-text">
          <p>
            I'm a passionate full-stack developer with expertise in building modern web applications. 
            I love turning complex problems into simple, beautiful, and intuitive designs.
          </p>
          <p>
            With 3+ years of experience in web development, I've worked with startups and established companies 
            to create solutions that drive business growth and user satisfaction.
          </p>
          <p>
            When I'm not coding, you can find me exploring new technologies, contributing to open-source projects, 
            or sharing my knowledge with the developer community.
          </p>
          <div className="about-stats">
            <div className="stat">
              <h3>50+</h3>
              <p>Projects Completed</p>
            </div>
            <div className="stat">
              <h3>30+</h3>
              <p>Happy Clients</p>
            </div>
            <div className="stat">
              <h3>3+</h3>
              <p>Years Experience</p>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}

export default About;
