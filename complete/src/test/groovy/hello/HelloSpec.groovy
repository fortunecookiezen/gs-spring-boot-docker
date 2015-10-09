package hello

import spock.lang.Specification
import org.springframework.boot.test.IntegrationTest;
import org.springframework.beans.factory.annotation.Value;

@IntegrationTest("server.port=0")
class HelloControllerSpec extends Specification {

      @Value('${local.server.port}')
      int port

  void "Test that spock is working"() {

          expect:
            true
      }
  def "test https"(){
    println this.port
    expect:
    true
  }
}
