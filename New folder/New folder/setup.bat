@echo off
setlocal enabledelayedexpansion

REM Navigate to GitHub Repo
cd /d "e:\GitHub Repo"

REM Delete the portfolio file if it exists
if exist portfolio (
    del portfolio
)

REM Create the directory structure
mkdir portfolio\public
mkdir portfolio\src\components

REM Create package.json
(
echo {
echo   "name": "portfolio",
echo   "version": "0.1.0",
echo   "private": true,
echo   "dependencies": {
echo     "react": "^18.2.0",
echo     "react-dom": "^18.2.0",
echo     "react-scripts": "5.0.1"
echo   },
echo   "scripts": {
echo     "start": "react-scripts start",
echo     "build": "react-scripts build",
echo     "test": "react-scripts test",
echo     "eject": "react-scripts eject"
echo   },
echo   "eslintConfig": {
echo     "extends": ["react-app"]
echo   },
echo   "browserslist": {
echo     "production": [">0.2%%", "not dead", "not op_mini all"],
echo     "development": ["last 1 chrome version", "last 1 firefox version", "last 1 safari version"]
echo   }
echo }
) > portfolio\package.json

echo Directory structure created successfully!
echo.
echo Next steps:
echo 1. Navigate to portfolio: cd portfolio
echo 2. Run npm install
echo 3. Copy the component files to src\components
