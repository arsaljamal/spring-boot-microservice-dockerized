From openjdk:11
WORKDIR /
ADD target/tour-service-1.0-SNAPSHOT.jar //
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/tour-service-1.0-SNAPSHOT.jar"]