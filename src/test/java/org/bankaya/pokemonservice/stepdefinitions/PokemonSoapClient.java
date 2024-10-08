package org.bankaya.pokemonservice.stepdefinitions;

import com.bankaya.soap.pokemon.v1.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;


@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class PokemonSoapClient {

  private final String SERVER_URL = "http://localhost";
  private final String POKEMON_ENDPOINT = "/ws/pokemon";

  @LocalServerPort
  private int port;


  private WebServiceTemplate webServiceTemplate = new WebServiceTemplate();

  private String pokemonEndpoint() {
    return SERVER_URL + ":" + port + POKEMON_ENDPOINT;
  }

  public GetPokemonAbilitiesResponse getPokemonAbilities(String pokemonName) {
    GetPokemonAbilitiesRequest request = new GetPokemonAbilitiesRequest();
    request.setPokemonName(pokemonName);
    return (GetPokemonAbilitiesResponse) webServiceTemplate.marshalSendAndReceive(pokemonEndpoint(),
        request);
  }

  public GetPokemonBaseExperienceResponse getPokemonBaseExperience(String pokemonName) {
    GetPokemonBaseExperienceRequest request = new GetPokemonBaseExperienceRequest();
    request.setPokemonName(pokemonName);
    return (GetPokemonBaseExperienceResponse) webServiceTemplate.marshalSendAndReceive(
        pokemonEndpoint(), request);
  }

  public GetPokemonHeldItemsResponse getPokemonHeldItems(String pokemonName) {
    GetPokemonHeldItemsRequest request = new GetPokemonHeldItemsRequest();
    request.setPokemonName(pokemonName);
    return (GetPokemonHeldItemsResponse) webServiceTemplate.marshalSendAndReceive(pokemonEndpoint(),
        request);
  }

  public GetPokemonIdResponse getPokemonId(String pokemonName) {
    GetPokemonIdRequest request = new GetPokemonIdRequest();
    request.setPokemonName(pokemonName);
    return (GetPokemonIdResponse) webServiceTemplate.marshalSendAndReceive(pokemonEndpoint(),
        request);
  }

  public GetPokemonLocationAreaEncountersResponse getPokemonLocationAreaEncounters(
      String pokemonName) {
    GetPokemonLocationAreaEncountersRequest request = new GetPokemonLocationAreaEncountersRequest();
    request.setPokemonName(pokemonName);
    return (GetPokemonLocationAreaEncountersResponse) webServiceTemplate.marshalSendAndReceive(
        pokemonEndpoint(), request);
  }

  public GetPokemonNameResponse getPokemonName(String pokemonName) {
    GetPokemonNameRequest request = new GetPokemonNameRequest();
    request.setPokemonName(pokemonName);
    return (GetPokemonNameResponse) webServiceTemplate.marshalSendAndReceive(pokemonEndpoint(),
        request);
  }
}