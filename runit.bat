@echo off
for /f "tokens=2 delims==" %%a in ('wmic OS Get localdatetime /value') do set "dt=%%a"
set "YY=%dt:~2,2%" & set "YYYY=%dt:~0,4%" & set "MM=%dt:~4,2%" & set "DD=%dt:~6,2%"
set "HH=%dt:~8,2%" & set "Min=%dt:~10,2%" & set "Sec=%dt:~12,2%"

echo Building Jar.
call mvn clean package -DskipTests

set "datestamp=%YYYY%%MM%%DD%" & set "timestamp=%HH%%Min%%Sec%"

::echo Set docker credentials.
::set DOCKER_USERNAME=<Your-username>
::set DOCKER_TOKEN=<Your-token>

::echo datestamp: "%datestamp%"
::echo timestamp: "%timestamp%"
echo tag: %datestamp%%timestamp%

echo Build docker image.
call docker build -t identity_reconciliation:%datestamp%%timestamp% .

echo retag the docker image.
call docker tag identity_reconciliation:%datestamp%%timestamp% %DOCKER_USERNAME%/identity_reconciliation:%datestamp%%timestamp%
call docker images

echo Docker login.
call docker login --username %DOCKER_USERNAME% --password %DOCKER_TOKEN%
call docker push bhargav0605/identity_reconciliation:%datestamp%%timestamp%
pause