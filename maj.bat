sleep 0.5
java -cp bin application.ChargementMaj &
cp -r save \\
cd ..
rm -rf SteaMyGame
git clone https://github.com/JulienCHATEAU/SteaMyGame.git
cd SteaMyGame
rm -rf save
cp -r \\save .
rm -rf \\save
taskkill /f /im javaw
sleep 0.5
java -cp bin application.Application &
bash endmaj.bat