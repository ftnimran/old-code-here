#!/usr/bin/env python3
import os
import sys

base_path = r'e:\GitHub Repo\portfolio'

# Create directories
dirs = [
    os.path.join(base_path, 'src', 'components'),
    os.path.join(base_path, 'public')
]

for dir_path in dirs:
    os.makedirs(dir_path, exist_ok=True)
    print(f"✓ Created: {dir_path}")

# Create package.json
package_json = r"""{
  "name": "portfolio",
  "version": "1.0.0",
  "description": "A professional React portfolio website",
  "private": true,
  "dependencies": {
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "react-scripts": "5.0.1",
    "web-vitals": "^2.1.4"
  },
  "scripts": {
    "start": "react-scripts start",
    "build": "react-scripts build",
    "test": "react-scripts test",
    "eject": "react-scripts eject"
  },
  "eslintConfig": {
    "extends": [
      "react-app"
    ]
  },
  "browserslist": {
    "production": [
      ">0.2%",
      "not dead",
      "not op_mini all"
    ],
    "development": [
      "last 1 chrome version",
      "last 1 firefox version",
      "last 1 safari version"
    ]
  }
}"""

with open(os.path.join(base_path, 'package.json'), 'w') as f:
    f.write(package_json)
print(f"✓ Created: package.json")

print("\nDirectory structure created successfully!")
