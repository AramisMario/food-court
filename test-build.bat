@echo off
cd /d "C:\Users\aramis.ortega_pragma\Documents\ESTUDIO\JAVA\proyecto-talentpool\foodCourt"
call gradlew.bat clean build -x test --no-daemon
