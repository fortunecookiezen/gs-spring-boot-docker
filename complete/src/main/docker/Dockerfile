FROM java:8
VOLUME /tmp
ADD gs-spring-boot-docker-0.2.0.jar app.jar
ADD tomcat.keystore /tomcat.keystore
RUN bash -c 'touch /app.jar /tomcat.keystore'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=prod","-jar","/app.jar"]
EXPOSE 8443
