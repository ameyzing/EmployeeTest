FROM openjdk:8
ADD target/employee-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "employee-0.0.1-SNAPSHOT.jar"]