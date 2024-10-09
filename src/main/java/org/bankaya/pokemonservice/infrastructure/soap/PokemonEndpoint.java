package org.bankaya.pokemonservice.infrastructure.soap;

import static org.bankaya.pokemonservice.infrastructure.soap.LocalPartConstants.GET_POKEMON_ABILITIES_REQUEST;
import static org.bankaya.pokemonservice.infrastructure.soap.LocalPartConstants.GET_POKEMON_BASE_EXPERIENCE_REQUEST;
import static org.bankaya.pokemonservice.infrastructure.soap.LocalPartConstants.GET_POKEMON_HELD_ITEMS_REQUEST;
import static org.bankaya.pokemonservice.infrastructure.soap.LocalPartConstants.GET_POKEMON_ID_REQUEST;
import static org.bankaya.pokemonservice.infrastructure.soap.LocalPartConstants.GET_POKEMON_LOCATION_AREA_ENCOUNTERS_REQUEST;
import static org.bankaya.pokemonservice.infrastructure.soap.LocalPartConstants.GET_POKEMON_NAME_REQUEST;
import static org.bankaya.pokemonservice.infrastructure.soap.LocalPartConstants.NAMESPACE_URI;

import com.bankaya.soap.pokemon.v1.GetPokemonAbilitiesRequest;
import com.bankaya.soap.pokemon.v1.GetPokemonAbilitiesResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonBaseExperienceRequest;
import com.bankaya.soap.pokemon.v1.GetPokemonBaseExperienceResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonHeldItemsRequest;
import com.bankaya.soap.pokemon.v1.GetPokemonHeldItemsResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonIdRequest;
import com.bankaya.soap.pokemon.v1.GetPokemonIdResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonLocationAreaEncountersRequest;
import com.bankaya.soap.pokemon.v1.GetPokemonLocationAreaEncountersResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonNameRequest;
import com.bankaya.soap.pokemon.v1.GetPokemonNameResponse;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bankaya.pokemonservice.application.PokemonService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@Endpoint
@Slf4j
@AllArgsConstructor
public class PokemonEndpoint {

  private final PokemonService pokemonService;

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = GET_POKEMON_ABILITIES_REQUEST)
  @ResponsePayload
  public GetPokemonAbilitiesResponse getPokemonAbilities(
      @RequestPayload GetPokemonAbilitiesRequest request) {
    String pokemonName = request.getPokemonName();
    log.info("m=getPokemonAbilities, getting abilities from: {}", pokemonName);
    return pokemonService.getPokemonAbilitiesByName(pokemonName);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = GET_POKEMON_BASE_EXPERIENCE_REQUEST)
  @ResponsePayload
  public GetPokemonBaseExperienceResponse getPokemonBaseExperience(
      @RequestPayload GetPokemonBaseExperienceRequest request) {
    String pokemonName = request.getPokemonName();
    log.info("m=getPokemonBaseExperience, getting base experience from: {}", pokemonName);
    return pokemonService.getPokemonBaseExperienceByName(pokemonName);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = GET_POKEMON_HELD_ITEMS_REQUEST)
  @ResponsePayload
  public GetPokemonHeldItemsResponse getPokemonHeldItems(
      @RequestPayload GetPokemonHeldItemsRequest request) {
    String pokemonName = request.getPokemonName();
    log.info("m=getPokemonHeldItems, getting held items from: {}", pokemonName);
    return pokemonService.getPokemonHeldItemsByName(pokemonName);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = GET_POKEMON_ID_REQUEST)
  @ResponsePayload
  public GetPokemonIdResponse getPokemonId(
      @RequestPayload GetPokemonIdRequest request) {
    String pokemonName = request.getPokemonName();
    log.info("m=getPokemonId, getting id from: {}", pokemonName);
    return pokemonService.getPokemonIdByName(pokemonName);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = GET_POKEMON_LOCATION_AREA_ENCOUNTERS_REQUEST)
  @ResponsePayload
  public GetPokemonLocationAreaEncountersResponse getPokemonLocationAreaEncounters(
      @RequestPayload GetPokemonLocationAreaEncountersRequest request) {
    String pokemonName = request.getPokemonName();
    log.info("m=getPokemonLocationAreaEncounters, getting location area encounters from: {}",
        pokemonName);
    return pokemonService.getPokemonLocationAreaEncounterByName(pokemonName);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = GET_POKEMON_NAME_REQUEST)
  @ResponsePayload
  public GetPokemonNameResponse getPokemonName(
      @RequestPayload GetPokemonNameRequest request) {
    String pokemonName = request.getPokemonName();
    log.info("m=getPokemonName, getting name from: {}", pokemonName);
    return pokemonService.getPokemonNameByName(pokemonName);
  }

}
