# 🚀 LeetCode Auto Push Script (`push_leetcode.py`)

This script helps you **automatically fetch LeetCode problems**, create structured folders, generate README files, and push your solutions to GitHub — all in one command.

Perfect for building a **clean and professional LeetCode repository** 🔥

---

## 📌 What This Script Does

When you run `push_leetcode.py`, it will:

✅ Take LeetCode problem URL as input
✅ Fetch problem details using GraphQL API
✅ Create organized folder structure
✅ Generate:

- Solution file (based on language)
- Problem README.md

✅ Open solution file in VS Code
✅ Update main `README.md` (progress tracker)
✅ Auto commit & push to GitHub 🚀

---

## 📂 Project Structure

After running the script, your repo will look like:

```bash
.
├── array/
│   └── two-sum/
│       ├── solution.cpp
│       └── README.md
│
├── string/
│   └── valid-palindrome/
│       ├── solution.py
│       └── README.md
│
└── README.md
```

---

## ⚙️ Requirements

Make sure you have:

- Python 3.x
- Git installed
- VS Code installed
- Internet connection

Install dependency:

```bash
pip install requests
```

---

## ▶️ How to Use

### Step 1: Run Script

```bash
python push_leetcode.py
```

---

### Step 2: Enter Inputs

Paste LeetCode problem URL:

```
https://leetcode.com/problems/two-sum/
```

Enter language:

```
cpp / c / java / python / js / csharp
```

---

### Step 3: Write Your Code

- Script will open solution file in VS Code
- Paste your solution
- Press **ENTER in terminal**

---

### Step 4: Done 🎉

Script will:

- Update main README
- Commit changes
- Push to GitHub automatically 🚀

---

## 📊 Main README Format

Your main `README.md` will automatically maintain this table:

| #   | Problem | Difficulty | Language | Category | Link | Date       |
| --- | ------- | ---------- | -------- | -------- | ---- | ---------- |
| 1   | Two Sum | Easy       | CPP      | Array    | Link | 2026-03-26 |

---

## 🧠 How It Works

- Uses **LeetCode GraphQL API** to fetch:
  - Title
  - Difficulty
  - Description
  - Tags

- Converts HTML problem statement → clean text

- Uses tags to create category folders

- Prevents duplicate entries in README

---

## 💡 Features Explained

### 📁 Auto Folder Creation

Problems are stored like:

```
category/problem-slug/
```

---

### 📝 Problem README Auto-Generated

Each problem gets:

- Description
- Tags
- Difficulty
- Space for approach & complexity

---

### 📊 Smart README Update

- Counts problems automatically
- Avoids duplicates
- Adds date

---

### 🚀 Git Integration

Automatically runs:

```bash
git add .
git commit -m "Added Problem"
git push
```

---

## ⚠️ Important Notes

- Make sure your Git repo is already initialized
- You must be logged in to GitHub
- VS Code command `code` must be available in PATH
- Internet required for API calls

---

## 🔥 Future Improvements (Optional)

You can upgrade this project by adding:

- Selenium auto-submit tracking
- Chrome Extension (like LeetHub)
- Multi-language support per problem
- Code auto-fetch from LeetCode submissions
- UI dashboard

---

## 🤝 Contribution

Feel free to fork and improve this script!

---

## ⭐ Support

If this helped you, give it a ⭐ on GitHub 🙌

---
