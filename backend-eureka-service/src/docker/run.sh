#!/bin/sh
echo "-> Starting Eureka service (service discovery)"
java    -XX:+PrintFlagsFinal \
        -XX:+UnlockExperimentalVMOptions \
        -XX:+UseCGroupMemoryLimitForHeap \
        -Djava.security.egd=file:/dev/./urandom \
        -jar /usr/local/eureka-service/@springBootJar@
