#!/bin/bash

#Load VA certificates from container's /tmp folder.
#This folder should be populated through a k8s ConfigMap.
#If this folder is empty, script simply runs the app jar file
echo 'Loading certificates...'
for file in $(ls /tmp/*.cer);
    do keytool -importcert -alias $file -keystore "/tmp/truststore.jks" -noprompt -storepass changeit -file $file;
done
echo 'Completed loading certificates to custom trust store'
echo 'Loading certs from JAVA_HOME trust store to the custom trust store created above.'
keytool -importkeystore -srckeystore $JAVA_HOME/lib/security/cacerts -srcstorepass changeit -destkeystore "/tmp/truststore.jks" -deststorepass changeit
JAVA_OPTS=$JAVA_OPTS" -Djavax.net.ssl.trustStore=/tmp/truststore.jks"
java -Djavax.net.ssl.trustStore=/tmp/truststore.jks -Djava.security.egd=file:/dev/./urandom -jar ./bip-archetypetest.jar