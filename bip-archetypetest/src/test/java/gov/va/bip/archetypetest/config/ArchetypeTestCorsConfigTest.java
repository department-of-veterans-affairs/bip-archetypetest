package gov.va.bip.archetypetest.config;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = { "spring.cloud.bus.enabled=false", "spring.cloud.discovery.enabled=false",
        "spring.cloud.consul.enabled=false", "spring.cloud.config.discovery.enabled=false",
        "spring.cloud.vault.enabled=false", "bip.framework.security.http.cors.enabled=true",
        "bip-archetypetest.origins=https://bip-archetypetest-ui-dev.dev8.bip.va.gov" })
public class ArchetypeTestCorsConfigTest extends TestCase {

  @Autowired
  private ArchetypeTestCorsConfig archetypeTestCorsConfig;

  @Autowired
  private RequestMappingHandlerMapping requestMappingHandlerMapping;

  @Test
  public void testCorsConfigurer() {
    WebMvcConfigurer corsConfigurer
        = archetypeTestCorsConfig.corsConfigurer();

    Assert.assertEquals("https://bip-archetypetest-ui-dev.dev8.bip.va.gov",
        this.requestMappingHandlerMapping.getCorsConfigurations().values().iterator().next().getAllowedOrigins().get(0));

  }
}