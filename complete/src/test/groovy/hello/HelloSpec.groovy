package hello

import spock.lang.Specification
import org.springframework.boot.test.IntegrationTest;
import org.springframework.beans.factory.annotation.Value;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.annotation.DirtiesContext;
// @RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
@DirtiesContext

class HelloControllerSpec extends Specification {

      @Value('${local.server.port}')
      int port

  void "Test that spock is working"() {

          expect:
            true
      }
  def "test https"(){
    println "The HTTPS port in use is ${this.port}"
    expect:
    true
  }
}
