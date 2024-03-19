FROM openjdk:20

WORKDIR /app

COPY ./microservice-config/target/microservice-config-0.0.1-SNAPSHOT.jar .

EXPOSE 8888

CMD ["java", "-jar", "microservice-config-0.0.1-SNAPSHOT.jar"]
