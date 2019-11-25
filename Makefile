# Makefile for Scheduler.java
#

Schedulerd.class	: Scheduler.java
	javac Scheduler.java

run_test	: Scheduler.class tasks.txt
	java Scheduler < tasks.txt

clean	:
	rm -rf *.class
