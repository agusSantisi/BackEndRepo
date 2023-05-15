FROM amazoncorretto:11-alpine-jdk

EXPOSE 8080

MAINTAINER AGUSTIN

COPY target/PortfolioBack-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT  ["java","-jar","/app.jar"]
