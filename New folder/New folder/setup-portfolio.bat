@echo off
REM Create directory structure
mkdir "e:\GitHub Repo\portfolio\src\components" 2>nul
mkdir "e:\GitHub Repo\portfolio\public" 2>nul

REM Create package.json
(
echo {
echo   "name": "portfolio",
echo   "version": "1.0.0",
echo   "description": "A professional React portfolio website",
echo   "private": true,
echo   "dependencies": {
echo     "react": "^18.2.0",
echo     "react-dom": "^18.2.0",
echo     "react-scripts": "5.0.1",
echo     "web-vitals": "^2.1.4"
echo   },
echo   "scripts": {
echo     "start": "react-scripts start",
echo     "build": "react-scripts build",
echo     "test": "react-scripts test",
echo     "eject": "react-scripts eject"
echo   },
echo   "eslintConfig": {
echo     "extends": [
echo       "react-app"
echo     ]
echo   },
echo   "browserslist": {
echo     "production": [
echo       ">0.2%%",
echo       "not dead",
echo       "not op_mini all"
echo     ],
echo     "development": [
echo       "last 1 chrome version",
echo       "last 1 firefox version",
echo       "last 1 safari version"
echo     ]
echo   }
echo }
) > "e:\GitHub Repo\portfolio\package.json"

echo Portfolio setup batch created
