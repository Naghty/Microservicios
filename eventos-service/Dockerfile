FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build
COPY pom.xml .
COPY config-server/pom.xml config-server/pom.xml
COPY eureka-server/pom.xml eureka-server/pom.xml
COPY cuentas-service/pom.xml cuentas-service/pom.xml
COPY eventos-service/pom.xml eventos-service/pom.xml
COPY eventos-service/src eventos-service/src
RUN mvn -q -pl eventos-service package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/eventos-service/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
