#!/usr/bin/env bash
cp -R ..\\SteaMyGame \\
if [ -d "\\SteaMyGame" ]; then
  cd ..
  rm -rf SteaMyGame
fi
if [ -d "$USERPROFILE\OneDrive\Bureau" ]; then
  cp \\SteaMyGame\\SteaMyGame.lnk $USERPROFILE\\OneDrive\\Bureau
elif [ -d "$USERPROFILE\Bureau" ]; then
  cp \\SteaMyGame\\SteaMyGame.lnk $USERPROFILE\\Bureau
fi