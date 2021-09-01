FROM maven:3.6.0-jdk-11-slim

EXPOSE 9000

RUN mkdir /app
ADD . /app
WORKDIR /app
RUN mvn -f /app/pom.xml clean package
RUN mv /app/target/*.jar /app/app.jar

ENTRYPOINT [ "sh", "-c", "java -jar /app/*.jar" ]