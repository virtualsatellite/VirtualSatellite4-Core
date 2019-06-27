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
	git fetch origin development development:development
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

# Detect Travis CI Pull Request to find out the name of the author
IS_PULL_REQUEST="false"
if [ ! -v $TRAVIS_PULL_REQUEST ]; then 
  echo "[Info] Detected TravisCI Pull Request Variable value: ${TRAVIS_PULL_REQUEST}"
  if [ "$TRAVIS_PULL_REQUEST" != "false" ]; then
  	echo "[Info] Detected TravisCI Pull Request is  ${TRAVIS_PULL_REQUEST}"
  	IS_PULL_REQUEST="true"
  	
  	PULL_REQUEST_AUTHOR=$(curl "https://api.github.com/repos/${TRAVIS_REPO_SLUG}/pulls/${TRAVIS_PULL_REQUEST}" | jq --raw-output '.user.login')
  	
  	echo "[Info] Pull Request Author: ${PULL_REQUEST_AUTHOR}"
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

UNKNOWN_AUTHORS=$(grep -v -Fq -f ./known_authors.txt ./commit_authors.txt)

echo "[Info] "
echo "[Info] ------------------------------------"
echo "[Info] generate Analysis Report"
echo "[Info] ------------------------------------"
echo "[Info] "

# Set the Review Status to APPROVE
# in case of one test failing, set it to REQUEST_CHANGES
REVIEW_STATUS="APPROVE"

REPORT=$'Author Verification Report \n'
REPORT+=$'---------------------------\n\n'

if [ -z "$UNKNOWN_AUTHORS" ]; then
	REVIEW_STATUS="REQUEST_CHANGES"
	REPORT+=$':x: Some Authors in commit History without CLA :x: \n'
else
	REPORT+=$':heavy_check_mark: All Authors in commit history with CLA. :heavy_check_mark: \n'	
fi

if [ $CHANGED_MAILMAP -ne 0 ]; then
	REVIEW_STATUS="REQUEST_CHANGES"
	REPORT+=$':warn: .mailmap file has been changed :warn: \n'
else
	REPORT+=$':heavy_check_mark: .mailmap file is not modified :heavy_check_mark: \n'	
fi

if [ $CHANGED_KNOWN_AUTHORS -ne 0 ]; then
	REVIEW_STATUS="REQUEST_CHANGES"
	REPORT+=$':warn: known_authors.txt file has been changed :warn: \n'
else
	REPORT+=$':heavy_check_mark: .known_authors file is not modified :heavy_check_mark: \n'	
fi

if [ "$IS_PULL_REQUEST" = "true" ]; then
	if [ $PULL_REQUEST_AUTHOR_KNOWN -ne 0 ] ; then
		REVIEW_STATUS="REQUEST_CHANGES"
		REPORT+=$':warn: The author of the Pull Request has no CLA :warn: \n'
	else
		REPORT+=$':heavy_check_mark: The author of the pull has a CLA :heavy_check_mark: \n'	
	fi
else
	REPORT+=$'This is not a Pull Request \n'
fi

echo "[Info] "
echo "[Info] ------------------------------------"
echo "[Info] Create Pull Request Review"
echo "[Info] ------------------------------------"
echo "[Info] "
	
	
if [ "$IS_PULL_REQUEST" == "true" ] ; then
    # Prepare the body for the review replace the \n with escaped ones
    BODY=$(echo "${REPORT}" | sed -z 's/\n/\\n/g') 
		
		# Write a review and ask for changes
		curl -H "Authorization: token ${GITHUB_API_TOKEN}" -X POST \
		-d "{\"commit_id\": \"${TRAVIS_COMMIT}\", \"body\": \"${BODY}\", \"event\": \"${REVIEW_STATUS}\"}" \
		"https://api.github.com/repos/${TRAVIS_REPO_SLUG}/pulls/${TRAVIS_PULL_REQUEST}/reviews"
fi

echo "${REPORT}"
if [ "$REVIEW_STATUS" == "APPROVE" ] ; then
  echo "[Info] ------------------------------------"
	echo "[Info] Report does not show anomalies"
  echo "[Info] ------------------------------------"
se
	echo "[Warn] ------------------------------------"
  echo "[Warn] Report shows anomalies!"
	echo "[Warn] ------------------------------------"
  exit 1
fi

    