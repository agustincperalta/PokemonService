package org.bankaya.pokemonservice.application.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bankaya.soap.pokemon.v1.AbilitySlot;
import com.bankaya.soap.pokemon.v1.GetPokemonAbilitiesResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonBaseExperienceResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonHeldItemsResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonIdResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonLocationAreaEncountersResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonNameResponse;
import com.bankaya.soap.pokemon.v1.HeldItem;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.bankaya.pokemonservice.infrastructure.client.PokeApiClient;
import org.bankaya.pokemonservice.infrastructure.client.PokemonMapperService;
import org.bankaya.pokemonservice.infrastructure.repository.RequestLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PokemonServiceImplTest {

  @Mock
  private PokeApiClient pokeApiClient;

  @Mock
  private PokemonMapperService pokemonMapperService;

  @Mock
  private RequestLogRepository requestLogRepository;

  @Mock
  private HttpServletRequest request;

  @InjectMocks
  private PokemonServiceImpl pokemonService;

  @BeforeEach
  void setUp() {
    Random random = new Random();
    String[] responses = {null, "", "unknown"};
    when(request.getHeader("X-Forwarded-For"))
        .thenAnswer(invocation -> responses[random.nextInt(responses.length)]);
    when(request.getRemoteAddr()).thenReturn("127.0.0.1");
  }

  @Test
  void testGetPokemonAbilitiesByName() {
    String name = "random_pokemon";
    Map<String, Object> mockPokemonData = Map.of("abilities", List.of(Map.of("ability", "static")));
    List<AbilitySlot> mappedAbilities = List.of(new AbilitySlot());

    when(pokeApiClient.getPokemonDataByName(name)).thenReturn(mockPokemonData);
    when(pokemonMapperService.mapAbilities(any())).thenReturn(mappedAbilities);

    GetPokemonAbilitiesResponse response = pokemonService.getPokemonAbilitiesByName(name);

    assertEquals(mappedAbilities, response.getAbilities());
    verify(requestLogRepository, times(1)).save(any());
    verify(pokeApiClient, times(1)).getPokemonDataByName(name);
  }

  @Test
  void testGetPokemonBaseExperienceByName() {
    String name = "pikachu";
    int baseExperience = 112;

    Map<String, Object> mockPokemonData = Map.of("base_experience", baseExperience);

    when(pokeApiClient.getPokemonDataByName(name)).thenReturn(mockPokemonData);

    GetPokemonBaseExperienceResponse response = pokemonService.getPokemonBaseExperienceByName(name);

    assertEquals(baseExperience, response.getBaseExperience());
    verify(requestLogRepository, times(1)).save(any());
    verify(pokeApiClient, times(1)).getPokemonDataByName(name);
  }

  @Test
  void testGetPokemonHeldItemsByName() {
    String name = "pikachu";
    Map<String, Object> mockPokemonData = Map.of("held_items",
        List.of(Map.of("item", "light-ball")));
    List<HeldItem> mappedHeldItems = List.of(new HeldItem());

    when(pokeApiClient.getPokemonDataByName(name)).thenReturn(mockPokemonData);
    when(pokemonMapperService.mapHeldItems(any())).thenReturn(mappedHeldItems);

    GetPokemonHeldItemsResponse response = pokemonService.getPokemonHeldItemsByName(name);

    assertEquals(mappedHeldItems, response.getHeldItems());
    verify(requestLogRepository, times(1)).save(any());
    verify(pokeApiClient, times(1)).getPokemonDataByName(name);
  }

  @Test
  void testGetPokemonIdByName() {
    String name = "pikachu";
    int expectedId = 25;
    Map<String, Object> mockPokemonData = Map.of("id", expectedId);

    when(pokeApiClient.getPokemonDataByName(name)).thenReturn(mockPokemonData);

    GetPokemonIdResponse response = pokemonService.getPokemonIdByName(name);

    assertEquals(expectedId, response.getId());
    verify(requestLogRepository, times(1)).save(any());
    verify(pokeApiClient, times(1)).getPokemonDataByName(name);
  }

  @Test
  void testGetPokemonLocationAreaEncounterByName() {
    String name = "pikachu";
    String expectedLocationAreaEncounters = "forest";
    Map<String, Object> mockPokemonData = Map.of("location_area_encounters",
        expectedLocationAreaEncounters);

    when(pokeApiClient.getPokemonDataByName(name)).thenReturn(mockPokemonData);

    GetPokemonLocationAreaEncountersResponse response = pokemonService.getPokemonLocationAreaEncounterByName(
        name);

    assertEquals(expectedLocationAreaEncounters, response.getLocationAreaEncounters());
    verify(requestLogRepository, times(1)).save(any());
    verify(pokeApiClient, times(1)).getPokemonDataByName(name);
  }

  @Test
  void testGetPokemonNameByName() {
    String name = "pikachu";
    String expectedPokemonName = "Pikachu";
    Map<String, Object> mockPokemonData = Map.of("name", expectedPokemonName);

    when(pokeApiClient.getPokemonDataByName(name)).thenReturn(mockPokemonData);

    GetPokemonNameResponse response = pokemonService.getPokemonNameByName(name);

    assertEquals(expectedPokemonName, response.getName());
    verify(requestLogRepository, times(1)).save(any());
    verify(pokeApiClient, times(1)).getPokemonDataByName(name);
  }
}
