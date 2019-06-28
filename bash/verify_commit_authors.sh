#!/bin/bash

echo "[Info] ------------------------------------"
echo "[Info] Verify commit authors"
echo "[Info] ------------------------------------"
echo "[Info] "

echo "[Info] ------------------------------------"
echo "[Info] Get the full history"
echo "[Info] and prepare local git repo"
echo "[Info] ------------------------------------"
echo "[Info] "

# Test if it is a shallow repository as usually checked out
# on travis ci build nodes

if [ -f $(git rev-parse --git-dir)/shallow ]; then
	echo "[Info] Current repository is shallow"
	echo "[Info] Need to unshallow"
	git fetch origin development:development
	git fetch --unshallow
else
  echo "[Info] Current repository is not shallow"
fi

echo "[Info] ------------------------------------"
echo "[Info] Show current branches"
echo "[Info] ------------------------------------"

git branch -a

echo "[Info] ------------------------------------"
echo "[Info] Analyse authors integrity"
echo "[Info] ------------------------------------"

echo "[Info] Checking .mailmap"

git diff --quiet development .mailmap
CHANGED_MAILMAP=$?

echo "[Info] Checking known_authors.txt"

git diff --quiet development known_authors.txt
CHANGED_KNOWN_AUTHORS=$?

echo "[Info] ------------------------------------"
echo "[Info] Fork detection"
echo "[Info] ------------------------------------"

# Now checking if we are on normal PR or on a forked PR
# we usually assume we are on a fork
IS_FORK="true"
if [ ! -v $TRAVIS_PULL_REQUEST ]; then 
	echo "[Info] Running on Travis CI"
	echo "[Info] Repo Slug: ${TRAVIS_REPO_SLUG}"
	echo "[Info] PR   Slug: ${TRAVIS_PULL_REQUEST_SLUG}"

	if [ "$TRAVIS_REPO_SLUG" ==  "$TRAVIS_PULL_REQUEST_SLUG" ]; then
		echo "[Info] PR is not issued from a fork!"
		IS_FORK="false"
	fi
else
	echo "[Info] Not running on Travis CI"
fi

echo "[Info] ------------------------------------"
echo "[Info] Create commit authors file"
echo "[Info] ------------------------------------"
echo "[Info] "

git fetch origin development development:development
git log development... --pretty=format:"%aN" | sort | uniq > ./commit_authors.txt

echo "[Info] ------------------------------------"
echo "[Info] List of Commits and Authors"
echo "[Info] ------------------------------------"
echo "[Info] "

git --no-pager log development... --pretty=format:"%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%aN>%Creset" --abbrev-commit --reverse

echo ""
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

echo ""
echo "[Info] ------------------------------------"
echo "[Info] Verify against known_authors file"
echo "[Info] ------------------------------------"
echo "[Info] "

UNKNOWN_AUTHORS=$(grep -v -F -f ./known_authors.txt ./commit_authors.txt)

CR='\033[0;31m' # Red Color
CY='\033[1;33m' # Yellow Color
CG='\033[1;32m' # Green Color
CN='\033[0m'    # Reset Color

echo -e "${CR}${UNKNOWN_AUTHORS}${CN}"

echo "[Info] "
echo "[Info] ------------------------------------"
echo "[Info] generate Analysis Report"
echo "[Info] ------------------------------------"
echo "[Info] "

# Set the Review Status to APPROVE
# in case of one test failing, set it to REQUEST_CHANGES
REVIEW_STATUS="APPROVE"

REPORT=$'[Info] Author Verification Report \n'
REPORT+=$'[Info] ---------------------------\n'

REVIEW_STATUS_WARNINGS="REQUEST_CHANGES"
if [ $IS_FORK == "true" ]; then
	REVIEW_STATUS_WARNINGS="APPROVE"
	REPORT+=$"[Info] Running on a PR fork, setting STRICT fail rules \n"
else
	REPORT+=$"[Info] Running on a normal PR, setting RELAXED fail rules \n"
fi

if [ -z "$UNKNOWN_AUTHORS" ]; then
	REVIEW_STATUS="REQUEST_CHANGES"
	REPORT+=$"[Warn] SERIOUS: Some Authors in commit History without CLA!...(REQUEST_CHANGES) \n"
else
	REPORT+=$"[Info] OK:      All Authors in commit history with CLA....(APPROVE) \n"	
fi

if [ $CHANGED_MAILMAP -ne 0 ]; then
	REVIEW_STATUS="${REVIEW_STATUS_WARNINGS}"
	REPORT+=$"[Warn] WARNING: <.mailmap> file has been changed!...(${REVIEW_STATUS_WARNINGS}) \n"
else
	REPORT+=$"[Info] OK:      <.mailmap> file is not modified....(APPROVE) \n"	
fi

if [ $CHANGED_KNOWN_AUTHORS -ne 0 ]; then
	REVIEW_STATUS="${REVIEW_STATUS_WARNINGS}"
	REPORT+=$"[Warn] WARNING: <known_authors.txt> file has been changed!...(${REVIEW_STATUS_WARNINGS}) \n"
else
	REPORT+=$"[Info] OK:      <known_authors.txt> file is not modified....(APPROVE) \n"	
fi

COLORED_REPORT=$(echo "${REPORT}" | sed -e "s/SERIOUS/\\${CR}SERIOUS\\${CN}/g" | sed -e "s/WARNING/\\${CY}WARNING\\${CN}/g" | sed -e "s/OK/\\${CG}OK\\${CN}/g")
COLORED_REPORT=$(echo "${COLORED_REPORT}" | sed -e "s/REQUEST_CHANGES/\\${CR}REQUEST_CHANGES\\${CN}/g" | sed -e "s/APPROVE/\\${CG}APPROVE\\${CN}/g")

echo -e "${COLORED_REPORT}"
if [ "$REVIEW_STATUS" == "APPROVE" ] ; then
  echo    "[Info] ------------------------------------"
  echo -e "[Info] ${CG}Report does not show anomalies!${CN}"
  echo    "[Info] ------------------------------------"
else
  echo    "[Warn] ------------------------------------"
  echo -e "[Warn] ${CR}Report shows anomalies!${CN}"
  echo    "[Warn] ------------------------------------"
  exit 1
fi

    