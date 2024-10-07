package org.bankaya.pokemonservice.application;

import com.bankaya.soap.pokemon.v1.GetPokemonAbilitiesResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonBaseExperienceResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonHeldItemsResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonIdResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonLocationAreaEncountersResponse;
import com.bankaya.soap.pokemon.v1.GetPokemonNameResponse;

public interface PokemonService {
  GetPokemonAbilitiesResponse getPokemonAbilitiesByName(String name);
  GetPokemonBaseExperienceResponse getPokemonBaseExperienceByName(String name);
  GetPokemonHeldItemsResponse getPokemonHeldItemsByName(String name);
  GetPokemonIdResponse getPokemonIdByName(String name);
  GetPokemonLocationAreaEncountersResponse getPokemonLocationAreaEncounterByName(String name);
  GetPokemonNameResponse getPokemonNameByName(String name);

}
