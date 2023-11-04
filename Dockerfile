FROM openjdk:17.0.2 as build
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
COPY pom.xml pom.xml

RUN ./mvnw install -DskipTests -Dmaven.gitcommitid.skip=true

FROM openjdk:17.0.2

ARG JAR_FILE=/app/target/*.jar
COPY --from=build ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]