#!/bin/bash
set -e

echo "Start entrypoint"

cd /opt/services/spring-react-qatest/
# this will force it to run forever in background

#java -Dspring.profiles.active=container -jar  spring-react-qatest-boot.jar

java  -jar  spring-react-qatest-boot.jar

echo "DONE entrypoint"
