FROM openjdk:11.0.2

ENTRYPOINT ["/usr/bin/java","-jar","/app.jar"]

ARG JAR_FILE
ADD target/${JAR_FILE}  /app.jar