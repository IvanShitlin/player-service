FROM gradle:8.2.1-jdk17 as builder

WORKDIR /app/intuit
COPY --chown=gradle:gradle . .

RUN gradle build --no-daemon --refresh-dependencies

FROM openjdk:17

WORKDIR /app/intuit
COPY --from=builder /app/intuit/build/libs/player-service-0.0.1-SNAPSHOT.jar /app/intuit/player-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "player-service-0.0.1-SNAPSHOT.jar"]
