FROM java:8
VOLUME /tmp
ADD gs-spring-boot-docker-0.2.0.jar app.jar
ADD tomcat.keystore /root/.keystore
RUN bash -c 'touch /app.jar /root/.keystore'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8080 8443
