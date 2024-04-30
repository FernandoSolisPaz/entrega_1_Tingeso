FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} evaluacion_1_tingeso.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "./evaluacion_1_tingeso.jar"]