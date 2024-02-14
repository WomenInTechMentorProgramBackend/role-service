FROM openjdk:17-oracle
COPY  target/role-service-0.0.1-SNAPSHOT.jar role-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/role-service-0.0.1-SNAPSHOT.jar"]
