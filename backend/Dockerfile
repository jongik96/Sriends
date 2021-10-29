FROM openjdk:8-jdk-alpine as builder
# COPY --from=build-stage ./dist ./src/main/resources/dist
COPY ./backend/gradlew .
COPY ./backend/gradle gradle
COPY ./backend/build.gradle .
COPY ./backend/settings.gradle . 
COPY ./backend/src src
RUN chmod +x ./gradlew
RUN ./gradlew clean build

FROM openjdk:8-jdk-alpine
COPY --from=builder build/libs/autonomous-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]