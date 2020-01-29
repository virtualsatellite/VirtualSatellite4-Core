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

# This method decrypts the SSH secret to upload it to sourceforge
sourceforgeDecryptSecret() {
	eval "$(ssh-agent -s)"
	mkdir -p -m 700 /tmp/.sourceforge_ssh
	openssl aes-256-cbc -K $DECRYPT_KEY -iv $DECRYPT_IV -in id_ed25519.enc -out /tmp/.sourceforge_ssh/id_ed25519 -d
	chmod 600 /tmp/.sourceforge_ssh/id_ed25519
	ssh-add /tmp/.sourceforge_ssh/id_ed25519
}

# this method gives some little usage info
printUsage() {
    echo "usage: ${COMMAND} -k SECRET -i SECRET -u [swtbot|development|integration|release]"
}

uploadSwtBot() {
	cp $TRAVIS_BUILD_DIR/de.dlr.sc.virsat.swtbot.test/target/surefire-reports/* $TRAVIS_BUILD_DIR/swtbot/
	REPO_SLUG_CLEAN=$(sed -e 's/\//\_/g' <<< ${REPO_SLUG})
	TEST_ARTEFACTS = SwtBot_${REPO_SLUG_CLEAN}_${TRAVIS_JOB_NAME}_${TRAVIS_JOB_NUMBER}_${TRAVIS_JOB_NUMBER}.zip
	zip ${TEST_ARTEFACTS} $TRAVIS_BUILD_DIR/swtbot/*
	rsync -e ssh -avP $TRAVIS_BUILD_DIR/swtbot/${TEST_ARTEFACTS} dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-Core/swtbot/
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
        -k | --key )            shift
                                DECRYPT_KEY=$1
                                ;;
        -i | --iv )    			shift
                                DECRYPT_IV=$1
                                ;;
        -u | --upload ) 		shift
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
    swtbot )           	uploadSwtBot
    					exit
                        ;;
    development )      	uploadDevelopment
    					exit
                        ;;
    integration )      	uploadIntegration
    					exit
                        ;;
    release )      		uploadRelease
    					exit
                        ;;
    * )                 printUsage
                        exit 1
esac
