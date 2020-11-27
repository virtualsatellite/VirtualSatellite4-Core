#!/bin/bash


# ---------- 
# Install
# ---------

echo "Installing tools"
sudo apt-get update
sudo apt-get install openjdk-8-jdk xvfb metacity libvtk6-java libzmq5 libzmq-java libzmq-jni ant expect jq

wget https://www.openssl.org/source/old/1.1.1/openssl-1.1.1g.tar.gz
tar xzvf openssl-1.1.1g.tar.gz
cd openssl-1.1.1g
./config
make
sudo make install
cd ..
  
# -------------------------------------------------------------------
# create adirectory for Overtarget and try to receive language plugin
# -------------------------------------------------------------------

echo "Installing and running OverTarget"
./bash/maven_build.sh -j dependencies -p dependencies

# --------------------------------------
# Start the ssh agent
# --------------------------------------

echo "Starting ssh-agent"
eval "$(ssh-agent -s)"

# ----------------------------------------
# Decrypt the key for accessign the 
# deployment store and add it to ssh-agent
# ----------------------------------------

echo "Adding sourceforge as known SSH host"
SSH_DIR="$HOME/.ssh"
mkdir -p "${SSH_DIR}"
touch "${SSH_DIR}/known_hosts"
chmod 600 "${SSH_DIR}/known_hosts"
ssh-keyscan "frs.sourceforge.net" >> "${SSH_DIR}/known_hosts"
