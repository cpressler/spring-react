
# THIS IS NOT USED IN THE PROJECT

###  mysql docker image for schema

The intent of this docker image is to create a mysql db with existing data that can be used for integration test.
The docker image will contain a mysql db and data from the schema

SQL files put into the docker-entrypoint-initdb.d directory 
are copied to the docker image and executed on running the container on startup.
