FROM openjdk:11.0.2
FROM maven:3.6.0-jdk-11

VOLUME /tmp
COPY src /tmp/src
COPY pom.xml /tmp/pom.xml

RUN mvn -B -f /tmp/pom.xml install

COPY target/travelneer-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]