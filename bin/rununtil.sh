#!/bin/bash
#Time type for the date command, use the same types (M, H, etc.) just without the %
TIMETYPE=$1
TIMETILL=$2
shift
shift
CURRENTDIRECTORY=`pwd`
cd bin
CURRENTTIME=`date +%$TIMETYPE`
while [ $TIMETILL -gt "$CURRENTTIME" ]
do
	echo Starting picture `date`
	`time java randomartassignment.picture.DriverParallel $@`
	echo Ending picture `date`
	CURRENTTIME=`date +%$TIMETYPE`
done
cd "$CURRENTDIRECTORY"
