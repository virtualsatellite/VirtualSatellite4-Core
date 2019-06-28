#!/bin/bash

echo "[Info] ------------------------------------"
echo "[Info] Upload SWT BoT Screenshots"
echo "[Info] ------------------------------------"
echo "[Info] "

if [ "$TRAVIS_SECURE_ENV_VARS" == "true" ]; then
	echo "[Info] Secrets found preparing upload."
	mkdir -p -m 700 /tmp/.sourceforge_ssh
	openssl aes-256-cbc -K $1 -iv $2 -in id_ed25519.enc -out /tmp/.sourceforge_ssh/id_ed25519 -d
	chmod 600 /tmp/.sourceforge_ssh/id_ed25519
	ssh-add /tmp/.sourceforge_ssh/id_ed25519
	ls -l $TRAVIS_BUILD_DIR/swtbot/
	echo "[Info] Sending screenshots to sourceforge"
	rsync -e ssh -avP $TRAVIS_BUILD_DIR/swtbot/  dlrscmns@frs.sourceforge.net:/home/frs/project/virtualsatellite/VirtualSatellite4-Core/swtbot/
else
	echo "[Info] No Secrets, won't upload."
fi

echo "[Info] ------------------------------------"
echo "[Info] Done"
echo "[Info] ------------------------------------"
    