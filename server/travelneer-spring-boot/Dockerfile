FROM openjdk:11.0.2-jre
VOLUME /tmp
COPY target/travelneer-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar", "app.jar"]