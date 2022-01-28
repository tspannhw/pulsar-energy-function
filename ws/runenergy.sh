#!/bin/bash

DATE=$(date +"%Y-%m-%d_%H%M")
echo "Started $DATE"

while :
do
   python3 -W ignore /opt/demo/energy/energy.py 2>/dev/null 

done
