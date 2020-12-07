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
  
# -------------------------------------
# Installing Overtarget
# -------------------------------------

echo "Installing and running Overtarget"
./bash/maven_build.sh -j dependencies -p dependencies
  
# -------------------------------------------------------------------
# generating and setting locale for en_US needed by swtbot
# -------------------------------------------------------------------

echo "Generating locale"
sudo locale-gen en_US.UTF-8

echo "Setting up locale"
sudo update-locale LANG=en_US.UTF-8

echo "Show current locales"
locale

