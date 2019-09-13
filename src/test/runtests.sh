#!/usr/bin/env bash
cd ../../
mvn clean verify -P integration-tests


mvn clean verify -P postman-docker-integration-tests

mvn clean verify -P postman-spring-integration-tests

mvn clean verify -P scala-integration-tests

mvn clean verify -P soapui-integration-tests

