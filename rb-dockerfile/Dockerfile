FROM openjdk:8u111-alpine
EXPOSE 8080
ADD target/*.jar robotdreams.jar
ENTRYPOINT ["java","-jar","/robotdreams.jar"]