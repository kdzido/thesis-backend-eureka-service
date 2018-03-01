#!/usr/bin/env bash

./gradlew :backend-eureka-service:clean :backend-eureka-service:build :backend-eureka-service:buildDockerImage && \
    docker run -ti --rm -p 8761:8761 kdzido/eurekaservice:latest

# -ti - interactive mode (ctrl-c) to stop container
# --rm - container will be removed after stop
