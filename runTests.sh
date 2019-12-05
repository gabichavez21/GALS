#!/bin/bash
files=($(ls input))

for file in ${files[@]}; do
    java  Scheduler < input/$file
done

mkdir output/

mv *"times"* output/

paste output/SJF_times.csv output/FCFS_times.csv output/Lott_times.csv > output/out.csv

libreoffice --calc output/out.csv
