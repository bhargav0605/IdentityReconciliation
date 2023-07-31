#!/bin/bash

dt=$(date '+%Y%m%d%H%M%S')

export JAVA_HOME=/home/bhargav/Software/jdk-17.0.8_linux-x64_bin/jdk-17.0.8
export PATH=$JAVA_HOME/bin:$PATH

echo $JAVA_HOME
echo $PATH

echo "Building Jar."
mvn clean package -DskipTests

echo "Set docker credentials."
DOCKER_USERNAME="<Your Username>"
DOCKER_TOKEN="<Your token>"

echo "tag: ${dt}"

echo "Build docker image."
docker build -t identity_reconciliation:${dt} .

echo "retag the docker image."
docker tag identity_reconciliation:${dt} ${DOCKER_USERNAME}/identity_reconciliation:${dt}
docker images

echo "Docker login."
docker login --username "${DOCKER_USERNAME}" --password "${DOCKER_TOKEN}"
docker push "${DOCKER_USERNAME}/identity_reconciliation:${dt}"
