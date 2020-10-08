FROM registry.access.redhat.com/openjdk/openjdk-11-rhel7:1.1

# Add a volume pointing to /tmp
VOLUME /tmp

# The application's jar file
ARG JAR_FILE=target/bip-archetypetest.jar

# Add the application's jar to the container
ADD ${JAR_FILE} bip-archetypetest.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./bip-archetypetest.jar"]

ADD ./scripts/wait-for-it.sh /wait-for-it.sh
