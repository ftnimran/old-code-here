const fs = require('fs');
const path = require('path');

// Create directories
const basePath = 'e:\\GitHub Repo\\portfolio';
const dirs = [
  path.join(basePath, 'src', 'components'),
  path.join(basePath, 'public')
];

dirs.forEach(dir => {
  fs.mkdirSync(dir, { recursive: true });
});

console.log('✓ All directories created successfully');
