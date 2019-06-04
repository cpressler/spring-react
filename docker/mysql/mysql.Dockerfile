FROM mysql:5.6
RUN echo "[mysqld]" >> /etc/mysql/my.cnf
RUN echo "max_connections = 500" >> /etc/mysql/my.cnf
COPY ./docker/mysql/docker-entrypoint-initdb.d /docker-entrypoint-initdb.d

EXPOSE 3306

CMD ["mysqld"]