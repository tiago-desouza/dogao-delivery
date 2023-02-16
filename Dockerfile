FROM openjdk:8-jdk-alpine

COPY target/dogao-delivery-0.0.1-SNAPSHOT.jar /opt/dogao-delivery-0.0.1-SNAPSHOT.jar

RUN apk update && apk add mysql mysql-client && \
    mkdir /var/run/mysqld && \
    chown mysql:mysql /var/run/mysqld && \
    /etc/init.d/mariadb setup && \
    /usr/bin/mysqld_safe --skip-grant-tables & \
    sleep 5 && \
    mysql -e "CREATE DATABASE nome-do-seu-banco;"

EXPOSE 3306

ENV MYSQL_ROOT_PASSWORD=123456
ENV MYSQL_DATABASE=dogao_db
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=123456

CMD ["java", "-jar", "/opt/dogao-delivery-0.0.1-SNAPSHOT.jar"]