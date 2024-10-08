Feature: Obtener información de un Pokémon
  Como consumidor del servicio
  Quiero obtener diferentes detalles de un Pokémon a través de solicitudes SOAP
  Para ver sus habilidades, experiencia, objetos, ID, áreas de encuentro y nombre

  Scenario: Obtener habilidades de un Pokémon
    Given existe un Pokémon llamado "pikachu"
    When envío una solicitud SOAP para obtener las habilidades de "pikachu"
    Then la respuesta debe contener al menos una habilidad

  Scenario: Obtener experiencia base de un Pokémon
    Given existe un Pokémon llamado "bulbasaur"
    When envío una solicitud SOAP para obtener la experiencia base de "bulbasaur"
    Then la respuesta debe contener un valor de experiencia base

  Scenario: Obtener objetos en posesión de un Pokémon
    Given existe un Pokémon llamado "psyduck"
    When envío una solicitud SOAP para obtener los objetos que posee "charizard"
    Then la respuesta no debe ser nula

  Scenario: Obtener ID de un Pokémon
    Given existe un Pokémon llamado "squirtle"
    When envío una solicitud SOAP para obtener el ID de "squirtle"
    Then la respuesta debe contener un ID válido

  Scenario: Obtener áreas de encuentro de un Pokémon
    Given existe un Pokémon llamado "jigglypuff"
    When envío una solicitud SOAP para obtener las áreas de encuentro de "jigglypuff"
    Then la respuesta debe contener una uri valida

  Scenario: Obtener nombre de un Pokémon por su nombre
    Given existe un Pokémon llamado "alakazam"
    When envío una solicitud SOAP para obtener el nombre del Pokémon con "alakazam"
    Then la respuesta debe contener un nombre de Pokémon válido
