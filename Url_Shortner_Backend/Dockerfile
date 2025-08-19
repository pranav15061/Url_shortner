

FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app


COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app


#COPY --from=build /app/target/UrlShortner-0.0.1-SNAPSHOT.jar .

# Copy JAR file from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port on which the Spring Boot application runs
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

