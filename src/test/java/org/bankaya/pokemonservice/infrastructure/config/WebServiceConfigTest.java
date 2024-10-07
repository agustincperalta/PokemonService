package org.bankaya.pokemonservice.infrastructure.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.xml.xsd.XsdSchema;

@SpringBootTest
class WebServiceConfigTest {
  @Autowired
  private WebServiceConfig webServiceConfig;

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private XsdSchema pokemonSchema;
  @Test
   void testMessageDispatcherServlet() {
    ServletRegistrationBean<MessageDispatcherServlet> servletBean = webServiceConfig.messageDispatcherServlet(applicationContext);
    assertNotNull(servletBean, "ServletRegistrationBean should not be null");
    assertEquals("/ws/*", servletBean.getUrlMappings().stream().toList().get(0), "URL Mapping should be '/ws/*'");
  }

  @Test
   void testPokemonSchema() {
    XsdSchema schema = webServiceConfig.pokemonSchema();
    assertNotNull(schema, "Pokemon XSD schema should not be null");
  }
}