package org.bankaya.pokemonservice.infrastructure.client.impl;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bankaya.pokemonservice.infrastructure.client.PokeApiClient;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
@AllArgsConstructor
public class PokeApiClientImpl implements PokeApiClient {

  private WebClient webClient;

  @Override
  public Map<String, Object> getPokemonDataByName(String name) {
    return webClient.get()
        .uri("/{name}", name)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
        })
        .block();
  }
}
