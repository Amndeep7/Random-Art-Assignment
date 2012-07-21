#!/bin/bash
DIRECTORY="$HOME/RandomArtAssignmentPictures/"
if [ $# -ne 0 ]
then
	DIRECTORY=$1
fi
./convertfromnetpbm "$DIRECTORY"
./removenetpbm "$DIRECTORY"
