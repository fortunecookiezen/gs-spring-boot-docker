package hello;

import java.io.FileNotFoundException;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.context.embedded.*;
import org.springframework.boot.context.embedded.tomcat.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;

@Configuration
public class SecureTomcatConfiguration {
 @Bean
 public EmbeddedServletContainerFactory servletContainer() throws FileNotFoundException {
   TomcatEmbeddedServletContainerFactory f = new TomcatEmbeddedServletContainerFactory(){
     @Override
     protected void postProcessContext(Context context) {
       SecurityConstraint securityConstraint = new SecurityConstraint();
       securityConstraint.setUserConstraint("CONFIDENTIAL");
       SecurityCollection collection = new SecurityCollection();
       collection.addPattern("/*");
       securityConstraint.addCollection(collection);
       context.addConstraint(securityConstraint);
     }
   };

   f.addAdditionalTomcatConnectors(initiateHttpConnector());
   f.addAdditionalTomcatConnectors(createSslConnector());
   return f;
 }
 private Connector createSslConnector() throws FileNotFoundException {
   Connector connector = new
   Connector(Http11NioProtocol.class.getName());
   Http11NioProtocol protocol =
   (Http11NioProtocol)connector.getProtocolHandler();
   connector.setPort(8443);
   connector.setSecure(true);
   connector.setScheme("https");
   protocol.setSSLEnabled(true);
   protocol.setKeyAlias("gs-spring-boot-docker-demo");
   protocol.setKeystorePass("bootdemo");
   protocol.setKeystoreFile(ResourceUtils
     .getFile("src/main/resources/tomcat.keystore")
     .getAbsolutePath());
   protocol.setSslProtocol("TLS");
   return connector;
 }



 private Connector initiateHttpConnector() {
   Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
   connector.setScheme("http");
   connector.setPort(8080);
   connector.setSecure(false);
   connector.setRedirectPort(8443);

   return connector;
 }

}
