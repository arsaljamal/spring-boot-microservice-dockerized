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
##### Stop ec-mysql container
``
docker stop tour-mysql
``
##### (ReStart) ec-mysql container
``
docker start tour-mysql
``
##### Remove ec-mysql container (must stop it first)
``
docker rm tour-mysql
``
##### Remove image (must stop and remove container first)
``
docker rmi mysql:latest
``
