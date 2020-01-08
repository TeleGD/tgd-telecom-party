#!/bin/bash
games=$1
packages=($2)
assets=$3
mkdir -p tmp
cd tmp
for game in $games
do
	wget -O tgd-$game-master.zip https://codeload.github.com/TeleGD/tgd-$game/zip/master
	jar xvf tgd-$game-master.zip
	rm -r -f ../src/games/$packages
	mv tgd-$game-master/src/games/$packages ../src/games
	for asset in $assets
	do
		mkdir -p ../res/$asset
		rm -r -f ../res/$asset/$packages
		if [[ -d tgd-$game-master/res/$asset/$packages && ! -L tgd-$game-master/res/$asset/$packages ]]
		then
			mv tgd-$game-master/res/$asset/$packages ../res/$asset
		fi
	done
	rm -r -f tgd-$game-master.zip
	rm -r -f tgd-$game-master
	packages=(${packages[@]:1})
done
