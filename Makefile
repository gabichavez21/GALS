# Makefile for Scheduler.java
#

all	:	createInput.py Scheduler.java
	./createInput.py
	javac Scheduler.java

run_simple	: Scheduler.class tasks.txt
	java Scheduler < tasks.txt

run_one		: Input0.txt
	java Scheduler < Input0.txt

run_many	: Input0.txt Input1.txt Input2.txt Input3.txt Input4.txt Input5.txt Input6.txt Input7.txt Input8.txt Input9.txt
	java Scheduler < Input0.txt
	java Scheduler < Input1.txt
	java Scheduler < Input2.txt
	java Scheduler < Input3.txt
	java Scheduler < Input4.txt
	java Scheduler < Input5.txt
	java Scheduler < Input6.txt
	java Scheduler < Input7.txt
	java Scheduler < Input8.txt
	java Scheduler < Input9.txt

Scheduler.class	: Scheduler.java
	javac Scheduler.java

clean	:
	rm -rf *.class Input* *times*
