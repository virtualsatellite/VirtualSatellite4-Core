#!/bin/bash
echo "[Info] ------------------------------------"
echo "[Info] Verify commit authors"
echo "[Info] ------------------------------------"
echo "[Info] "

if [ "$1" != "" ]; then
    echo "[WARN] No authors.txt file provided."
	exit 20
fi


echo "[Info] Create commit_authors.txt file..."

git log development... --pretty=format:"%an" | sort | uniq > commit_authors.txt

echo "[Info] Verify against known_authers.txt file"

if grep -v -F -f $1 commit_authors.txt
then
	echo "[WARN] Unknown authors in commit history."
	exit 21
else
	echo "[OK] Only known authors in commit history."
fi