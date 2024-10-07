package org.bankaya.pokemonservice.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  public static final int BYTE_COUNT = 10 * 1024 * 1024;

  @Bean
  public WebClient webClient(
      @Value("${pokeapi.url:https://pokeapi.co/api/v2/pokemon}") String pokeApiUrl) {
    return WebClient.builder()
        .baseUrl(pokeApiUrl)
        .codecs(clientCodecConfigurer -> clientCodecConfigurer.defaultCodecs()
            .maxInMemorySize(BYTE_COUNT))
        .build();
  }
}
