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

# --------------------------------------------------------------------------------------------
# This script tries to setup the environment variables for native librarie sucha s vtk and zmq
# --------------------------------------------------------------------------------------------


# Store the name of the command calling from commandline to be properly
# displayed in case of usage issues
COMMAND=$0

# this method gives some little usage info
printUsage() {
	echo "usage: ${COMMAND}"
	echo ""
	echo "It is reocmmended to source the script to correctly set"
	echo "the environment variables"
	echo ""
	echo "Copyright by DLR (German Aerospace Center)"
}



# process all command line arguments
if [ "$1" != "" ]; then
	printUsage
	exit 1
fi

augmentLdLibraryPath() {
	AUGMENT_WITH=$1
	echo -n "Augmenting LD_LIBRARY_PATH with : $AUGMENT_WITH ... "
	if [[ $LD_LIBRARY_PATH == *$AUGMENT_WITH:* ]]; then
		echo "skipping"
	else
		if [ -z "$LD_LIBRARY_PATH" ]; then
			LD_LIBRARY_PATH=$AUGMENT_WITH
		else
			LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$AUGMENT_WITH
		fi
		echo "done"
	fi
}

echo "Current LD_LIBRARY_PATH: $LD_LIBRARY_PATH"

# -------------------------------------
# Start to setup java
# ------------------------------------

# Fin out where java is
JAVA_INSTALL_BIN=$(readlink -f $(which java))
JAVA_INSTALL=${JAVA_INSTALL_BIN%"/bin/java"}
JAVA_LIB_AWT=$(find $JAVA_INSTALL -name libawt.so)
JAVA_LIB_JVM=$(find $JAVA_INSTALL -name libjvm.so)

echo "Found Java Bin in the following location: $JAVA_INSTALL_BIN"
echo "Assuming Java installation here: $JAVA_INSTALL"

augmentLdLibraryPath $(dirname $JAVA_LIB_AWT)
augmentLdLibraryPath $(dirname $JAVA_LIB_JVM)

# -------------------------------------
# Start setting up vtk and zmq so dirs
# ------------------------------------

EXPECTED_JNI_SO_DIR=/usr/lib/x86_64-linux-gnu/jni/
EXPECTED_SO_DIR=/usr/lib/x86_64-linux-gnu/

#export VS_JAR_VTK=/usr/share/java/vtk7.jar
export VS_JAR_VTK=/usr/share/java/vtk6.jar
export VS_JAR_ZMQ=/usr/share/java/jzmq.jar

augmentLdLibraryPath $EXPECTED_SO_DIR
augmentLdLibraryPath $EXPECTED_JNI_SO_DIR

export LD_LIBRARY_PATH
echo "Current LD_LIBRARY_PATH: $LD_LIBRARY_PATH"
