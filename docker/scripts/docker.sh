#!/bin/bash

# Login to  Docker Registry



#docker login
# wait for prompt for user password

# Set directory. Need to get to root project  directory to execute


# Set image version based on parameter passed in
tagversion=$1
push=$2
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
docker build -f docker/server/spring-react-qatest-be.Dockerfile . --tag softvisionlvcp/spring-react-qatest-be:$tagversion --build-arg buildversion=`echo $BUILDVERSION`

echo "testing if login is active"
docker login

if [ "$2" == "p" ]; then
  echo "Pushing the docker image now"
  # Push Image to repository
  docker push softvisionlvcp/spring-react-qatest-be:$tagversion
else
  echo "Docker push not enabled"
fi



echo "Building the docker front end  image now"
# now build the UI portion of this
docker build -f docker/frontend/2stage-ui.Dockerfile .   --tag softvisionlvcp/spring-react-qatest-ui


if [ "$2" == "p" ]; then
  echo "Pushing the docker front end ui image now"
  # Push Image to repository
  docker push softvisionlvcp/spring-react-qatest-ui:latest
else
  echo "Docker push not enabled"
fi

# to run interactive
# docker run -p 8000:80 softvisionlvcp/spring-react-qatest-ui

#to run detached
# docker run -d -p 8000:80 softvisionlvcp/spring-react-qatest-ui --name docker_frontend

# to exec into a detached container
#docker exec -it docker_frontend /bin/sh