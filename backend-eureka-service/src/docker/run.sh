#!/bin/sh
echo "-> Starting Eureka service (service discovery)"
java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/eureka-service/@springBootJar@
