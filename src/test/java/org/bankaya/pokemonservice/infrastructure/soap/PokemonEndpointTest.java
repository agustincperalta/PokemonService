package org.bankaya.pokemonservice.infrastructure.soap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.bankaya.pokemonservice.application.PokemonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PokemonEndpointTest {

  @Mock
  private PokemonService pokemonService;

  @InjectMocks
  private PokemonEndpoint pokemonEndpoint;


  @Test
  void testGetPokemonAbilities() {
    GetPokemonAbilitiesRequest request = new GetPokemonAbilitiesRequest();
    request.setPokemonName("pikachu");
    GetPokemonAbilitiesResponse expectedResponse = new GetPokemonAbilitiesResponse();
    when(pokemonService.getPokemonAbilitiesByName("pikachu")).thenReturn(expectedResponse);

    GetPokemonAbilitiesResponse actualResponse = pokemonEndpoint.getPokemonAbilities(request);

    assertEquals(expectedResponse, actualResponse);
    verify(pokemonService).getPokemonAbilitiesByName("pikachu");
  }

  @Test
  void testGetPokemonBaseExperience() {
    GetPokemonBaseExperienceRequest request = new GetPokemonBaseExperienceRequest();
    request.setPokemonName("charizard");
    GetPokemonBaseExperienceResponse expectedResponse = new GetPokemonBaseExperienceResponse();
    when(pokemonService.getPokemonBaseExperienceByName("charizard")).thenReturn(expectedResponse);

    GetPokemonBaseExperienceResponse actualResponse = pokemonEndpoint.getPokemonBaseExperience(
        request);

    assertEquals(expectedResponse, actualResponse);
    verify(pokemonService).getPokemonBaseExperienceByName("charizard");
  }

  @Test
  void testGetPokemonHeldItems() {
    GetPokemonHeldItemsRequest request = new GetPokemonHeldItemsRequest();
    request.setPokemonName("bulbasaur");
    GetPokemonHeldItemsResponse expectedResponse = new GetPokemonHeldItemsResponse();
    when(pokemonService.getPokemonHeldItemsByName("bulbasaur")).thenReturn(expectedResponse);

    GetPokemonHeldItemsResponse actualResponse = pokemonEndpoint.getPokemonHeldItems(request);

    assertEquals(expectedResponse, actualResponse);
    verify(pokemonService).getPokemonHeldItemsByName("bulbasaur");
  }

  @Test
  void testGetPokemonId() {
    GetPokemonIdRequest request = new GetPokemonIdRequest();
    request.setPokemonName("mewtwo");
    GetPokemonIdResponse expectedResponse = new GetPokemonIdResponse();
    when(pokemonService.getPokemonIdByName("mewtwo")).thenReturn(expectedResponse);

    GetPokemonIdResponse actualResponse = pokemonEndpoint.getPokemonId(request);

    assertEquals(expectedResponse, actualResponse);
    verify(pokemonService).getPokemonIdByName("mewtwo");
  }

  @Test
  void testGetPokemonLocationAreaEncounters() {
    GetPokemonLocationAreaEncountersRequest request = new GetPokemonLocationAreaEncountersRequest();
    request.setPokemonName("eevee");
    GetPokemonLocationAreaEncountersResponse expectedResponse = new GetPokemonLocationAreaEncountersResponse();
    when(pokemonService.getPokemonLocationAreaEncounterByName("eevee")).thenReturn(
        expectedResponse);

    GetPokemonLocationAreaEncountersResponse actualResponse = pokemonEndpoint.getPokemonLocationAreaEncounters(
        request);

    assertEquals(expectedResponse, actualResponse);
    verify(pokemonService).getPokemonLocationAreaEncounterByName("eevee");
  }

  @Test
  void testGetPokemonName() {
    GetPokemonNameRequest request = new GetPokemonNameRequest();
    request.setPokemonName("squirtle");
    GetPokemonNameResponse expectedResponse = new GetPokemonNameResponse();
    when(pokemonService.getPokemonNameByName("squirtle")).thenReturn(expectedResponse);

    GetPokemonNameResponse actualResponse = pokemonEndpoint.getPokemonName(request);

    assertEquals(expectedResponse, actualResponse);
    verify(pokemonService).getPokemonNameByName("squirtle");
  }
}
