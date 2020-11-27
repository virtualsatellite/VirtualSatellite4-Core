#!/bin/bash

#/*******************************************************************************
# * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
# *
# * This program and the accompanying materials are made available under the
# * terms of the Eclipse Public License 2.0 which is available at
# * http://www.eclipse.org/legal/epl-2.0.
# *
# * SPDX-License-Identifier: EPL-2.0
# *******************************************************************************/

# --------------------------------------------------------------------------
# This script handles the different calls to maven
# --------------------------------------------------------------------------

# fail the script if a call fails
set -e

# Store the name of the command calling from commandline to be properly
# displayed in case of usage issues
COMMAND=$0


# this method gives some little usage info
printUsage() {
	echo "usage: ${COMMAND} -t token -r releaseName -d [description] -c (create|delete)"
	echo ""
	echo "Options:"
	echo " -t, --token      <token>	      GitHub API Token to get access."
	echo " -r, --release    <releaseName> The name of the release and the tag."
	echo " -d, --decription <profile>     Optional description."
	echo " -c, --command    <command>     At the moment either delete, create or patch."
	echo ""
	echo ""
	echo "Copyright by DLR (German Aerospace Center)"
}

GITHUB_TOKEN=""
GITHUB_COMMIT=$(git rev-parse HEAD)
GITHUB_REPO_FULL_NAME=$(git config --get remote.origin.url | sed 's/.*:\/\/github.com\///;s/.git$//')
RELEASE_DESCRIPTION="No Description"

printGitHubAccessInfos() {
  echo ""
  echo "GitHub Release Command Summary
  echo "------------------------------"
  echo "GitHub API Token:           $GITHUB_TOKEN"
  echo "GitHub Release ID:          $RELEASE_ID"
  echo "GitHub Release Name:        $RELEASE_NAME"
  echo "GitHub Release Description: $RELEASE_DESCRIPTION
  echo "GitHub Repo Name:           $GITHUB_REPO_FULL_NAME"
  echo "GitHub Commit:              $GITHUB_COMMIT"
  echo "GitHub Release Command:     $RELEASE_COMMAND"
  echo ""
}

generatePostDataCreateRelease()
{
  cat <<EOF
{
  "tag_name": "$RELEASE_NAME",
  "target_commitish": "$GITHUB_COMMIT",
  "name": "$RELEASE_NAME",
  "body": "$RELEASE_DESCRIPTION",
  "draft": false,
  "prerelease": true
}
EOF
}

callGitHubGetReleaseId() {
  RELEASE_ID=$(curl "https://api.github.com/repos/$GITHUB_REPO_FULL_NAME/releases/tags/$RELEASE_NAME?access_token=$GITHUB_TOKEN" | jq ".id")
  echo "Received Release ID: $RELEASE_ID"
}

callGitHubCreateRelease() {
  printGitHubAccessInfos
  
  curl --data "$(generatePostDataCreateRelease)" "https://api.github.com/repos/$GITHUB_REPO_FULL_NAME/releases?access_token=$GITHUB_TOKEN"
}

callGitHubPatchRelease() {
  callGitHubGetReleaseId
  printGitHubAccessInfos

  curl -X PATCH --data "$(generatePostDataCreateRelease)" "https://api.github.com/repos/$GITHUB_REPO_FULL_NAME/releases/$RELEASE_ID?access_token=$GITHUB_TOKEN"
}

callGitHubDeleteRelease() {
  callGitHubGetReleaseId
  printGitHubAccessInfos
  
  curl -X DELETE "https://api.github.com/repos/$GITHUB_REPO_FULL_NAME/releases/$RELEASE_ID?access_token=$GITHUB_TOKEN"
}

callGitHubUploadAsset() {
  callGitHubGetReleaseId
  printGitHubAccessInfos
  
  curl \
    -H "Authorization: token $GITHUB_TOKEN" \
    -H "Content-Type: $(file -b --mime-type $RELEASE_ASSET_FILE)" \
    --data-binary @$RELEASE_ASSET_FILE \
    "https://uploads.github.com/repos/$GITHUB_REPO_FULL_NAME/releases/$RELEASE_ID/assets?name=$(basename $RELEASE_ASSET_FILE)"
}

callGitHubUploadMultipleAssets() {
  RELEASE_ASSET_FILES=$(find $(pwd -P)/$RELEASE_ASSET_ROOT -name "$RELEASE_ASSET_PATTERN")

  echo "About to uplaod files $RELEASE_ASSET_FILES"
  for FILE in $RELEASE_ASSET_FILES; do
    RELEASE_ASSET_FILE=$FILE
    echo "uplaoding file $RELEASE_ASSET_FILE"
    callGitHubUploadAsset
  done
}

# process all command line arguments
while [ "$1" != "" ]; do
    case $1 in
        -t | --token )          shift
                                GITHUB_TOKEN=$1
                                ;;
        -r | --release )    	shift
                                RELEASE_NAME=$1
                                ;;
        -d | --description )    shift
                                RELEASE_DESCRIPTION=$1
                                ;;
        -c | --command )        shift
                                RELEASE_COMMAND=$1
                                ;;
        -f | --file)            shift
                                RELEASE_ASSET_FILE=$1
                                ;;
        -p | --pattern)         shift
                                RELEASE_ASSET_PATTERN=$1
                                ;;
        -dir | --rootDir)         shift
                                RELEASE_ASSET_ROOT=$1
                                ;;
        -h | --help )           printUsage
                                exit
                                ;;
        * )                     printUsage
                                exit 1
    esac
    shift
done

if [[ -z "$RELEASE_NAME"  ||  -z "$GITHUB_TOKEN" ]]
then
    echo "Either release name or github token is not provided"
    printUsage
    exit 1
fi 

case $RELEASE_COMMAND in
    create )            callGitHubCreateRelease;;
    delete )            callGitHubDeleteRelease;;
    patch )             callGitHubPatchRelease;;
    upload )            callGitHubUploadAsset;;
    multi )             callGitHubUploadMultipleAssets;; 
    * )                 printUsage
                        exit 1
esac
