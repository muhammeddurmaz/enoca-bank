FROM openjdk:11
WORKDIR /app
ADD target/enoca-bank-0.0.1-SNAPSHOT.jar enoca-bank-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","enoca-bank-0.0.1-SNAPSHOT.jar"]