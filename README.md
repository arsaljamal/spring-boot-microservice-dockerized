# spring-boot-microservice-dockerized
This is a Spring boot micro-service application which is dockerized.

Install Docker For Mac/Windows/Linux
#### Docker Commands
##### Start MySql Container (downloads image if not found)
``
docker run  --detach   --name tour-mysql -p 6604:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=tourpk -e MYSQL_USER=pk_user -e MYSQL_PASSWORD=pk_pass -d mysql
``

##### view all images
``
docker images
``

##### view all containers (running or not)
``
docker ps -a
``
##### Interact with Database (link to ec-mysql container) with mysql client
``
docker run -it --link tour-mysql:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'
``
##### Stop tour-mysql container
``
docker stop tour-mysql
``
##### (ReStart) tour-mysql container
``
docker start tour-mysql
``
##### Remove tour-mysql container (must stop it first)
``
docker rm tour-mysql
``
##### Remove image (must stop and remove container first)
``
docker rmi mysql:latest
``

#### Setup
``
Set JAVA_HOME
Set M2_HOME
Add M2_HOME/bin to the execution path
mvn package
``

#### Startup with Profile settings
##### Default profile, H2 database
``
mvn spring-boot:run
``

or

``
java  -jar target/tour-service-1.0-SNAPSHOT.jar
``
##### Prod profile, MySql database (requires running container tour-mysql)
``
mvn spring-boot:run -Dspring.profiles.active=prod
``

or

``
java  -Dspring.profiles.active=prod -jar target/tour-service-1.0-SNAPSHOT.jar
``
#### Dockerize Tour-Service
##### Build jar
``
mvn package
``

or
``
mvn package -DskipTests -Dspring.profiles.active=prod
``
##### Build Docker image
``
docker build -t tour-service .
``
##### Run Docker container
``
docker run --name tour-app -p 8080:8080 --link tour-mysql:mysql -d tour-service
``

##### Run Docker container with prod profile set in Dockerfile and migration scripts on host
``
docker run --name tour-app -p 8080:8080 -v ~/app/src/main/resource/db/migration:/var/migration -e profile=prod -e server=tour-mysql -e port=3306 -e db_user=pk_user -e db_pass=pk_pass --link tour-mysql:mysql -d tour-service
``

##### enter Docker container
``
docker exec -t -i tour-app /bin/bash
``