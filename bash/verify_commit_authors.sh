#!/bin/bash

set -e

echo "[Info] ------------------------------------"
echo "[Info] Verify commit authors"
echo "[Info] ------------------------------------"
echo "[Info] "

echo "[Info] Create commit_authors.txt file..."

git log origin/development... --pretty=format:"%aN" | sort | uniq > ./commit_authors.txt

echo "[Info] ------------------------------------"
echo "[Info] List of Commits and authors"
echo "[Info] ------------------------------------"
echo "[Info] "

git --no-pager log origin/development... --pretty=format:"%h - %aN"

echo "[Info] ------------------------------------"
echo "[Info] List of Commit Authors"
echo "[Info] ------------------------------------"
echo "[Info] "

cat ./commit_authors.txt

echo "[Info] ------------------------------------"
echo "[Info] List of Known Authors"
echo "[Info] ------------------------------------"
echo "[Info] "

cat ./known_authors.txt

echo "[Info] ------------------------------------"
echo "[Info] Verify against known_authors file"
echo "[Info] ------------------------------------"
echo "[Info] "

if grep -v -F -f ./known_authors.txt ./commit_authors.txt
then
	echo "[WARN] Unknown authors in commit history."
	exit 21
else
	echo "[OK] Only known authors in commit history."
fi