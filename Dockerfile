FROM maven:3.6-openjdk-15 as builder

WORKDIR /app

COPY GesetzeAPI-application/src ./src/
COPY GesetzeAPI-application/pom.xml ./

# Build a release artifact.
RUN mvn package -DskipTests

FROM openjdk:15

# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/target/GesetzeAPI.jar /prod/GesetzeAPI.jar
ADD GesetzeAPI-application/laws /prod/laws

EXPOSE 80

CMD ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "--enable-preview", "/prod/GesetzeAPI.jar"]