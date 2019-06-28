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
echo "[Info] Analyse build system integrity"
echo "[Info] ------------------------------------"

echo "[Info] Checking .travis.yml"

git diff --quiet development .travis.yml
CHANGED_TRAVIS=$?

echo "[Info] Checking ./bash/verify_commit_authors.sh"

git diff --quiet development ./bash/verify_commit_authors.sh
CHANGED_VCA=$?


echo "[Info] ------------------------------------"
echo "[Info] Analyse authors integrity"
echo "[Info] ------------------------------------"

echo "[Info] Checking .mailmap"

git diff --quiet development .mailmap
CHANGED_MAILMAP=$?

echo "[Info] Checking known_authors.txt"

git diff --quiet development known_authors.txt
CHANGED_KNOWN_AUTHORS=$?

# Detect Travis CI Pull Request to find out the name of the author
IS_PULL_REQUEST="false"
if [ ! -v $TRAVIS_PULL_REQUEST ]; then 
  echo "[Info] Detected TravisCI Pull Request Variable value: ${TRAVIS_PULL_REQUEST}"
  if [ "$TRAVIS_PULL_REQUEST" != "false" ]; then
  	echo "[Info] Detected TravisCI Pull Request is  ${TRAVIS_PULL_REQUEST}"
  	IS_PULL_REQUEST="true"
  	
  	PULL_REQUEST_API_CALL=$(curl -s --show-error "https://api.github.com/repos/${TRAVIS_REPO_SLUG}/pulls/${TRAVIS_PULL_REQUEST}")
  	
  	PULL_REQUEST_AUTHOR=$(echo "${PULL_REQUEST_API_CALL}" | jq --raw-output '.user.login')
  	PULL_REQUEST_AUTHOR_ASSOCIATION=$(echo "${PULL_REQUEST_API_CALL}" | jq --raw-output '.author_association')
  	
  	echo "[Info] Pull Request Author: ${PULL_REQUEST_AUTHOR} (Association: ${PULL_REQUEST_AUTHOR_ASSOCIATION})"
  	grep -Fq ${PULL_REQUEST_AUTHOR} ./known_authors.txt
  	PULL_REQUEST_AUTHOR_KNOWN=$?
  fi 
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
if [ "$PULL_REQUEST_AUTHOR_ASSOCIATION" != "MEMBER" ]; then
	REVIEW_STATUS_WARNINGS="APPROVED"
fi

if [ $CHANGED_TRAVIS -ne 0 ]; then
	REVIEW_STATUS="${REVIEW_STATUS_WARNINGS}"
	REPORT+=$"[Warn] WARNING: <.travis.yml> file has been changed!...(${REVIEW_STATUS_WARNINGS}) \n"
else
	REPORT+=$"[Info] OK:      <.travis.yml> file is not modified....(APPROVED) \n"	
fi

if [ $CHANGED_VCA -ne 0 ]; then
	REVIEW_STATUS="${REVIEW_STATUS_WARNINGS}"
	REPORT+=$"[Warn] WARNING: <verify_commit_authors.sh> file has been changed!...(${REVIEW_STATUS_WARNINGS}) \n"
else
	REPORT+=$"[Info] OK:      <verify_commit_authors.sh> file is not modified....(APPROVED) \n"
fi

if [ -z "$UNKNOWN_AUTHORS" ]; then
	REVIEW_STATUS="REQUEST_CHANGES"
	REPORT+=$"[Warn] SERIOUS: Some Authors in commit History without CLA!...(REQUEST_CHANGES) \n"
else
	REPORT+=$"[Info] OK:      All Authors in commit history with CLA....(APPROVED) \n"	
fi

if [ $CHANGED_MAILMAP -ne 0 ]; then
	REVIEW_STATUS="${REVIEW_STATUS_WARNINGS}"
	REPORT+=$"[Warn] WARNING: <.mailmap> file has been changed!...(${REVIEW_STATUS_WARNINGS}) \n"
else
	REPORT+=$"[Info] OK:      <.mailmap> file is not modified....(APPROVED) \n"	
fi

if [ $CHANGED_KNOWN_AUTHORS -ne 0 ]; then
	REVIEW_STATUS="${REVIEW_STATUS_WARNINGS}"
	REPORT+=$"[Warn] WARNING: <known_authors.txt> file has been changed!...(${REVIEW_STATUS_WARNINGS}) \n"
else
	REPORT+=$"[Info] OK:      <known_authors.txt> file is not modified....(APPROVED) \n"	
fi

if [ "$IS_PULL_REQUEST" = "true" ]; then
	if [ $PULL_REQUEST_AUTHOR_KNOWN -ne 0 ] ; then
		REVIEW_STATUS="REQUEST_CHANGES"
		REPORT+=$"[Warn] SERIOUS: The author of the Pull Request has no CLA!...(REQUEST_CHANGES) \n"
	else
		REPORT+=$"[Info] OK:      The author of the pull has a CLA....(APPROVED) \n"	
	fi
else
	REPORT+=$"[Info] OK:      This is not a Pull Request, thus no author to be ckecked....(APPROVED) \n"
fi

# This idea does not work with PR from a fork
# TravisCI will not decypher the secret needed
# to add the review.
# echo "[Info] "
# echo "[Info] ------------------------------------"
# echo "[Info] Create Pull Request Review"
# echo "[Info] ------------------------------------"
# echo "[Info] "
# 	
# if [ "$IS_PULL_REQUEST" == "true" ] ; then
#  #  Prepare the body for the review replace the \n with escaped ones
#  BODY=$(echo "${REPORT}" | sed -z 's/\n/\\n/g') 
#  
#  # Write a review and ask for changes
#  curl -s -o /dev/null --show-error -H "Authorization: token ${GITHUB_API_TOKEN}" -X POST \
#  -d "{\"commit_id\": \"${TRAVIS_COMMIT}\", \"body\": \"${BODY}\", \"event\": \"${REVIEW_STATUS}\"}" \
#  "https://api.github.com/repos/${TRAVIS_REPO_SLUG}/pulls/${TRAVIS_PULL_REQUEST}/reviews"
# fi

COLORED_REPORT=$(echo "${REPORT}" | sed -e "s/SERIOUS/\\${CR}SERIOUS\\${CN}/g" | sed -e "s/WARNING/\\${CY}WARNING\\${CN}/g" | sed -e "s/OK/\\${CG}OK\\${CN}/g")
COLORED_REPORT=$(echo "${COLORED_REPORT}" | sed -e "s/REQUEST_CHANGES/\\${CR}REQUEST_CHANGES\\${CN}/g" | sed -e "s/APPROVED/\\${CG}APPROVED\\${CN}/g")

echo -e "${COLORED_REPORT}"
if [ "$REVIEW_STATUS" == "APPROVE" ] ; then
  echo    "[Info] ------------------------------------"
  echo -e "[Info] ${CR}Report does not show anomalies!${CN}"
  echo    "[Info] ------------------------------------"
else
  echo    "[Warn] ------------------------------------"
  echo -e "[Warn] ${CR}Report shows anomalies!${CN}"
  echo    "[Warn] ------------------------------------"
  exit 1
fi

    