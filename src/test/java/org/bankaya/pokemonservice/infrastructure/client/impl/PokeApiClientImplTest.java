package org.bankaya.pokemonservice.infrastructure.client.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class PokeApiClientImplTest {

  @Mock
  private WebClient webClient;

  @Mock
  private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

  @Mock
  private WebClient.RequestHeadersSpec requestHeadersSpec;

  @Mock
  private WebClient.ResponseSpec responseSpec;

  @Mock
  private Mono<Map<String, Object>> monoResponse;

  @InjectMocks
  private PokeApiClientImpl pokeApiClientImpl;

  @Test
  @SuppressWarnings("unchecked")
  void testGetPokemonDataByName() {
    String pokemonName = "pikachu";
    Map<String, Object> expectedResponse = Map.of("name", "pikachu", "id", 25);

    when(webClient.get()).thenReturn(requestHeadersUriSpec);
    when(requestHeadersUriSpec.uri("/{name}", pokemonName)).thenReturn(requestHeadersSpec);
    when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
    when(responseSpec.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
    })).thenReturn(monoResponse);
    when(monoResponse.block()).thenReturn(expectedResponse);

    Map<String, Object> actualResponse = pokeApiClientImpl.getPokemonDataByName(pokemonName);

    assertNotNull(actualResponse);
    assertEquals(expectedResponse, actualResponse);

    verify(webClient).get();
    verify(requestHeadersUriSpec).uri("/{name}", pokemonName);
    verify(requestHeadersSpec).retrieve();
    verify(responseSpec).bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
    });
    verify(monoResponse).block();
  }
}
