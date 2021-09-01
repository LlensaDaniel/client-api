FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
ARG JAR_FILE
COPY "/target/client-api-1.0.0.jar" app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
