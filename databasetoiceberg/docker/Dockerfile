# Builder stage: use Maven 3.9.8 with Eclipse Temurin JDK 17
FROM maven:3.9.8-eclipse-temurin-17 AS builder
WORKDIR /workspace

# copy only what is needed to leverage docker cache
COPY pom.xml .
COPY src ./src

# Use non-interactive Maven batch mode, produce packaged jar (skip tests for speed; CI may run tests separately)
RUN mvn -B -DskipTests clean package

# Runtime stage: minimal JRE
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy artifact produced by the builder stage
# Adjust path if your build produces a different jar name (or use assembly plugin for a fat jar)
COPY --from=builder /workspace/target/*.jar app.jar

# Expose ports if needed (example for web UI)
# EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]