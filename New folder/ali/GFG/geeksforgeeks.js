const fs = require("fs");
const path = require("path");
const axios = require("axios");
const readline = require("readline");
const { execSync } = require("child_process");

// ===== INPUT =====
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

function ask(q) {
  return new Promise((resolve) => rl.question(q, (ans) => resolve(ans)));
}

// ===== LANGUAGE MAP =====
const langMap = {
  cpp: "solution.cpp",
  c: "solution.c",
  java: "Solution.java",
  python: "solution.py",
  js: "solution.js",
  csharp: "Solution.cs",
};

const displayLangMap = { cpp: "C++", js: "JavaScript", python: "Python3" };

// ===== CATEGORY =====
function getCategory(tags, title) {
  const tagsLower = (tags || []).map((t) => t.toLowerCase());
  const titleLower = (title || "").toLowerCase();

  const mapping = [
    ["dynamic programming", "DP"],
    ["segment tree", "Segment Tree"],
    ["fenwick tree", "Binary Indexed Tree"],
    ["graph", "Graph"],
    ["topological sort", "Graph"],
    ["trie", "Trie"],
    ["union find", "Union Find"],
    ["backtracking", "Backtracking"],
    ["recursion", "Backtracking"],
    ["tree", "Tree"],
    ["binary search tree", "Tree"],
    ["linked list", "Linked List"],
    ["monotonic stack", "Monotonic Stack"],
    ["sliding window", "Sliding Window"],
    ["two pointers", "Two Pointer"],
    ["prefix sum", "Prefix Sum"],
    ["binary search", "Binary Search"],
    ["heap", "Heap"],
    ["greedy", "Greedy"],
    ["stack", "Stack"],
    ["queue", "Queue"],
    ["hash table", "Hashing"],
    ["bit manipulation", "Bit Manipulation"],
    ["matrix", "Matrix"],
    ["geometry", "Geometry"],
    ["game theory", "Game Theory"],
    ["database", "SQL"],
    ["shell", "Shell"],
    ["design", "Design"],
    ["array", "Array"],
    ["string", "String"],
    ["math", "Math"],
    ["simulation", "Simulation"],
    ["sorting", "Sorting"],
  ];

  for (let [key, val] of mapping) {
    if (tagsLower.includes(key) || titleLower.includes(key)) return val;
  }

  if (titleLower.includes("counter") || titleLower.includes("closure"))
    return "Closure";
  if (titleLower.includes("promise")) return "Promise";
  if (titleLower.includes("function")) return "Basics";

  return "General";
}

// ===== SAFE NAME =====
function safeName(text) {
  if (!text) return "unknown";
  return text
    .toLowerCase()
    .replace(/\s+/g, "-")
    .replace(/[^a-z0-9\-]/g, "");
}

// ===== TITLE FORMAT =====
function formatTitle(title, maxLength = 37) {
  if (!title) return "Problem";
  title = title.replace(/\|/g, "");
  return title.length <= maxLength
    ? title
    : title.slice(0, maxLength - 3) + "...";
}

// ===== CLEAN HTML =====
function cleanHTML(html) {
  if (!html) return "No description available.";
  return html.replace(/<[^>]+>/g, "").trim();
}

// ===== DATE =====
function getDate() {
  const d = new Date();
  const dd = String(d.getDate()).padStart(2, "0");
  const mm = String(d.getMonth() + 1).padStart(2, "0");
  const yy = String(d.getFullYear()).slice(-2);
  return `${dd}-${mm}-${yy}`;
}

// ===== MAIN =====
(async () => {
  const urlInput = (await ask("Paste GeeksforGeeks URL: ")).trim();
  const language = (await ask("Language: ")).trim().toLowerCase();

  if (!langMap[language]) {
    console.log("❌ Invalid language!");
    process.exit();
  }

  const displayLanguage = displayLangMap[language] || language.toUpperCase();

  const match = urlInput.match(/problems\/([^/]+)/);
  if (!match) {
    console.log("❌ Invalid URL!");
    process.exit();
  }

  const slug = match[1];

  // ===== FETCH GeeksforGeeks DATA =====
  console.log("⏳ Fetching GeeksforGeeks problem details...");
  let problemData;
  try {
    const res = await axios.get(
      `https://practiceapi.geeksforgeeks.org/api/v1/problems/${slug}/`,
      {
        headers: {
          "User-Agent":
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36",
          Referer: "https://www.geeksforgeeks.org/",
        },
      },
    );
    problemData = res.data.results;
  } catch (e) {
    console.log("❌ Error fetching from GeeksforGeeks API!");
    process.exit();
  }

  if (!problemData) {
    console.log("❌ Problem not found!");
    process.exit();
  }

  const title = problemData.title || slug.replace(/-/g, " ");
  const difficulty = (problemData.difficulty || "medium").toLowerCase();
  const tags = problemData.category ? [problemData.category] : [];
  const link = urlInput.split("?")[0];
  const category = getCategory(tags, title);

  // ===== PATH (Changed: No more 'GeeksforGeeks' parent folder) =====
  const folder = path.join(difficulty, safeName(category), slug);
  fs.mkdirSync(folder, { recursive: true });

  const solutionPath = path.join(folder, langMap[language]);

  if (!fs.existsSync(solutionPath)) {
    fs.writeFileSync(
      solutionPath,
      `// ${title}\n// ${link}\n// Difficulty: ${difficulty}\n\n`,
    );
  }

  console.log("✅ Folder ready:", folder);

  // ===== README (PROBLEM) =====
  const desc = cleanHTML(
    problemData.problem_explanation || problemData.description,
  );
  const shortDesc = desc.length > 800 ? desc.slice(0, 800) + "..." : desc;

  const problemReadme = path.join(folder, "README.md");

  if (!fs.existsSync(problemReadme)) {
    fs.writeFileSync(
      problemReadme,
      `# ${title}

🔗 [Problem Link](${link})
📊 Difficulty: ${difficulty}
📂 Category: ${category}

## 📝 Description
${shortDesc}`,
    );
  }

  // ===== VS CODE (SAME WINDOW) =====
  try {
    execSync(`code -r "${solutionPath}"`);
  } catch {}

  await ask("Paste your code and press ENTER...");

  // ===== MAIN README =====
  const mainReadme = "README.md";

  if (!fs.existsSync(mainReadme)) {
    fs.writeFileSync(
      mainReadme,
      `# 🚀 GeeksforGeeks Solutions

| # | Problem | Difficulty | Language | Category | Link | Date |
|---|--------|------------|----------|----------|------|------|
`,
    );
  }

  let lines = fs.readFileSync(mainReadme, "utf-8").split("\n");

  if (lines.some((line) => line.includes(`/${slug}/`))) {
    console.log("⚠️ Already exists in README.");
  } else {
    const count = lines.filter(
      (l) => l.startsWith("|") && !l.includes("Problem") && !l.includes("---"),
    ).length;

    const row = `| ${count + 1} | ${formatTitle(title)} | ${difficulty} | ${displayLanguage} | ${category} | [Link](${link}) | ${getDate()} |`;

    fs.appendFileSync(mainReadme, row + "\n");
    console.log("📊 README updated!");
  }

  // ===== GIT =====
  try {
    execSync("git add .");
    execSync("git diff --cached --quiet");
    console.log("⚠️ No changes to commit.");
  } catch {
    try {
      execSync(
        `git commit -m "Added GeeksforGeeks: ${title} | ${difficulty} | ${displayLanguage}"`,
      );
      execSync("git push");
      console.log("🚀 Successfully pushed!");
    } catch {
      console.log("❌ Push failed.");
    }
  }

  rl.close();
})();
