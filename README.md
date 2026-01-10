this is selenium java test project
this is configured with selenium grid
selenium grid is running inside docker in my gaming PC
from this local PC ensure config/selenium.properties browser = docker (for running on grid)
for running > go to gaming pc c>selenium_grid>in terminal hit docker compose up
this will start hub and node 
after that in local machin run tests > mvn clean test
and generate report > allure serve target/allure-results
thats it -----------------