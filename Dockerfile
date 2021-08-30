FROM quay-enterprise-quay-quay-enterprise.apps.ocppaz0.ar.bsch/santandertec/santander-tecnologia-docker-base-images-java-maven:v11-maven

VOLUME /tmp
ADD /target/*.jar app.jar

CMD [ "sh", "-c", "java -jar app.jar" ]
