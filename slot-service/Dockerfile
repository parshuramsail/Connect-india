FROM openjdk:11
ADD ./target/slot-service-0.0.1-SNAPSHOT.jar /usr/src/slot-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
EXPOSE 8087
ENTRYPOINT ["java","-jar","slot-service-0.0.1-SNAPSHOT.jar"]