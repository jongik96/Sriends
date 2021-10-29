FROM openjdk:11-jdk as builder
# COPY --from=build-stage ./dist ./src/main/resources/dist
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle . 
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew clean build

FROM openjdk:11-jdk
COPY --from=builder build/libs/autonomous-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]