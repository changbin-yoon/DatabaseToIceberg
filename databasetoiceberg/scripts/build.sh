#!/bin/bash

# Navigate to the project directory
cd "$(dirname "$0")/.."

# Clean previous builds
mvn clean

# Build the project and create the JAR file
mvn package

# Build the Docker image
docker build -t databasetoiceberg:latest -f docker/Dockerfile .

# Output success message
echo "Build completed successfully."