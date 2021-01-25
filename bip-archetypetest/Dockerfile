FROM registry.access.redhat.com/openjdk/openjdk-11-rhel7:1.1

USER root

# add the nexus yum repository
ADD ./scripts/nexus.repo /etc/yum.repos.d
RUN yum update -y

# Delete vulnerable jar if it exists (force flag accomplishes this)
RUN rm -f /usr/share/java/prometheus-jmx-exporter/jmx_prometheus_javaagent.jar


USER jboss

# Add a volume pointing to /tmp
VOLUME /tmp

# The application's jar file
ARG JAR_FILE=target/bip-archetypetest.jar

# Add the application's jar to the container
ADD ${JAR_FILE} bip-archetypetest.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./bip-archetypetest.jar"]

ADD ./scripts/wait-for-it.sh /wait-for-it.sh
