# 🚀 LeetCode Sync Script

Automatically fetch, organize, and manage your LeetCode solutions with a single command. This script creates folders, generates README files, opens your solution in VS Code, and pushes everything to GitHub.

---

## 📌 Features

- 📥 Fetch problem data directly from LeetCode (GraphQL)
- 📂 Auto-create structured folders:

  ```
  difficulty/category/problem-slug/
  ```

- 📝 Generate:
  - Problem `README.md`
  - Main `README.md` (table format)

- 💻 Open solution in same VS Code window
- 🔖 Auto-detect category from tags
- 📅 Add submission date automatically
- 🔄 Git automation:
  - git add
  - git commit
  - git push

---

## 📁 Folder Structure Example

```
easy/
 └── array/
      └── two-sum/
           ├── solution.cpp
           └── README.md
```

---

## ⚙️ Requirements

- Node.js installed
- Git installed & configured
- VS Code installed with `code` CLI enabled
- Internet connection

---

## 📦 Installation

1. Clone your repository:

   ```bash
   git clone <your-repo-url>
   cd <repo-name>
   ```

2. Install dependencies:

   ```bash
   npm install axios
   ```

---

## ▶️ Usage

Run the script:

```bash
node leetcode_sync.js
```

---

## 🧾 Input Format

You will be prompted for:

### 1. LeetCode URL

Example:

```
https://leetcode.com/problems/two-sum/
```

### 2. Language

Supported languages:

| Input  | File Name     |
| ------ | ------------- |
| cpp    | solution.cpp  |
| c      | solution.c    |
| java   | Solution.java |
| python | solution.py   |
| js     | solution.js   |
| csharp | Solution.cs   |

---

## ⚡ What Happens After Running?

1. Fetches problem data from LeetCode
2. Creates folder structure
3. Generates:
   - Solution file
   - Problem README

4. Opens solution in same VS Code window
5. Waits for you to paste code
6. Updates main `README.md`
7. Pushes to GitHub automatically

---

## 📊 Main README Format

| #   | Problem | Difficulty | Language | Category | Link | Date |
| --- | ------- | ---------- | -------- | -------- | ---- | ---- |

---

## 🧠 Category Detection

The script auto-detects categories like:

- Array
- DP
- Graph
- Tree
- Greedy
- Sliding Window
- Binary Search
- and more

Fallback: `General`

---

## ✨ Example Flow

```bash
node leetcode_sync.js

Paste LeetCode URL: https://leetcode.com/problems/two-sum/
Language: cpp
```

✔ Folder created
✔ File opened in VS Code
✔ README updated
✔ Changes pushed

---

## ⚠️ Notes

- Duplicate entries are prevented in main README
- Title is automatically shortened for table formatting
- Description is trimmed for readability
- Uses `code -r` to prevent opening a new VS Code window

---

## 🔥 Pro Tips

- Keep your repo clean and structured
- Use consistent language inputs (cpp, js, etc.)
- Commit messages are auto-generated

---

## 📌 Script Name

```
leetcode_sync.js
```

---

## ❤️ Author

Made for efficient LeetCode tracking & GitHub syncing.
