package org.bankaya.pokemonservice.cucumberIT.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.bankaya.pokemonservice.application.impl.PokemonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
public class PokemonStepDefinitions {

  @Autowired
  private PokemonServiceImpl pokemonService;

  private String pokemonName;
  private GetPokemonAbilitiesResponse abilitiesResponse;
  private GetPokemonBaseExperienceResponse experienceResponse;
  private GetPokemonHeldItemsResponse heldItemsResponse;
  private GetPokemonIdResponse idResponse;
  private GetPokemonLocationAreaEncountersResponse locationAreaResponse;
  private GetPokemonNameResponse nameResponse;

  @Before
  public void setUpCucumber() {
    MockHttpServletRequest mockRequest = new MockHttpServletRequest();
    mockRequest.addHeader("X-Forwarded-For", "127.0.0.1");
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockRequest));
  }

  @Given("existe un Pokémon llamado {string}")
  public void existe_un_Pokemon_llamado(String name) {
    this.pokemonName = name;
    log.info("Given: Pokémon llamado {}", name);
  }

  @When("envío una solicitud SOAP para obtener las habilidades de {string}")
  public void envio_una_solicitud_soap_para_obtener_las_habilidades_de(String name) {
    GetPokemonAbilitiesRequest request = new GetPokemonAbilitiesRequest();
    request.setPokemonName(name);
    abilitiesResponse = pokemonService.getPokemonAbilitiesByName(name);
    log.info("When: Solicitud para obtener habilidades de {}", name);
  }


  @When("envío una solicitud SOAP para obtener la experiencia base de {string}")
  public void envio_una_solicitud_soap_para_obtener_la_experiencia_base_de(String name) {
    GetPokemonBaseExperienceRequest request = new GetPokemonBaseExperienceRequest();
    request.setPokemonName(name);
    experienceResponse = pokemonService.getPokemonBaseExperienceByName(name);
    log.info("When: Solicitud para obtener la experiencia base de {}", name);
  }

  @When("envío una solicitud SOAP para obtener los objetos que posee {string}")
  public void envio_una_solicitud_soap_para_obtener_los_objetos_que_posee(String name) {
    GetPokemonHeldItemsRequest request = new GetPokemonHeldItemsRequest();
    request.setPokemonName(name);
    heldItemsResponse = pokemonService.getPokemonHeldItemsByName(name);
    log.info("When: Solicitud para obtener los objetos de {}", name);
  }


  @When("envío una solicitud SOAP para obtener el ID de {string}")
  public void envio_una_solicitud_soap_para_obtener_el_id_de(String name) {
    GetPokemonIdRequest request = new GetPokemonIdRequest();
    request.setPokemonName(name);
    idResponse = pokemonService.getPokemonIdByName(name);
    log.info("When: Solicitud para obtener el ID de {}", name);
  }


  @When("envío una solicitud SOAP para obtener las áreas de encuentro de {string}")
  public void envio_una_solicitud_soap_para_obtener_las_areas_de_encuentro_de(String name) {
    GetPokemonLocationAreaEncountersRequest request = new GetPokemonLocationAreaEncountersRequest();
    request.setPokemonName(name);
    locationAreaResponse = pokemonService.getPokemonLocationAreaEncounterByName(name);
    log.info("When: Solicitud para obtener las áreas de encuentro de {}", name);
  }


  @When("envío una solicitud SOAP para obtener el nombre del Pokémon con {string}")
  public void envio_una_solicitud_soap_para_obtener_el_nombre_del_pokemon_con_id(String name) {
    GetPokemonNameRequest request = new GetPokemonNameRequest();
    request.setPokemonName(name);
    nameResponse = pokemonService.getPokemonNameByName(
        pokemonName); // Asumimos que el nombre se basa en ID
    log.info("When: Solicitud para obtener el nombre del Pokémon con {}", name);
  }

  @Then("la respuesta debe contener al menos una habilidad")
  public void la_respuesta_debe_contener_al_menos_una_habilidad() {
    assertNotNull(abilitiesResponse);
    assertFalse(abilitiesResponse.getAbilities().isEmpty(),
        "La lista de habilidades no debe estar vacía");
    log.info("Then: La respuesta contiene al menos una habilidad");
  }

  @Then("la respuesta debe contener un valor de experiencia base")
  public void la_respuesta_debe_contener_un_valor_de_experiencia_base() {
    assertNotNull(experienceResponse);
    assertTrue(experienceResponse.getBaseExperience() > 0,
        "La experiencia base debe ser mayor a 0");
    log.info("Then: La respuesta contiene un valor de experiencia base");
  }

  @Then("la respuesta no debe ser nula")
  public void la_respuesta_debe_contener_al_menos_un_objeto_en_posesion() {
    assertNotNull(heldItemsResponse);
    assertNotNull(heldItemsResponse.getHeldItems(),
        "La lista de objetos en posesión no debe ser nula");
    log.info("Then: La respuesta contiene al menos un objeto en posesión");
  }

  @Then("la respuesta debe contener un ID válido")
  public void la_respuesta_debe_contener_un_id_valido() {
    assertNotNull(idResponse);
    assertTrue(idResponse.getId() > 0, "El ID debe ser mayor a 0");
    log.info("Then: La respuesta contiene un ID válido");
  }

  @Then("la respuesta debe contener una uri valida")
  public void la_respuesta_debe_contener_al_menos_un_area_de_encuentro() {
    assertNotNull(locationAreaResponse);
    assertFalse(locationAreaResponse.getLocationAreaEncounters().isEmpty(),
        "La lista de áreas de encuentro no debe estar vacía");

    boolean hasValidUrl = locationAreaResponse.getLocationAreaEncounters()
        .matches("^(http|https)://.*$");
    assertTrue(hasValidUrl, "Debe haber al menos un área de encuentro con una URL válida");

    log.info("Then: La respuesta contiene al menos un área de encuentro con una URL válida");
  }

  @Then("la respuesta debe contener un nombre de Pokémon válido")
  public void la_respuesta_debe_contener_un_nombre_de_pokemon_valido() {
    assertNotNull(nameResponse);
    assertNotNull(nameResponse.getName(), "El nombre del Pokémon no debe ser nulo");
    assertFalse(nameResponse.getName().isEmpty(),
        "El nombre del Pokémon no debe estar vacío");
    log.info("Then: La respuesta contiene un nombre de Pokémon válido");
  }
}
