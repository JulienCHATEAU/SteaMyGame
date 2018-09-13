cp img\\maj1.jpeg ..\\SteaMyGame_temp
cp img\\maj2.jpeg ..\\SteaMyGame_temp
cp img\\maj3.jpeg ..\\SteaMyGame_temp
java -cp bin application.ChargementMaj &
cp -r save \\SteaMyGame_temp
cd ..
rm -rf SteaMyGame
git clone https://github.com/JulienCHATEAU/SteaMyGame.git
cd SteaMyGame
rm -rf save
cp -r \\SteaMyGame_temp\\save .
rm -rf \\SteaMyGame_temp\\save
taskkill //f //im "java*"
sleep 0.5
java -cp bin application.Application &