package org.bankaya.pokemonservice.infrastructure.client;

import java.util.Map;

public interface PokeApiClient {
  public Map<String, Object> getPokemonDataByName(String name);
}
