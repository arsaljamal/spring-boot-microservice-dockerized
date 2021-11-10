#Build Stage
FROM maven:3.8.2-openjdk-11-slim
COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package

# Run Stage
From openjdk:11
WORKDIR /
ADD target/tour-service-1.0-SNAPSHOT.jar //
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/tour-service-1.0-SNAPSHOT.jar"]