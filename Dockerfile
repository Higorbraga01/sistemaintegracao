FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/sistema-integracao.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]