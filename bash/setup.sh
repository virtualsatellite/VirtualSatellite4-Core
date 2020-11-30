#!/bin/bash


# ---------- 
# Install
# ---------

echo "Installing tools"
sudo apt-get update
sudo apt-get install openjdk-8-jdk xvfb metacity libvtk6-java libzmq5 libzmq-java libzmq-jni ant expect jq

# -------------------------------------
# openSSL installation (temp. disabled)
# -------------------------------------

# wget https://www.openssl.org/source/old/1.1.1/openssl-1.1.1g.tar.gz
# tar xzvf openssl-1.1.1g.tar.gz
# cd openssl-1.1.1g
# ./config
# make
# sudo make install
# cd ..
  
# -------------------------------------------------------------------
# create adirectory for Overtarget and try to receive language plugin
# -------------------------------------------------------------------

echo "Installing and running OverTarget"
./bash/maven_build.sh -j dependencies -p dependencies
