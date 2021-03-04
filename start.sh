#!/bin/bash

while sleep 1
do
    deno.exe run --allow-net --allow-read ./main.js
    if [ $? == 124 ]
    then
        git pull
    fi
done
