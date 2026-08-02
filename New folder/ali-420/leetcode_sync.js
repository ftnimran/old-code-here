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

// ===== AUTO DETECT REPO =====
function getRepoURL() {
  try {
    let url = execSync("git config --get remote.origin.url")
      .toString()
      .trim();

    if (!url) throw new Error();

    if (url.startsWith("git@")) {
      url = url.replace("git@github.com:", "https://github.com/");
    }

    return url.replace(".git", "");
  } catch {
    console.log("⚠️ Git repo detect nahi hua, fallback use ho raha hai...");
    return "https://github.com/ftnimran/leetcode-solutions";
  }
}

// ===== AUTO DETECT BRANCH =====
function getBranch() {
  try {
    return execSync("git branch --show-current").toString().trim() || "main";
  } catch {
    return "main";
  }
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

const displayLangMap = { cpp: "C++", js: "JavaScript" };

// ===== CATEGORY =====
function getCategory(tags, title) {
  const tagsLower = tags.map((t) => t.toLowerCase());
  const titleLower = title.toLowerCase();

  const mapping = [
    ["dynamic programming", "DP"],
    ["graph", "Graph"],
    ["tree", "Tree"],
    ["linked list", "Linked List"],
    ["sliding window", "Sliding Window"],
    ["two pointers", "Two Pointer"],
    ["binary search", "Binary Search"],
    ["heap", "Heap"],
    ["greedy", "Greedy"],
    ["stack", "Stack"],
    ["queue", "Queue"],
    ["hash table", "Hashing"],
    ["bit manipulation", "Bit Manipulation"],
    ["matrix", "Matrix"],
    ["array", "Array"],
    ["string", "String"],
    ["math", "Math"],
    ["sorting", "Sorting"],
  ];

  for (let [key, val] of mapping) {
    if (tagsLower.includes(key)) return val;
  }

  return "General";
}

// ===== SAFE NAME =====
function safeName(text) {
  return text.toLowerCase().replace(/\s+/g, "-").replace(/[^a-z0-9\-]/g, "");
}

// ===== TITLE FORMAT =====
function formatTitle(title, maxLength = 37) {
  title = title.replace(/\|/g, "");
  return title.length <= maxLength
    ? title
    : title.slice(0, maxLength - 3) + "...";
}

// ===== CLEAN HTML (FULL FORMAT) =====
function cleanHTML(html) {
  if (!html) return "No description available.";

  return html
    .replace(/<\/p>/g, "\n\n")
    .replace(/<br\s*\/?>/g, "\n")
    .replace(/<li>/g, "• ")
    .replace(/<\/li>/g, "\n")
    .replace(/<pre>/g, "\n```\n")
    .replace(/<\/pre>/g, "\n```\n")
    .replace(/<code>/g, "")
    .replace(/<\/code>/g, "")
    .replace(/<strong>/g, "**")
    .replace(/<\/strong>/g, "**")
    .replace(/<[^>]+>/g, "")
    .replace(/&quot;/g, '"')
    .replace(/&nbsp;/g, " ")
    .trim();
}

// ===== DATE =====
function getDate() {
  const d = new Date();
  return `${String(d.getDate()).padStart(2, "0")}-${String(
    d.getMonth() + 1
  ).padStart(2, "0")}-${String(d.getFullYear()).slice(-2)}`;
}

// ===== MAIN =====
(async () => {
  const urlInput = (await ask("Paste LeetCode URL: ")).trim();
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

  // ===== FETCH =====
  const res = await axios.post("https://leetcode.com/graphql", {
    query: `
      query getQuestion($titleSlug: String!) {
        question(titleSlug: $titleSlug) {
          title
          difficulty
          content
          topicTags { name }
        }
      }
    `,
    variables: { titleSlug: slug },
  });

  const q = res.data.data.question;
  if (!q) {
    console.log("❌ Problem not found!");
    process.exit();
  }

  const title = q.title;
  const difficulty = q.difficulty.toLowerCase();
  const tags = q.topicTags.map((t) => t.name);
  const link = `https://leetcode.com/problems/${slug}/`;

  const category = getCategory(tags, title);

  // ===== PATH =====
  const folder = path.join(difficulty, safeName(category), slug);
  fs.mkdirSync(folder, { recursive: true });

  const solutionPath = path.join(folder, langMap[language]);

  if (!fs.existsSync(solutionPath)) {
    fs.writeFileSync(
      solutionPath,
      `// ${title}\n// ${link}\n// Difficulty: ${difficulty}\n\n`
    );
  }

  console.log("✅ Folder ready:", folder);

  // ===== README (PROBLEM FULL) =====
  const desc = cleanHTML(q.content);

  const problemReadme = path.join(folder, "README.md");

  if (!fs.existsSync(problemReadme)) {
    fs.writeFileSync(
      problemReadme,
      `# ${title}

🔗 [Problem Link](${link})
📊 Difficulty: ${difficulty}
📂 Category: ${category}

## 📝 Description
${desc}
`
    );
  }

  // ===== VS CODE =====
  try {
    execSync(`code -r "${solutionPath}"`);
  } catch {}

  await ask("Paste your code and press ENTER...");

  // ===== MAIN README =====
  const mainReadme = "README.md";

  if (!fs.existsSync(mainReadme)) {
    fs.writeFileSync(
      mainReadme,
      `# 🚀 LeetCode Solutions

| # | Problem | Difficulty | Language | Category | Solution | Date |
|---|--------|------------|----------|----------|------|------|
`
    );
  }

  let lines = fs.readFileSync(mainReadme, "utf-8").split("\n");

  if (lines.some((line) => line.includes(`/${slug}/`))) {
    console.log("⚠️ Already exists in README.");
  } else {
    const count = lines.filter(
      (l) => l.startsWith("|") && !l.includes("Problem") && !l.includes("---")
    ).length;

    const GITHUB_REPO = getRepoURL();
    const branch = getBranch();

    const folderLink = `${GITHUB_REPO}/tree/${branch}/${folder.replace(
      /\\/g,
      "/"
    )}`;

    const row = `| ${count + 1} | ${formatTitle(title)} | ${difficulty} | ${displayLanguage} | ${category} | [Link](${folderLink}) | ${getDate()} |`;

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
        `git commit -m "Added ${title} | ${difficulty} | ${displayLanguage}"`
      );
      execSync("git push");
      console.log("🚀 Successfully pushed!");
    } catch {
      console.log("❌ Push failed.");
    }
  }

  rl.close();
})();