@echo off
dir /s /B *.java > sources.txt
if not exist bin mkdir bin
javac -d bin @sources.txt
