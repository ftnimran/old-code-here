import React from 'react';
import './Projects.css';

function Projects() {
  const projects = [
    {
      id: 1,
      title: 'E-Commerce Platform',
      description: 'A full-stack e-commerce solution with payment integration, inventory management, and real-time order tracking.',
      technologies: ['React', 'Node.js', 'MongoDB', 'Stripe'],
      link: '#'
    },
    {
      id: 2,
      title: 'Task Management App',
      description: 'Collaborative task management tool with real-time updates, team collaboration features, and analytics dashboard.',
      technologies: ['React', 'Firebase', 'Material-UI'],
      link: '#'
    },
    {
      id: 3,
      title: 'Social Media Analytics',
      description: 'Analytics dashboard that tracks social media metrics, provides insights, and generates automated reports.',
      technologies: ['React', 'Python', 'PostgreSQL', 'Chart.js'],
      link: '#'
    },
    {
      id: 4,
      title: 'Weather Application',
      description: 'Real-time weather app with location-based forecasts, weather alerts, and beautiful UI animations.',
      technologies: ['React', 'API Integration', 'Tailwind CSS'],
      link: '#'
    },
    {
      id: 5,
      title: 'Blog Platform',
      description: 'Blogging platform with CMS, markdown support, comment system, and SEO optimization.',
      technologies: ['Next.js', 'Markdown', 'PostgreSQL', 'Vercel'],
      link: '#'
    },
    {
      id: 6,
      title: 'Fitness Tracker',
      description: 'Mobile-friendly fitness tracking app with workout logging, progress tracking, and personalized recommendations.',
      technologies: ['React Native', 'Firebase', 'Chart.js'],
      link: '#'
    }
  ];

  return (
    <section id="projects" className="projects">
      <h2 className="section-title">Featured Projects</h2>
      <div className="projects-grid">
        {projects.map(project => (
          <div key={project.id} className="project-card">
            <div className="project-header">
              <h3>{project.title}</h3>
            </div>
            <p className="project-description">{project.description}</p>
            <div className="project-tech">
              {project.technologies.map((tech, index) => (
                <span key={index} className="tech-tag">{tech}</span>
              ))}
            </div>
            <a href={project.link} className="project-link">View Project →</a>
          </div>
        ))}
      </div>
    </section>
  );
}

export default Projects;
