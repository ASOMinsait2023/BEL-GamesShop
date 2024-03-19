FROM openjdk:20

WORKDIR /app

COPY ./microservice-videogames/target/microservice-videogames-0.0.1-SNAPSHOT.jar .

EXPOSE 7090

CMD ["java", "-jar", "microservice-videogames-0.0.1-SNAPSHOT.jar"]
