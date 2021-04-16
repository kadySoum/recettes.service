FROM adoptopenjdk:11-jre-hotspot
#ARG JAR_FILE=*.jar
COPY target/recettes.service-0.0.1-SNAPSHOT.jar /recettes.jar
ENTRYPOINT ["java", "-jar", "recettes.jar"]