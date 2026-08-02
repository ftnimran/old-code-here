const fs = require('fs');
const path = require('path');

const directories = [
  'e:\\GitHub Repo\\portfolio',
  'e:\\GitHub Repo\\portfolio\\public',
  'e:\\GitHub Repo\\portfolio\\src',
  'e:\\GitHub Repo\\portfolio\\src\\components'
];

try {
  directories.forEach(dir => {
    if (!fs.existsSync(dir)) {
      fs.mkdirSync(dir, { recursive: true });
      console.log(`✓ Created: ${dir}`);
    } else {
      console.log(`✓ Already exists: ${dir}`);
    }
  });
  console.log('\n✓ All directories created successfully!');
} catch (error) {
  console.error('Error creating directories:', error.message);
}
