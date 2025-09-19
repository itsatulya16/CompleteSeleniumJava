@echo off
echo Starting Selenium containers...
docker compose up -d

echo Waiting for Selenium to start...
timeout /t 15 >nul

echo Executing test cases...
mvn clean test

echo Stopping Selenium containers...
docker compose down

echo Done!

