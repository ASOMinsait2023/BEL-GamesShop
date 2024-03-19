FROM openjdk:20

WORKDIR /app

COPY ./microservice-eureka/target/microservice-eureka-0.0.1-SNAPSHOT.jar .

EXPOSE 7095

CMD ["java", "-jar", "microservice-eureka-0.0.1-SNAPSHOT.jar"]