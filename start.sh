#!/bin/bash

while sleep 1
do
    ./deno run --allow-net --allow-read --allow-write ./main.js
    if [ $? == 124 ]
    then
        git pull
    fi
done
