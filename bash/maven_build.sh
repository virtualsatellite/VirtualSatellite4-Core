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
	echo "usage: ${COMMAND} -j [surefire|spotbugs|checkstyle|assemble] -p [development|integration|release]"
	echo ""
	echo "Options:"
	echo " -j, --jobs <jobname>	    The name of the Travis-CI job to be build."
	echo " -p, --profile <profile>  The name of the maven profile to be build."
	echo ""
	echo "Jobname:"
	echo " surefire      To run all surefire tests including junit and swtbot."
	echo " spotbugs      To run spotbugs static code analysis."
	echo " checkstyle    To run checkstyle for testing style guidelines."
	echo " assemble      To run full assemble including the java docs build."
	echo ""
	echo "Profile:"
	echo " development      Maven profile for development and feature builds."
	echo " integration      Maven profile for integration builds."
	echo " release          Maven profile for release builds. Fails to overwrite deployments."
	echo ""
	echo "Copyright by DLR (German Aerospace Center)"
}

callMavenSurefire() {
	echo "Maven - Surefire - ${MAVEN_PROFILE}"
	mvn clean compile -P ${MAVEN_PROFILE},target -B -V | tee maven.log
	echo "Check for Maven Problems on Overtarget:"
	(grep -n "\[\(WARN\|ERROR\)\]" maven.log || exit 0  && exit 1;)
	mvn install -P ${MAVEN_PROFILE},surefire,product -B -V | tee maven.log
	echo "Check for Maven Problems on Product:"
	(grep -n "\[\(WARN\|WARNING\|ERROR\)\]" maven.log || exit 0  && exit 1;)
	echo "Check for failed test cases:"
	(grep -n "<<< FAILURE!" maven.log || exit 0 && exit 1;)
	echo "Ant jacoco Reports"
	ant jacocoPrepareDependencies
	ant jacocoReport 2>&1 | tee ant.log
	(grep -n "\(Rule violated\|BUILD FAILED\)" ant.log || exit 0 && exit 1;)
	echo "CodeCov"
	bash <(curl -s https://codecov.io/bash)
}

callMavenSpotbugs() {
	echo "Maven - Spotbugs - ${MAVEN_PROFILE}"
	mvn clean compile -P $MAVEN_PROFILE,target -B -V | tee maven.log
	echo "Check for Maven Problems on Overtarget:"
	(grep -n "\[\(WARN\|ERROR\)\]" maven.log || exit 0  && exit 1;)
	mvn install -P $MAVEN_PROFILE,spotbugs,product -B -V | tee maven.log
	echo "Check for Maven Problems on Product:"
	(grep -n "\[\(WARN\|WARNING\|ERROR\)\]" maven.log || exit 0  && exit 1;)
}

callMavenCheckstyle() {
	echo "Maven - Checkstyle - ${MAVEN_PROFILE}"
	mvn clean compile -P ${MAVEN_PROFILE},target -B -V | tee maven.log
	echo "Check for Maven Problems on Overtarget:"
	(grep -n "\[\(WARN\|ERROR\)\]" maven.log || exit 0  && exit 1;)
	mvn install -P ${MAVEN_PROFILE},checkstyle,product -B -V | tee maven.log
	echo "Check for Maven Problems on Product:"
	(grep -n "\[\(WARN\|WARNING\|ERROR\)\]" maven.log || exit 0  && exit 1;)
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
	mvn install -P ${MAVEN_PROFILE},javadoc,deploy,${DEPLOY_TYPE},product -B -V | tee maven.log
	echo "Check for Maven Problems on Product:"
	(grep -n "\[\(WARN\|WARNING\|ERROR\)\]" maven.log || exit 0  && exit 1;)
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
    development )       ;;
    integration )       ;;
    release )           ;;
    * )                 printUsage
                        exit 1
esac

# Decide which job to run
case $TRAVIS_JOB in
    surefire )          callMavenSurefire
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
