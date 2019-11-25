# Makefile for Scheduler.java
#

all	:	createInput.py Scheduler.java
	./createInput.py
	javac Scheduler.java

run_simple	: Scheduler.class tasks.txt
	java Scheduler < tasks.txt

run_many	: Input0.txt Input1.txt Input2.txt
	java Scheduler < Input0.txt
	java Scheduler < Input1.txt
	java Scheduler < Input2.txt

Scheduler.class	: Scheduler.java
	javac Scheduler.java

clean	:
	rm -rf *.class Input*
