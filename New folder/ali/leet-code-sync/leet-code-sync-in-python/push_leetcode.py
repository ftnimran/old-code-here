import os
import requests
import re
from datetime import datetime
from html import unescape

# ===== LANGUAGE MAP =====
lang_map = {
    "cpp": "solution.cpp",
    "c": "solution.c",
    "java": "Solution.java",
    "python": "solution.py",
    "js": "solution.js",
    "csharp": "Solution.cs"
}

# ===== INPUT =====
url_input = input("Paste LeetCode URL: ").strip()
language = input("Language (cpp/c/java/python/js/csharp): ").lower().strip()

if language not in lang_map:
    print("❌ Invalid language!")
    exit()

# ===== EXTRACT SLUG =====
match = re.search(r"problems/([^/]+)/", url_input)
if not match:
    print("❌ Invalid URL!")
    exit()

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
            topicTags {
                name
            }
        }
    }
    """,
    "variables": {"titleSlug": slug}
}

# ===== FETCH =====
try:
    response = requests.post(url, json=query)
    data = response.json()
    q = data["data"]["question"]

    if not q:
        raise Exception()

except:
    print("❌ Failed to fetch problem.")
    exit()

# ===== DATA =====
title = q["title"]
difficulty = q["difficulty"]
html_content = q["content"]
tags = [tag["name"] for tag in q["topicTags"]]
link = f"https://leetcode.com/problems/{slug}/"

# ===== CATEGORY =====
category = tags[0] if tags else difficulty
category_folder = category.lower().replace(" ", "-")

# ===== CLEAN HTML =====
def clean_html(raw_html):
    text = re.sub('<.*?>', '', raw_html)
    return unescape(text).strip()

description = clean_html(html_content)

# ===== CREATE FOLDER =====
problem_folder = os.path.join(category_folder, slug)
os.makedirs(problem_folder, exist_ok=True)

# ===== CREATE SOLUTION FILE =====
solution_path = os.path.join(problem_folder, lang_map[language])

if not os.path.exists(solution_path):
    with open(solution_path, "w") as f:
        f.write(f"// {title}\n")
        f.write(f"// {link}\n")
        f.write(f"// Difficulty: {difficulty}\n\n")

print(f"✅ Solution file created: {solution_path}")

# ===== CREATE PROBLEM README =====
problem_readme_path = os.path.join(problem_folder, "README.md")

with open(problem_readme_path, "w", encoding="utf-8") as f:
    f.write(f"""# {title}

🔗 Problem Link: {link}  
📊 Difficulty: {difficulty}  
📂 Category: {category}  
🏷 Tags: {", ".join(tags)}

---

## 📝 Problem Description

{description[:1200]}...

---

## 💡 Approach
<!-- Write your approach here -->

---

## ⏱ Complexity
- Time: O()
- Space: O()
""")

print("📝 Problem README generated!")

# ===== OPEN IN VS CODE =====
os.system(f"code {solution_path}")
input("Paste your code and press ENTER to continue...")

# ===== MAIN README =====
main_readme = "README.md"

# create file if not exists
if not os.path.exists(main_readme):
    with open(main_readme, "w") as f:
        f.write("# 🚀 LeetCode Solutions\n\n")
        f.write("| # | Problem | Difficulty | Language | Category | Link | Date |\n")
        f.write("|---|--------|------------|----------|----------|------|------|\n")

# read file safely
with open(main_readme, "r") as f:
    content = f.read()

lines = content.splitlines()

# ===== DUPLICATE CHECK =====
if any(slug in line for line in lines):
    print("⚠️ Already exists in README. Skipping entry.")
else:
    # count only problem rows
    data_lines = [
        line for line in lines
        if line.startswith("|") and not line.startswith("|---") and "Problem" not in line
    ]

    count = len(data_lines)

    today = datetime.now().strftime("%Y-%m-%d")

    new_row = f"| {count+1} | {title} | {difficulty} | {language.upper()} | {category} | [Link]({link}) | {today} |"

    # ALWAYS add new line properly
    with open(main_readme, "a") as f:
        f.write("\n" + new_row + "\n")

    print("📊 Main README updated!")

# ===== SAFE GIT PUSH =====
os.system("git add .")
status = os.system('git diff --cached --quiet')

if status != 0:
    os.system(f'git commit -m "Added {title} ({language})"')
    os.system("git push")
    print("🚀 Successfully pushed to GitHub!")
else:
    print("⚠️ No changes to commit.")