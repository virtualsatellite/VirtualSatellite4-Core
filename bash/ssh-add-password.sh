#!/bin/bash

#/*******************************************************************************
# * Copyright (c) 20 20 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
# *
# * This program and the accompanying materials are made available under the
# * terms of the Eclipse Public License 2.0 which is available at
# * http://www.eclipse.org/legal/epl-2.0.
# *
# * SPDX-License-Identifier: EPL-2.0
# *******************************************************************************/

# --------------------------------------------------------------------------
# This script handles setting an ssh key and using a pasword that was given by command line
# --------------------------------------------------------------------------

COMMAND=$0

printUsage() {
	echo "usage: ${COMMAND} -k <keyfile> -p <password>"
	echo ""
	echo "Options:"
	echo " -k, --keyfile <keyfile>	  The keyfile to be added to ssh agent."
	echo " -p, --password <password>  The password to be used when adding the keyfile."
	echo " -h, --help                 Show this help."
	echo ""
	echo "Copyright by DLR (German Aerospace Center)"
}


while [ "$1" != "" ]; do
    case $1 in
        -k | --keyfile )        shift
                                KEY_FILE=$1
                                ;;
        -p | --password )  			shift
                                PASSWORD=$1
                                ;;
        -h | --help )           printUsage
                                exit
                                ;;
        * )                     printUsage
                                exit 1
    esac
    shift
done

expect << EOF
  spawn ssh-add $KEY_FILE
  expect "Enter passphrase"
  send "$PASSWORD\r"
  expect eof
EOF
