FROM openjdk:11
ADD ./target/helpDesk-service-0.0.1-SNAPSHOT.jar /usr/src/helpDesk-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "helpDesk-service-0.0.1-SNAPSHOT.jar"]