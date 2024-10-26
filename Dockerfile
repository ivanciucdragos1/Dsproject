FROM postgres:13

ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=root

COPY userDBscript.sql /docker-entrypoint-initdb.d/
COPY devicesDBscript.sql /docker-entrypoint-initdb.d/
COPY init.sql /docker-entrypoint-initdb.d/