#!/usr/bin/env python3
import os
import json
from pathlib import Path

project_root = r"e:\GitHub Repo\portfolio"

# Create base directories
os.makedirs(os.path.join(project_root, "public"), exist_ok=True)
os.makedirs(os.path.join(project_root, "src", "components"), exist_ok=True)

files = {
    "package.json": {
        "name": "portfolio",
        "version": "0.1.0",
        "private": True,
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
    },
}

# Create package.json
with open(os.path.join(project_root, "package.json"), 'w') as f:
    json.dump(files["package.json"], f, indent=2)
    print(f"Created: package.json")

print("Setup completed!")
