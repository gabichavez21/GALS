# Makefile for Scheduler.java
#

all	:	createInput.py Scheduler.java
	mkdir input/
	./createInput.py
	javac Scheduler.java

run_simple	: Scheduler.class tasks.txt
	java Scheduler < tasks.txt

run_one		: input/ input/Input0.txt
	java Scheduler < input/Input0.txt

run_many	: input/
	./runTests.sh

Scheduler.class	: Scheduler.java
	javac Scheduler.java

clean	:
	rm -rf *.class input/Input* input/ output/*times* output/
