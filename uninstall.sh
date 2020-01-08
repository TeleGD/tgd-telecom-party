#!/bin/bash
games=$1
packages=($2)
assets=$3
for game in $games
do
	rm -r -f src/games/$packages
	for asset in $assets
	do
		rm -r -f res/$asset/$packages
	done
	packages=(${packages[@]:1})
done
