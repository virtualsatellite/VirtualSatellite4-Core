#!/bin/bash


# ---------- 
# Install
# ---------

echo "Installing tools"
sudo apt-get update
sudo apt-get install xvfb metacity libvtk6-java libzmq5 libzmq-java libzmq-jni ant expect
  
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

echo "Connecting to sourceforge"
mkdir -p -m 700 ./tmp/.sourceforge_ssh
echo "Executing openssl"
openssl aes-256-cbc -K $encrypted_abcf28ac24e1_key -iv $encrypted_abcf28ac24e1_iv -in ./id_ed25519.enc -out ./tmp/.sourceforge_ssh/id_ed25519 -d
echo "Adjusting rights"
chmod 600 ./tmp/.sourceforge_ssh/id_ed25519
echo "Adding passwords"
#./bash/ssh-add-password.sh -k ./tmp/.sourceforge_ssh/id_ed25519 -p ${ssh_password} 2>/dev/null
