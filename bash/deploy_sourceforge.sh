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
# This script handles all upload activities of the project Virtual Satellite
# --------------------------------------------------------------------------

# Store the name of the command calling from commandline to be properly
# displayed in case of usage issues
COMMAND=$0

# this method gives some little usage info
printUsage() {
    echo "usage: ${COMMAND} -u [swtbot|development|integration|release]"
}

uploadSwtBot() {
	if [ "$(ls -A ${TRAVIS_BUILD_DIR}/swtbot)" ]; then
		echo "Test Artifacts: Collecting all reports in: ${TRAVIS_BUILD_DIR}/swtbot"
		cp $TRAVIS_BUILD_DIR/de.dlr.sc.virsat.swtbot.test/target/surefire-reports/* $TRAVIS_BUILD_DIR/swtbot/
		TEST_ARTEFACTS_ZIP=SwtBot_${TRAVIS_REPO_SLUG}_${TRAVIS_BRANCH}_${TRAVIS_JOB_NUMBER}_${TRAVIS_COMMIT}
		# Remove "/" "\" and "." from filename and attach .zip to it
		TEST_ARTEFACTS_ZIP=$(sed  -e 's/[\/.]/\_/g' <<< ${TEST_ARTEFACTS_ZIP}).zip
		echo "Test Artifacts: About to zip all artifacts into: ${TEST_ARTEFACTS_ZIP}"
		zip ${TRAVIS_BUILD_DIR}/swtbot/${TEST_ARTEFACTS_ZIP} swtbot/*
		echo "Test Artifacts: Starting upload to SourceForge..."
		ls -lh ${TRAVIS_BUILD_DIR}/swtbot/
		rsync -e ssh -avP ${TRAVIS_BUILD_DIR}/swtbot/${TEST_ARTEFACTS_ZIP} dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-Core/swtbot/
	else
		echo "Test Artifacts:No files in folder: ${TRAVIS_BUILD_DIR}/swtbot"
	fi
}

uploadDevelopment() {
	rsync -e ssh -avP --delete $TRAVIS_BUILD_DIR/deploy/unsecured/p2/VirSat4_Core_Application/development/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-Core/development/
	rsync -e ssh -avP --delete $TRAVIS_BUILD_DIR/deploy/unsecured/p2/VirSat4_Dvlm_ConceptIDE/development/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-DVLM/development/
	rsync -e ssh -avP --delete $TRAVIS_BUILD_DIR/deploy/unsecured/bin/VirSat4_Core_Application/development/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-Core/bin/development/
}

uploadIntegration() {
	rsync -e ssh -avP $TRAVIS_BUILD_DIR/deploy/unsecured/p2/VirSat4_Core_Application/integration/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-Core/integration/
	rsync -e ssh -avP $TRAVIS_BUILD_DIR/deploy/unsecured/p2/VirSat4_Dvlm_ConceptIDE/integration/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-DVLM/integration/
	rsync -e ssh -avP $TRAVIS_BUILD_DIR/deploy/unsecured/bin/VirSat4_Core_Application/integration/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-Core/bin/integration/
}

uploadRelease() {
	rsync -e ssh -avP $TRAVIS_BUILD_DIR/deploy/secured/p2/VirSat4_Core_Application/release/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-Core/release/
	rsync -e ssh -avP $TRAVIS_BUILD_DIR/deploy/secured/p2/VirSat4_Dvlm_ConceptIDE/release/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-DVLM/release/
	rsync -e ssh -avP $TRAVIS_BUILD_DIR/deploy/secured/bin/VirSat4_Core_Application/release/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-Core/bin/release/
}


# process all command line arguments
while [ "$1" != "" ]; do
    case $1 in
        -u | --upload )         shift
                                UPLOAD=$1
                                ;;
        -h | --help )           printUsage
                                exit
                                ;;
        * )                     printUsage
                                exit 1
    esac
    shift
done

# Setup ssh with sourceforge secrets
sourceforgeDecryptSecret

# Decide what to upload
case $UPLOAD in
    swtbot )            uploadSwtBot
                        exit
                        ;;
    development )       uploadDevelopment
                        exit
                        ;;
    integration )       uploadIntegration
                        exit
                        ;;
    release )           uploadRelease
                        exit
                        ;;
    * )                 printUsage
                        exit 1
esac
