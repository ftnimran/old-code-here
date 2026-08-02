import os
import requests
import re
import time
import sys
from datetime import datetime
from html import unescape

# ===== OPTIONAL HTML PARSER =====
try:
    from bs4 import BeautifulSoup
    USE_BS = True
except:
    USE_BS = False

# ===== LANGUAGE MAP =====
lang_map = {
    "cpp": "solution.cpp",
    "c": "solution.c",
    "java": "Solution.java",
    "python": "solution.py",
    "js": "solution.js",
    "csharp": "Solution.cs"
}

display_lang_map = {"cpp": "C++", "js": "JavaScript"}

# ===== CATEGORY LOGIC =====
def get_category(tags, title):
    tags_lower = [t.lower() for t in tags]
    title_lower = title.lower()

    priority_mapping = [
        ("dynamic programming", "DP"),
        ("segment tree", "Segment Tree"),
        ("fenwick tree", "Binary Indexed Tree"),
        ("graph", "Graph"),
        ("topological sort", "Graph"),
        ("trie", "Trie"),
        ("union find", "Union Find"),
        ("backtracking", "Backtracking"),
        ("recursion", "Backtracking"),
        ("tree", "Tree"),
        ("binary search tree", "Tree"),
        ("linked list", "Linked List"),
        ("monotonic stack", "Monotonic Stack"),
        ("sliding window", "Sliding Window"),
        ("two pointers", "Two Pointer"),
        ("prefix sum", "Prefix Sum"),
        ("binary search", "Binary Search"),
        ("heap", "Heap"),
        ("greedy", "Greedy"),
        ("stack", "Stack"),
        ("queue", "Queue"),
        ("hash table", "Hashing"),
        ("bit manipulation", "Bit Manipulation"),
        ("matrix", "Matrix"),
        ("geometry", "Geometry"),
        ("game theory", "Game Theory"),
        ("database", "SQL"),
        ("shell", "Shell"),
        ("design", "Design"),
        ("array", "Array"),
        ("string", "String"),
        ("math", "Math"),
        ("simulation", "Simulation"),
        ("sorting", "Sorting")
    ]

    for key, folder in priority_mapping:
        if key in tags_lower:
            return folder

    if "counter" in title_lower or "closure" in title_lower:
        return "Closure"
    if "promise" in title_lower:
        return "Promise"
    if "function" in title_lower:
        return "Basics"

    return "General"

# ===== SAFE NAME =====
def safe_name(text):
    return re.sub(r'[^a-z0-9\-]', '', text.lower().replace(" ", "-"))

# ===== FORMAT TITLE (NO WRAP) =====
def format_title(title, max_length=37):
    title = title.replace("|", "")  # fix markdown break
    return title if len(title) <= max_length else title[:max_length-3] + "..."

# ===== CLEAN HTML =====
def clean_html(raw_html):
    if not raw_html:
        return "No description available."
    if USE_BS:
        soup = BeautifulSoup(raw_html, "html.parser")
        return soup.get_text(separator="\n").strip()
    else:
        text = re.sub('<.*?>', '', raw_html)
        return unescape(text).strip()

# ===== FETCH =====
def fetch_with_retry(url, payload, retries=3):
    headers = {
        "User-Agent": "Mozilla/5.0",
        "Referer": "https://leetcode.com/",
        "Content-Type": "application/json"
    }
    for i in range(retries):
        try:
            response = requests.post(url, json=payload, headers=headers, timeout=10)
            response.raise_for_status()
            return response.json()
        except Exception:
            if i < retries - 1:
                print(f"⚠️ Retry {i+1}...")
                time.sleep(2)
            else:
                print("❌ Failed to fetch problem.")
                sys.exit()

# ===== INPUT =====
url_input = input("Paste LeetCode URL: ").strip()
language = input("Language (cpp/c/java/python/js/csharp): ").lower().strip()

if language not in lang_map:
    print("❌ Invalid language!")
    sys.exit()

display_language = display_lang_map.get(language, language.upper())

# ===== EXTRACT SLUG =====
match = re.search(r"problems/([^/]+)", url_input)
if not match:
    print("❌ Invalid URL!")
    sys.exit()

slug = match.group(1)

# ===== GRAPHQL =====
url = "https://leetcode.com/graphql"

query = {
    "query": """
    query getQuestion($titleSlug: String!) {
        question(titleSlug: $titleSlug) {
            title
            difficulty
            content
            topicTags { name }
        }
    }
    """,
    "variables": {"titleSlug": slug}
}

data = fetch_with_retry(url, query)

if "errors" in data:
    print("❌ API Error:", data["errors"])
    sys.exit()

q = data.get("data", {}).get("question")

if not q:
    print("❌ Problem not found!")
    sys.exit()

# ===== DATA =====
title = q["title"]
difficulty = q["difficulty"].lower()
tags = [tag["name"] for tag in q["topicTags"]]
link = f"https://leetcode.com/problems/{slug}/"

category = get_category(tags, title)

# ===== PATH =====
problem_folder = os.path.join(difficulty, safe_name(category), slug)
os.makedirs(problem_folder, exist_ok=True)

# ===== SOLUTION FILE =====
solution_path = os.path.join(problem_folder, lang_map[language])

if not os.path.exists(solution_path):
    with open(solution_path, "w", encoding="utf-8") as f:
        f.write(f"// {title}\n// {link}\n// Difficulty: {difficulty.capitalize()}\n\n")

print(f"✅ Folder ready: {problem_folder}")

# ===== README (PROBLEM) =====
description = clean_html(q["content"])
short_desc = description[:800] + "..." if len(description) > 800 else description

problem_readme = os.path.join(problem_folder, "README.md")

if not os.path.exists(problem_readme):
    with open(problem_readme, "w", encoding="utf-8") as f:
        f.write(f"# {title}\n\n🔗 [Problem Link]({link})\n📊 Difficulty: {difficulty.capitalize()}\n📂 Category: {category}\n\n## 📝 Description\n{short_desc}")

# ===== VS CODE =====
if os.system("code --version >nul 2>&1" if os.name == "nt" else "code --version > /dev/null 2>&1") == 0:
    os.system(f'code "{solution_path}"')

input("Paste your code and press ENTER to continue...")

# ===== MAIN README =====
main_readme = "README.md"

if not os.path.exists(main_readme):
    with open(main_readme, "w", encoding="utf-8") as f:
        f.write("# 🚀 LeetCode Solutions\n\n")
        f.write("| # | Problem | Difficulty | Language | Category | Link | Date |\n")
        f.write("|---|--------|------------|----------|----------|------|------|\n")

with open(main_readme, "r", encoding="utf-8") as f:
    lines = f.readlines()

# ===== DUPLICATE CHECK =====
if any(f"/{slug}/" in line for line in lines):
    print("⚠️ Already exists in README.")
else:
    count = len([l for l in lines if l.startswith("|") and "Problem" not in l and not l.startswith("|---")])
    
    # ✅ DATE FORMAT FIX
    today = datetime.now().strftime("%d-%m-%y")
    
    # ✅ TITLE FORMAT FIX
    formatted_title = format_title(title)

    new_row = f"| {count+1} | {formatted_title} | {difficulty.capitalize()} | {display_language} | {category} | [Link]({link}) | {today} |\n"

    with open(main_readme, "a", encoding="utf-8") as f:
        f.write(new_row)

    print("📊 README updated!")

# ===== GIT =====
os.system("git add .")

if os.system("git diff --cached --quiet") != 0:
    os.system(f'git commit -m "Added {title} | {difficulty.capitalize()} | {display_language}"')
    push_status = os.system("git push")

    if push_status != 0:
        print("❌ Push failed. Check git login.")
    else:
        print("🚀 Successfully pushed!")
else:
    print("⚠️ No changes to commit.")