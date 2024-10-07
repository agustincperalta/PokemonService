package org.bankaya.pokemonservice.application.impl;

import com.bankaya.soap.pokemon.v1.GetPokemonAbilitiesResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonBaseExperienceResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonHeldItemsResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonIdResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonLocationAreaEncountersResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonNameResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bankaya.pokemonservice.infrastructure.client.PokemonMapperService;
import org.bankaya.pokemonservice.infrastructure.repository.RequestLog;
import org.bankaya.pokemonservice.application.PokemonService;
import org.bankaya.pokemonservice.infrastructure.client.PokeApiClient;
import org.bankaya.pokemonservice.infrastructure.repository.RequestLogRepository;
import org.bankaya.pokemonservice.infrastructure.soap.LocalPartConstants;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PokemonServiceImpl implements PokemonService {

  private final PokeApiClient pokeApiClient;
  private final PokemonMapperService pokemonMapperService;
  private final RequestLogRepository requestLogRepository;
  private final HttpServletRequest request;

  @SuppressWarnings("unchecked")
  @Override
  public GetPokemonAbilitiesResponse getPokemonAbilitiesByName(String name) {
    Map<String, Object> pokemonData = pokeApiClient.getPokemonDataByName(name);
    GetPokemonAbilitiesResponse response = new GetPokemonAbilitiesResponse();
    List<Map<String, Object>> abilities = (List<Map<String, Object>>) pokemonData.get("abilities");
    log.info("m=getPokemonByName, abilities : {}", abilities);
    response.getAbilities().addAll(pokemonMapperService.mapAbilities(abilities));
    saveLog(LocalPartConstants.GET_POKEMON_ABILITIES_REQUEST);
    return response;
  }

  @Override
  public GetPokemonBaseExperienceResponse getPokemonBaseExperienceByName(String name) {
    Map<String, Object> pokemonData = pokeApiClient.getPokemonDataByName(name);

    GetPokemonBaseExperienceResponse response = new GetPokemonBaseExperienceResponse();
    Integer baseExperience = (Integer) pokemonData.get("base_experience");
    log.info("m=getPokemonBaseExperienceByName, base experience: {}", baseExperience);
    response.setBaseExperience(baseExperience);
    saveLog(LocalPartConstants.GET_POKEMON_BASE_EXPERIENCE_REQUEST);
    return response;
  }

  @Override
  @SuppressWarnings("unchecked")
  public GetPokemonHeldItemsResponse getPokemonHeldItemsByName(String name) {
    Map<String, Object> pokemonData = pokeApiClient.getPokemonDataByName(name);

    GetPokemonHeldItemsResponse response = new GetPokemonHeldItemsResponse();
    List<Map<String, Object>> heldItems = (List<Map<String, Object>>) pokemonData.get("held_items");
    log.info("m=getPokemonHeldItemsByName, held items: {}", heldItems);
    response.getHeldItems().addAll(pokemonMapperService.mapHeldItems(heldItems));
    saveLog(LocalPartConstants.GET_POKEMON_HELD_ITEMS_REQUEST);
    return response;
  }

  @Override
  public GetPokemonIdResponse getPokemonIdByName(String name) {
    Map<String, Object> pokemonData = pokeApiClient.getPokemonDataByName(name);

    GetPokemonIdResponse response = new GetPokemonIdResponse();
    Integer id = (Integer) pokemonData.get("id");
    log.info("m=getPokemonIdByName, id: {}", id);
    response.setId(id);
    saveLog(LocalPartConstants.GET_POKEMON_ID_REQUEST);
    return response;
  }

  @Override
  public GetPokemonLocationAreaEncountersResponse getPokemonLocationAreaEncounterByName(
      String name) {
    Map<String, Object> pokemonData = pokeApiClient.getPokemonDataByName(name);
    GetPokemonLocationAreaEncountersResponse response = new GetPokemonLocationAreaEncountersResponse();
    String locationAreaEncounters = (String) pokemonData.get("location_area_encounters");
    log.info("m=getPokemonLocationAreaEncounterByName, local area encounters: {}",
        locationAreaEncounters);
    response.setLocationAreaEncounters(locationAreaEncounters);
    saveLog(LocalPartConstants.GET_POKEMON_LOCATION_AREA_ENCOUNTERS_REQUEST);
    return response;
  }

  @Override
  public GetPokemonNameResponse getPokemonNameByName(String name) {
    Map<String, Object> pokemonData = pokeApiClient.getPokemonDataByName(name);
    GetPokemonNameResponse response = new GetPokemonNameResponse();
    String pokemonName = (String) pokemonData.get("name");
    log.info("m=getPokemonNameByName, name: {}", pokemonName);
    saveLog(LocalPartConstants.GET_POKEMON_NAME_REQUEST);
    response.setName(pokemonName);
    return response;
  }

  private void saveLog(String operation) {
    String ipAddress = request.getHeader("X-Forwarded-For");
    if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getRemoteAddr();
    }
    RequestLog log = RequestLog.builder()
        .ipAddress(ipAddress)
        .requestTime(LocalDateTime.now())
        .methodName(operation).build();
    requestLogRepository.save(log);
  }

}
