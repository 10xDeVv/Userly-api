FROM maven:3.9.4-eclipse-temurin-17-alpine

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/Userly-0.0.1-SNAPSHOT.jar"]
