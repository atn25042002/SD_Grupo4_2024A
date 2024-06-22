FROM mysql:8.0

ENV MYSQL_DATABASE=testdb
ENV MYSQL_ROOT_PASSWORD=rootpassword
ENV MYSQL_USER=testuser
ENV MYSQL_PASSWORD=testpassword

COPY init.sql /docker-entrypoint-initdb.d/