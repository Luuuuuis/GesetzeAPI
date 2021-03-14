FROM maven:3.6-openjdk-15 as builder

WORKDIR /app

COPY src ./src/
COPY pom.xml ./

# Build a release artifact.
RUN mvn package -DskipTests

FROM openjdk:15

# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/target/GesetzeAPI.jar /prod/GesetzeAPI.jar


CMD ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "--enable-preview", "/prod/GesetzeAPI.jar"]