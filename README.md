# Argus

# Build Docker Instructions

docker build -t argus Docker

# Run Docker Instructions

docker run -d -p 80:8080 argus -Dspring-boot.run.arguments=--mirror=<MIRROR_URL>
