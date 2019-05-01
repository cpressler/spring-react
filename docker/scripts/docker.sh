#!/bin/bash

# Login to  Docker Registry



#docker login
# wait for prompt for user password

# Set directory. Need to get to root project  directory to execute


# Set image version based on parameter passed in
tagversion=$1
echo "Version is..."$tagversion
case "$tagversion" in
    *SNAPSHOT)
        echo "Inside case..."
        tagversion="latest";
        echo "Version is..."$tagversion
    ;;
esac

echo "Building the docker image now"
# Build Image
export BUILDVERSION=$1
#env
#echo $BUILDVERSION
docker build -f docker/spring-react-qatest-be.Dockerfile . --tag softvisionlvcp/spring-react-qatest-be:$tagversion --build-arg buildversion=`echo $BUILDVERSION`

echo "testing if login is active"
docker login

echo "Pushing the docker image now"
# Push Image to repository
docker push softvisionlvcp/spring-react-qatest-be:$tagversion


echo "Building the docker front end  image now"
# now build the UI portion of this
docker build -f docker/2stage-ui.Dockerfile .   --tag softvisionlvcp/spring-react-qatest-ui

echo "Pushing the docker front end ui image now"
# Push Image to repository
docker push softvisionlvcp/spring-react-qatest-ui:latest

# to run
# docker run -p 8000:80 softvisionlvcp/spring-react-qatest-ui