#!/usr/bin/env node
const fs = require('fs');
const path = require('path');

const projectRoot = path.join(__dirname, '..', 'portfolio');

// Define all file paths and contents
const files = {
  'package.json': `{
  "name": "portfolio",
  "version": "0.1.0",
  "private": true,
  "dependencies": {
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "react-scripts": "5.0.1"
  },
  "scripts": {
    "start": "react-scripts start",
    "build": "react-scripts build",
    "test": "react-scripts test",
    "eject": "react-scripts eject"
  },
  "eslintConfig": {
    "extends": ["react-app"]
  },
  "browserslist": {
    "production": [">0.2%", "not dead", "not op_mini all"],
    "development": ["last 1 chrome version", "last 1 firefox version", "last 1 safari version"]
  }
}`,
  '.gitignore': `# dependencies
/node_modules
/.pnp
.pnp.js

# testing
/coverage

# production
/build

# misc
.DS_Store
.env.local
.env.development.local
.env.test.local
.env.production.local

npm-debug.log*
yarn-debug.log*
yarn-error.log*

# IDEs
.vscode/
.idea/
*.swp
*.swo
`,
  'README.md': `# Portfolio Website

A professional portfolio website built with React.

## Getting Started

### Prerequisites
- Node.js and npm installed on your machine

### Installation

\`\`\`bash
npm install
\`\`\`

### Running the Application

\`\`\`bash
npm start
\`\`\`

The application will open at [http://localhost:3000](http://localhost:3000) in your browser.

### Building for Production

\`\`\`bash
npm build
\`\`\`

This creates an optimized production build in the \`build\` folder.

### Testing

\`\`\`bash
npm test
\`\`\`

## Project Structure

\`\`\`
portfolio/
├── public/
│   └── index.html
├── src/
│   ├── components/
│   │   ├── Header.js
│   │   ├── Header.css
│   │   ├── Hero.js
│   │   ├── Hero.css
│   │   ├── About.js
│   │   ├── About.css
│   │   ├── Projects.js
│   │   ├── Projects.css
│   │   ├── Contact.js
│   │   ├── Contact.css
│   │   ├── Footer.js
│   │   └── Footer.css
│   ├── App.js
│   ├── App.css
│   ├── index.js
│   └── index.css
├── .gitignore
├── package.json
└── README.md
\`\`\`

## Components

- **Header**: Navigation component with links to different sections
- **Hero**: Landing section with introduction
- **About**: About me section
- **Projects**: Showcase of projects
- **Contact**: Contact information and form
- **Footer**: Footer with additional links

## License

This project is open source and available under the MIT License.
`,
  'public/index.html': `<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta name="theme-color" content="#000000" />
    <meta name="description" content="A professional portfolio website built with React" />
    <title>Portfolio</title>
  </head>
  <body>
    <noscript>You need to enable JavaScript to run this app.</noscript>
    <div id="root"></div>
  </body>
</html>
`,
  'src/index.js': `import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
`,
  'src/index.css': `* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

html {
  scroll-behavior: smooth;
}

body {
  font-family: 'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  background-color: #f5f5f5;
  color: #333;
}

code {
  font-family: 'Courier New', monospace;
}
`,
  'src/App.js': `import React from 'react';
import './App.css';
import Header from './components/Header';
import Hero from './components/Hero';
import About from './components/About';
import Projects from './components/Projects';
import Contact from './components/Contact';
import Footer from './components/Footer';

function App() {
  return (
    <div className="App">
      <Header />
      <Hero />
      <About />
      <Projects />
      <Contact />
      <Footer />
    </div>
  );
}

export default App;
`,
  'src/App.css': `.App {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

main {
  flex: 1;
}

section {
  padding: 60px 20px;
  max-width: 1200px;
  margin: 0 auto;
  width: 100%;
}

h1 {
  font-size: 2.5rem;
  font-weight: bold;
  margin-bottom: 20px;
  color: #2c3e50;
}

h2 {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 30px;
  color: #2c3e50;
  text-align: center;
}

h3 {
  font-size: 1.5rem;
  font-weight: 600;
  color: #34495e;
}

p {
  line-height: 1.8;
  color: #555;
  font-size: 1rem;
}

a {
  color: #3498db;
  text-decoration: none;
  transition: color 0.3s ease;
}

a:hover {
  color: #2980b9;
}

button {
  padding: 12px 24px;
  font-size: 1rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.3s ease;
}

button:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

@media (max-width: 768px) {
  h1 {
    font-size: 2rem;
  }

  h2 {
    font-size: 1.5rem;
  }

  section {
    padding: 40px 15px;
  }
}
`,
};

// Create directory structure
function ensureDir(dir) {
  if (!fs.existsSync(dir)) {
    fs.mkdirSync(dir, { recursive: true });
  }
}

// Main setup
try {
  console.log(`Setting up portfolio project in ${projectRoot}...`);
  
  // Create all directories
  ensureDir(projectRoot);
  ensureDir(path.join(projectRoot, 'public'));
  ensureDir(path.join(projectRoot, 'src'));
  ensureDir(path.join(projectRoot, 'src', 'components'));
  
  // Create all files
  Object.entries(files).forEach(([filePath, content]) => {
    const fullPath = path.join(projectRoot, filePath);
    const dir = path.dirname(fullPath);
    ensureDir(dir);
    fs.writeFileSync(fullPath, content);
    console.log(`✓ Created: ${filePath}`);
  });
  
  console.log('\n✓ Base files created! Now creating component files...');
} catch (err) {
  console.error('Error during setup:', err);
  process.exit(1);
}
