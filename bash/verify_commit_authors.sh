#!/bin/bash
echo "[Info] ------------------------------------"
echo "[Info] Verify commit authors"
echo "[Info] ------------------------------------"
echo "[Info] "

echo "[Info] Create commit_authors.txt file..."

git log development... --pretty=format:"%an" | sort | uniq > commit_authors.txt

echo "[Info] Verify against known_authers.txt file"

if grep -v -F -f ./known_authors.txt commit_authors.txt
then
	echo "[WARN] Unknown authors in commit history."
	exit 21
else
	echo "[OK] Only known authors in commit history."
fi