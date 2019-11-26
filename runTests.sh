#!/bin/bash
files=($(ls input))

for file in ${files[@]}; do
    java  Scheduler < input/$file
done

mkdir output/

mv *"times"* output/
