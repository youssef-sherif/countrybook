FROM maven:3.6.0-jdk-11

VOLUME /tmp

COPY pom.xml /tmp/pom.xml

COPY src /tmp/src

RUN mvn -B -f /tmp/pom.xml -DskipTests install

FROM openjdk:11.0.2

WORKDIR /src

COPY --from=maven target/travelneer-0.0.1-SNAPSHOT.jar app.jar

CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]