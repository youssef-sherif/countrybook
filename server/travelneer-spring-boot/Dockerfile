FROM openjdk:11.0.2

ENTRYPOINT ["/usr/bin/java", "-jar","/usr/share/api/backend-service.jar"]

ADD target/lib /usr/share/api/lib
ADD target/backend-service.jar  /usr/share/api/backend-service.jar