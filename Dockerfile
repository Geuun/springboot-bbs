###############################################################################################
##
##  Record for me in the future (:D)
##  If you're still building and using the server on RaspberryPi
##  Check the contents below
##  Server architecture : arm64(arrch64) == arm64/v8
##  So we need an image that supports arm64/v8
##  
##  Example)
##  Check https://hub.docker.com/_/gradle OS/ARCH tab :)
##  (If the tool you are going to use does not support arm, Search on the DockerHub)
##  Then to use CI/CD you have to build multi-cpu architecture for every architecture !
##
###############################################################################################

FROM 7.6-jdk11-jammy AS builder
WORKDIR /build

# Enables new dependency packages to be downloaded only when Gradle files are changed
COPY build.gradle settings.gradle /build/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

# Building Applications from Builder Images
COPY . /build
RUN gradle build -x test --parallel

# APP
FROM openjdk:11.0-slim
WORKDIR /app

# Copy only jar files from the builder image
COPY --from=builder /build/build/libs/*-SNAPSHOT.jar ./app.jar

EXPOSE 8080

# Run with 'Nobody' permission instead of 'root'
USER nobody
ENTRYPOINT [                                                \
   "java",                                                 \
   "-jar",                                                 \
   "-Djava.security.egd=file:/dev/./urandom",              \
   "-Dsun.net.inetaddr.ttl=0",                             \
   "app.jar"              \
]
