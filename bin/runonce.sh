#!/bin/bash
CURRENTDIRECTORY=`pwd`
cd bin
echo `pwd`
echo Starting picture `date`
`java randomartassignment.picture.DriverParallel $@`
echo Ending picture `date`
cd "$CURRENTDIRECTORY"
