package org.bankaya.pokemonservice.infrastructure.client.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bankaya.soap.pokemon.v1.AbilitySlot;
import com.bankaya.soap.pokemon.v1.HeldItem;
import com.bankaya.soap.pokemon.v1.VersionDetail;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PokemonMapperServiceImplTest {

  private PokemonMapperServiceImpl pokemonMapperService;

  @BeforeEach
  void setUp() {
    pokemonMapperService = new PokemonMapperServiceImpl();
  }

  @Test
  void testMapAbilities() {
    List<Map<String, Object>> abilitiesData = new ArrayList<>();
    Map<String, Object> ability1 = new HashMap<>();
    Map<String, Object> ability1Map = new HashMap<>();
    ability1Map.put("name", "overgrow");
    ability1Map.put("url", "https://pokeapi.co/api/v2/ability/65/");
    ability1.put("ability", ability1Map);
    ability1.put("is_hidden", false);
    ability1.put("slot", 1);
    abilitiesData.add(ability1);

    List<AbilitySlot> result = pokemonMapperService.mapAbilities(abilitiesData);

    assertEquals(1, result.size());
    assertEquals("overgrow", result.get(0).getAbility().getName());
    assertEquals("https://pokeapi.co/api/v2/ability/65/", result.get(0).getAbility().getUrl());
    assertFalse(result.get(0).isIsHidden());
    assertEquals(1, result.get(0).getSlot());
  }

  @Test
  void testMapAbilities_NullInput() {
    List<AbilitySlot> result = pokemonMapperService.mapAbilities(null);

    assertTrue(result.isEmpty());
  }

  @Test
  void testMapHeldItems() {
    List<Map<String, Object>> heldItemsData = getMaps();

    List<HeldItem> result = pokemonMapperService.mapHeldItems(heldItemsData);

    assertEquals(1, result.size());
    assertEquals("leftovers", result.get(0).getItem().getName());
    assertEquals("https://pokeapi.co/api/v2/item/234/", result.get(0).getItem().getUrl());
    assertEquals(1, result.get(0).getVersionDetails().size());

    VersionDetail versionDetail = result.get(0).getVersionDetails().get(0);
    assertEquals(50, versionDetail.getRarity());
    assertEquals("gold", versionDetail.getVersion().getName());
    assertEquals("https://pokeapi.co/api/v2/version/12/", versionDetail.getVersion().getUrl());
  }

  private static List<Map<String, Object>> getMaps() {
    List<Map<String, Object>> heldItemsData = new ArrayList<>();
    Map<String, Object> heldItem1 = new HashMap<>();
    Map<String, Object> itemDetail = new HashMap<>();
    itemDetail.put("name", "leftovers");
    itemDetail.put("url", "https://pokeapi.co/api/v2/item/234/");
    heldItem1.put("item", itemDetail);

    List<Map<String, Object>> versionDetailsList = new ArrayList<>();
    Map<String, Object> versionDetailMap = new HashMap<>();
    versionDetailMap.put("rarity", 50);

    Map<String, Object> versionMap = new HashMap<>();
    versionMap.put("name", "gold");
    versionMap.put("url", "https://pokeapi.co/api/v2/version/12/");
    versionDetailMap.put("version", versionMap);
    versionDetailsList.add(versionDetailMap);

    heldItem1.put("version_details", versionDetailsList);
    heldItemsData.add(heldItem1);
    return heldItemsData;
  }

  @Test
  void testMapHeldItems_NullInput() {
    List<HeldItem> result = pokemonMapperService.mapHeldItems(null);

    assertTrue(result.isEmpty());
  }
}
