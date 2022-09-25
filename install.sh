#!/bin/bash
games=$1
packages=($2)
assets=$3
for game in $games
do
	rm -r -f src/games/$packages
	cd src/games
	ln -s ../../ext/tgd-$game/src/games/$packages $packages
	cd ../..
	for asset in $assets
	do
		mkdir -p res/$asset
		rm -r -f res/$asset/$packages
		if [[ -d ext/tgd-$game/res/$asset/$packages && ! -L ext/tgd-$game/res/$asset/$packages ]]
		then
			cd res/$asset
			ln -s ../../ext/tgd-$game/res/$asset/$packages $packages
			cd ../..
		fi
	done
	packages=(${packages[@]:1})
done
