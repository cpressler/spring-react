FROM centos:7

ARG buildversion
ARG ARTIFACT_ID=spring-react-qatest

RUN echo "Oh dang look at that $buildversion"

ARG sourceJar=./target/spring-react-qatest-${buildversion}-boot.jar
#RUN echo $sourceJar

EXPOSE 8180


RUN yum update -y -q && \
yum install -y -q java-1.8.0-openjdk && \
rm -rf /var/cache/yum && \
mkdir /opt/services && \
mkdir /opt/services/spring-react-qatest


COPY ./spring-react-qatest.service /usr/lib/systemd/system/spring-react-qatest.service
COPY $sourceJar /opt/services/spring-react-qatest/spring-react-qatest-boot.jar
RUN chmod 666 /usr/lib/systemd/system/spring-react-qatest.service && \
ln -s /usr/lib/systemd/system/spring-react-qatest.service /etc/systemd/system/multi-user.target.wants/spring-react-qatest.service

ADD ./docker/config/docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod +x /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]

