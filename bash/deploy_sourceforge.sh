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
    echo "usage: ${COMMAND} -u [development|integration|release]"
}

prepareSourceforgeSecrets() {
	# Start the ssh agent
	echo "Starting ssh-agent"
	eval "$(ssh-agent -s)"

	# Decrypt the key for accessign the 
	# deployment store and add it to ssh-agent
	echo "Adding sourceforge as known SSH host"
	SSH_DIR="$HOME/.ssh"
	mkdir -p "${SSH_DIR}"
	touch "${SSH_DIR}/known_hosts"
	chmod 600 "${SSH_DIR}/known_hosts"
	ssh-keyscan "frs.sourceforge.net" >> "${SSH_DIR}/known_hosts"

	# Prepare sourceforge secrets
	echo "Connecting to sourceforge"
	mkdir -p -m 700 tmp/.sourceforge_ssh
	echo "OpenSSL is version"
	openssl version
	echo "Executing openssl"
	openssl aes-256-cbc -d -a -pbkdf2 -in id_ed25519.enc -out tmp/.sourceforge_ssh/id_ed25519_dec -pass pass:${openssl_pass}
	echo "Adjusting rights"
	chmod 600 tmp/.sourceforge_ssh/id_ed25519_dec
	echo "Adding passwords"
	bash/ssh-add-password.sh -k tmp/.sourceforge_ssh/id_ed25519_dec -p ${ssh_key_pass} 2>/dev/null
	
	# Setup ssh with sourceforge secrets
	sourceforgeDecryptSecret
}

uploadDevelopment() {
	prepareSourceforgeSecrets();
	rsync -e ssh -avP --delete deploy/unsecured/p2/VirSat4_Core_Application/development/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-Core/development/
	rsync -e ssh -avP --delete deploy/unsecured/p2/VirSat4_Dvlm_ConceptIDE/development/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-DVLM/development/
	rsync -e ssh -avP --delete deploy/unsecured/bin/VirSat4_Core_Application/development/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-Core/bin/development/
}

uploadIntegration() {
	prepareSourceforgeSecrets();
	rsync -e ssh -avP deploy/unsecured/p2/VirSat4_Core_Application/integration/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-Core/integration/
	rsync -e ssh -avP deploy/unsecured/p2/VirSat4_Dvlm_ConceptIDE/integration/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-DVLM/integration/
	rsync -e ssh -avP deploy/unsecured/bin/VirSat4_Core_Application/integration/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-Core/bin/integration/
}

uploadRelease() {
	prepareSourceforgeSecrets();
	rsync -e ssh -avP deploy/secured/p2/VirSat4_Core_Application/release/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-Core/release/
	rsync -e ssh -avP deploy/secured/p2/VirSat4_Dvlm_ConceptIDE/release/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-DVLM/release/
	rsync -e ssh -avP deploy/secured/bin/VirSat4_Core_Application/release/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-Core/bin/release/
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

# Decide what to upload
case $UPLOAD in
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
