#!/bin/bash
DIRECTORY="$HOME/RandomArtAssignmentPictures/"
if [ $# -ne 0 ]
then
	DIRECTORY=$1
fi
find "$DIRECTORY" -name "*.p*" -exec mogrify -format jpg -quality 100 {} \;
