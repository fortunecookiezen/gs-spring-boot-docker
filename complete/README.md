
#Generating Local Certificate
In order to test https locally, you can run the following command on Linux to generate a keystore to test with.

```sh
keytool -genkey -alias gs-spring-boot-docker-demo -keyalg RSA -keystore src/main/resources/tomcat.keystore
```
