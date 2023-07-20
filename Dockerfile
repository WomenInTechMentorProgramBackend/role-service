FROM eclipse-temurin:latest
ARG DEPENDENCY=target/dependency
COPY target/*.jar app.jar
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-jar","/app.jar"]
