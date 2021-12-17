#Build Stage
FROM maven:3.8.2-openjdk-11-slim AS MAVEN_BUILD
COPY pom.xml /app/
COPY src /app/src
WORKDIR /app/
RUN mvn package

# Run Stage
From openjdk:11
WORKDIR /app
COPY --from=MAVEN_BUILD /app/target/tour-service-1.0-SNAPSHOT.jar /app/
COPY --from=MAVEN_BUILD /app/src/main/resources/db/migration /var/migration
EXPOSE 8080 5005
ENV _JAVA_OPTIONS '-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005'
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","tour-service-1.0-SNAPSHOT.jar"]
