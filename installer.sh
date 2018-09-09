#!/usr/bin/env bash
cp -R ..\\SteaMyGame-master \\
mv \\SteaMyGame-master \\SteaMyGame
if [ -d "\\SteaMyGame" ]; then
  cd ..
  rm -rf SteaMyGame-master
fi
if [ -d "$USERPROFILE\OneDrive\Bureau" ]; then
  cp \\SteaMyGame\\SteaMyGame.lnk $USERPROFILE\\OneDrive\\Bureau
elif [ -d "$USERPROFILE\Bureau" ]; then
  cp \\SteaMyGame\\SteaMyGame.lnk $USERPROFILE\\Bureau
fi