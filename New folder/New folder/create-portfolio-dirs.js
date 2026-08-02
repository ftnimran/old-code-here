const fs = require('fs');
const path = require('path');

const baseDir = 'e:\\GitHub Repo\\portfolio';
const dirs = [
  baseDir,
  path.join(baseDir, 'public'),
  path.join(baseDir, 'src'),
  path.join(baseDir, 'src', 'components')
];

dirs.forEach(dir => {
  if (!fs.existsSync(dir)) {
    fs.mkdirSync(dir, { recursive: true });
    console.log(`Created: ${dir}`);
  }
});

console.log('All directories created successfully!');
