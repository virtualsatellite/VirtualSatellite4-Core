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

# set general variables to correctly execute the build process
export OVERTARGET_VERSION=1.2.1.r202007301216
export OVERTARGET_REPO=https://sourceforge.net/projects/overtarget/files/release/1.2.1/483/plugins/
export OVERTARGET_GROUP=de.dlr.sc.overtarget
export JUNIT_DEBUG_PROJECT_TEST_CASE=true
export SWTBOT_SCREENSHOT=true

# this method gives some little usage info
printUsage() {
	echo "usage: ${COMMAND} -j [dependencies|surefire|spotbugs|checkstyle|assemble] -p [dependencies|development|integration|release]"
	echo ""
	echo "Options:"
	echo " -j, --jobs <jobname>	    The name of the Travis-CI job to be build."
	echo " -p, --profile <profile>  The name of the maven profile to be build."
	echo ""
	echo "Jobname:"
	echo " dependencies  Downloads and installs maven dependencies, e.g. overtarget."
	echo " surefire      To run all surefire tests including junit and swtbot."
	echo " surecoverage  To run all surefire tests including junit and swtbot and finally upload reports to codecov."
	echo " sureheadless  To run all surefire tests including junit and swtbot and finally upload reports to codecov. Alos starts xvfb and metacity."
	echo " spotbugs      To run spotbugs static code analysis."
	echo " checkstyle    To run checkstyle for testing style guidelines."
	echo " assemble      To run full assemble including the java docs build."
	echo ""
	echo "Profile:"
	echo " dependencies     To be set when calling the update dependencies."
	echo " development      Maven profile for development and feature builds."
	echo " integration      Maven profile for integration builds."
	echo " release          Maven profile for release builds. Fails to overwrite deployments."
	echo ""
	echo "Copyright by DLR (German Aerospace Center)"
}

callMavenDependencies() {
	mkdir -p ./OverTarget
	curl -v -L -o ./OverTarget/OverTarget.jar ${OVERTARGET_REPO}/${OVERTARGET_GROUP}.language_${OVERTARGET_VERSION}.jar/download
	mvn install:install-file -Dfile=./OverTarget/OverTarget.jar -DgroupId=${OVERTARGET_GROUP} -DartifactId=${OVERTARGET_GROUP}.language -Dversion=${OVERTARGET_VERSION} -Dpackaging=jar
}

checkforMavenProblems() {
	echo "Check for Maven Problems on Product:"
	(grep -n "\[\(WARN\|WARNING\|ERROR\)\]" maven.log \
	| grep -v "\[WARNING\] Checksum validation failed" \
	| grep -v "\[WARNING\] Could not validate integrity of download" \
	| grep -v "\[WARNING\] Some attempts to read artifact" \
	| grep -v "An error occurred while transferring artifact packed:" \
	| grep -v "Retry another mirror:" \
	| grep -v "Artifact not found:" \
	| grep -v "An error occurred while transferring artifact canonical:" \
	| grep -v "Unable to read repository at" \
	|| exit 0 && exit 1;)
}

callMavenSurefire() {
	echo "Setting VS_JAR_VTK to: $VS_JAR_VTK"
	echo "Setting VS_JAR_ZMQ to: $VS_JAR_ZMQ"

	echo "Maven - Surefire - ${MAVEN_PROFILE}"
	mvn clean compile -P ${MAVEN_PROFILE},target -B -V | tee maven.log
	echo "Check for Maven Problems on Overtarget:"
	(grep -n "\[\(WARN\|ERROR\)\]" maven.log || exit 0  && exit 1;)
	mvn clean install -P ${MAVEN_PROFILE},surefire,product -B -V | tee maven.log
	# Always try too upload coverage reports as soon as possible
	checkforMavenProblems
	echo "Check for failed test cases:"
	(grep -n "<<< FAILURE!" maven.log || exit 0 && exit 1;)
	echo "Ant jacoco Reports"
	ant jacocoPrepareDependencies
	ant jacocoReport 2>&1 | tee ant.log
	(grep -n "\(Rule violated\|BUILD FAILED\)" ant.log || exit 0 && exit 1;)
}

callMavenSurefireAndCoverageHeadless() {
        sudo apt-get update
        sudo apt-get install xvfb metacity
        export DISPLAY=:99.0
        Xvfb -ac :99 -screen 0 1280x1024x16 > /dev/null 2>&1 &
        metacity --sm-disable --replace 2> metacity.err &
        callMavenSurefireAndCoverage
}

callMavenSurefireAndCoverage() {
	callMavenSurefire
	echo "CodeCov"
	bash <(curl -s https://codecov.io/bash)
}


callMavenSpotbugs() {
	echo "Maven - Spotbugs - ${MAVEN_PROFILE}"
	mvn clean compile -P $MAVEN_PROFILE,target -B -V | tee maven.log
	echo "Check for Maven Problems on Overtarget:"
	(grep -n "\[\(WARN\|ERROR\)\]" maven.log || exit 0  && exit 1;)
	mvn clean install -P $MAVEN_PROFILE,spotbugs,product -B -V | tee maven.log
	checkforMavenProblems
}

callMavenCheckstyle() {
	echo "Maven - Checkstyle - ${MAVEN_PROFILE}"
	mvn clean compile -P ${MAVEN_PROFILE},target -B -V | tee maven.log
	echo "Check for Maven Problems on Overtarget:"
	(grep -n "\[\(WARN\|ERROR\)\]" maven.log || exit 0  && exit 1;)
	mvn clean install -P ${MAVEN_PROFILE},checkstyle,product -B -V | tee maven.log
	checkforMavenProblems
}

callMavenAssemble() {
	if [ "$MAVEN_PROFILE" == "release" ] ; then
		DEPLOY_TYPE="deployBackuped"
	else
		DEPLOY_TYPE="deployUnsecured"
	fi
	echo "Maven - Assemlbe - ${MAVEN_PROFILE} - ${DEPLOY_TYPE}"
	mvn clean compile -P ${MAVEN_PROFILE},target -B -V | tee maven.log
	echo "Check for Maven Problems on Overtarget:"
	(grep -n "\[\(WARN\|ERROR\)\]" maven.log || exit 0  && exit 1;)
	mvn clean install -P ${MAVEN_PROFILE},doc,deploy,${DEPLOY_TYPE},product -B -V | tee maven.log
	checkforMavenProblems
	echo "Check for AsciiDoc Problems on Product:"
	(grep -n "\[INFO\] asciidoctor: \(WARN\|ERROR\|ERR\)" maven.log || exit 0  && exit 1;)
}

# process all command line arguments
while [ "$1" != "" ]; do
    case $1 in
        -j | --jobs )           shift
                                TRAVIS_JOB=$1
                                ;;
        -p | --profile )    	shift
                                MAVEN_PROFILE=$1
                                ;;
        -h | --help )           printUsage
                                exit
                                ;;
        * )                     printUsage
                                exit 1
    esac
    shift
done

case $MAVEN_PROFILE in
    dependencies )      ;;
    development )       ;;
    integration )       ;;
    release )           ;;
    * )                 printUsage
                        exit 1
esac

source ./bash/setup_environment.sh

# Decide which job to run
case $TRAVIS_JOB in
    dependencies )      callMavenDependencies
                        exit
                        ;;
    surefire )          callMavenSurefire
                        exit
                        ;;
    surecoverage )      callMavenSurefireAndCoverage
                        exit
                        ;;
    sureheadless )      callMavenSurefireAndCoverageHeadless
                        exit
                        ;;
    spotbugs )      	callMavenSpotbugs
                        exit
                        ;;
    checkstyle )      	callMavenCheckstyle
                        exit
                        ;;
    assemble )      	callMavenAssemble
                        exit
                        ;;
    * )                 printUsage
                        exit 1
esac
