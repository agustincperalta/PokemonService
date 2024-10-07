package org.bankaya.pokemonservice.infrastructure.client.impl;

import com.bankaya.soap.pokemon.v1.Ability;
import com.bankaya.soap.pokemon.v1.AbilitySlot;
import com.bankaya.soap.pokemon.v1.HeldItem;
import com.bankaya.soap.pokemon.v1.ItemDetail;
import com.bankaya.soap.pokemon.v1.Version;
import com.bankaya.soap.pokemon.v1.VersionDetail;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bankaya.pokemonservice.infrastructure.client.PokemonMapperService;
import org.springframework.stereotype.Service;

@Service
public class PokemonMapperServiceImpl implements PokemonMapperService {

  @Override
  public List<AbilitySlot> mapAbilities(List<Map<String, Object>> abilities) {
    List<AbilitySlot> abilitySlots = new ArrayList<>();

    if (abilities != null) {
      for (Map<String, Object> abilityEntry : abilities) {
        Map<String, Object> abilityMap = (Map<String, Object>) abilityEntry.get("ability");

        Ability ability = new Ability();
        ability.setName((String) abilityMap.get("name"));
        ability.setUrl((String) abilityMap.get("url"));

        AbilitySlot abilitySlot = new AbilitySlot();
        abilitySlot.setAbility(ability);
        abilitySlot.setIsHidden((Boolean) abilityEntry.get("is_hidden"));
        abilitySlot.setSlot((Integer) abilityEntry.get("slot"));

        abilitySlots.add(abilitySlot);
      }
    }

    return abilitySlots;
  }

  @Override
  public List<HeldItem> mapHeldItems(List<Map<String, Object>> heldItemsData) {
    List<HeldItem> heldItems = new ArrayList<>();

    if (heldItemsData != null) {
      for (Map<String, Object> heldItemEntry : heldItemsData) {
        HeldItem heldItem = new HeldItem();

        Map<String, Object> itemMap = (Map<String, Object>) heldItemEntry.get("item");
        ItemDetail itemDetail = new ItemDetail();
        itemDetail.setName((String) itemMap.get("name"));
        itemDetail.setUrl((String) itemMap.get("url"));
        heldItem.setItem(itemDetail);

        List<Map<String, Object>> versionDetailsList = (List<Map<String, Object>>) heldItemEntry.get("version_details");
        List<VersionDetail> versionDetails = new ArrayList<>();

        for (Map<String, Object> versionDetailEntry : versionDetailsList) {
          VersionDetail versionDetail = new VersionDetail();
          versionDetail.setRarity((Integer) versionDetailEntry.get("rarity"));

          Map<String, Object> versionMap = (Map<String, Object>) versionDetailEntry.get("version");
          Version version = new Version();
          version.setName((String) versionMap.get("name"));
          version.setUrl((String) versionMap.get("url"));
          versionDetail.setVersion(version);

          versionDetails.add(versionDetail);
        }

        heldItem.getVersionDetails().addAll(versionDetails);

        heldItems.add(heldItem);
      }
    }

    return heldItems;
  }
}
