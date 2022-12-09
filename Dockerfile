FROM registry.access.redhat.com/ubi9/openjdk-17-runtime:1.13-9.1668596094

LABEL io.k8s.description="BALI POC Microservice"

COPY target/*.jar /opt/app.jar

USER 1001

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/app.jar"]
