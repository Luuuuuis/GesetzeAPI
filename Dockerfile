FROM springci/spring-native:java17-0.11.x as builder

WORKDIR /app/

COPY GesetzeAPI-application/src ./src/
COPY GesetzeAPI-application/pom.xml ./

# Build a release artifact.
RUN mvn package -Pnative -DskipTests

FROM oraclelinux:8-slim

# Copy the jar to the production image from the builder stage.
COPY --from=builder "/app/target/gesetzeapi-application" "/prod/gesetzeapi-application"
ADD GesetzeAPI-application/laws /prod/laws

EXPOSE 80

CMD ["sh", "-c", "/prod/gesetzeapi-application" ]