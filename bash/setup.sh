#!/bin/bash


# ---------- 
# Install
# ---------
    
sudo apt-get install libvtk6-java libzmq5 libzmq-java libzmq-jni metacity ant expect

# -----------------------------------
# Startup the metacity window manager
# -----------------------------------

metacity --sm-disable --replace 2> metacity.err &
  
# -------------------------------------------------------------------
# create adirectory for Overtarget and try to receive language plugin
# -------------------------------------------------------------------

./bash/maven_build.sh -j dependencies -p dependencies

# --------------------------------------------------------------
# Setup environment variables for correct linking of vtk and zmq
# --------------------------------------------------------------

source ./bash/setup_environment.sh

# --------------------------------------
# Start the ssh agent
# --------------------------------------

eval "$(ssh-agent -s)"

# ----------------------------------------
# Decrypt the key for accessign the 
# deployment store and add it to ssh-agent
# ----------------------------------------

mkdir -p -m 700 /tmp/.sourceforge_ssh
openssl aes-256-cbc -K $encrypted_abcf28ac24e1_key -iv $encrypted_abcf28ac24e1_iv -in id_ed25519.enc -out /tmp/.sourceforge_ssh/id_ed25519 -d
chmod 600 /tmp/.sourceforge_ssh/id_ed25519
./bash/ssh-add-password.sh -k /tmp/.sourceforge_ssh/id_ed25519 -p ${ssh_password} 2>/dev/null