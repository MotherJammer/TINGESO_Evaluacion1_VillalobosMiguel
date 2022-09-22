FROM openjdk:17
ARG JAR_FILE=build/libs/CalculadoraSalarios-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} CalculadoraSalarios-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/CalculadoraSalarios-0.0.1-SNAPSHOT.jar"]