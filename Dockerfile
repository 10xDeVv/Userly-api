FROM maven:3.9.4-eclipse-temurin-17-alpine

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/Userly-0.0.1-SNAPSHOT.jar"]
