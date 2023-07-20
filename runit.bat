@echo off
for /f "tokens=2 delims==" %%a in ('wmic OS Get localdatetime /value') do set "dt=%%a"
set "YY=%dt:~2,2%" & set "YYYY=%dt:~0,4%" & set "MM=%dt:~4,2%" & set "DD=%dt:~6,2%"
set "HH=%dt:~8,2%" & set "Min=%dt:~10,2%" & set "Sec=%dt:~12,2%"

echo Building Jar.
call mvn clean package -DskipTests

set "datestamp=%YYYY%%MM%%DD%"
echo datestamp: "%datestamp%"

echo Build docker image.
call docker build -t identity_reconciliation:"%datestamp%" .

echo retag the docker image.
call docker tag identity_reconciliation:"%datestamp%" bhargav0605/identity_reconciliation:"%datestamp%"
call docker images
call docker push bhargav0605/identity_reconciliation:"%datestamp%"
pause