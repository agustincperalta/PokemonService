package org.bankaya.pokemonservice.infrastructure.client;

import com.bankaya.soap.pokemon.v1.AbilitySlot;
import com.bankaya.soap.pokemon.v1.HeldItem;
import java.util.List;
import java.util.Map;

public interface PokemonMapperService {
    List<AbilitySlot> mapAbilities(List<Map<String, Object>> abilities);
    List<HeldItem> mapHeldItems(List<Map<String, Object>> heldItems);

}
