# PokemonService![Pokemon Service](https://img.icons8.com/color/48/000000/pokeball.png)
[![CucumberReports: PokemonService](https://messages.cucumber.io/api/report-collections/89dd6010-534c-45b1-b59e-9fbb638fd687/badge)](https://reports.cucumber.io/report-collections/89dd6010-534c-45b1-b59e-9fbb638fd687)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=agustincperalta_PokemonService&metric=alert_status)](https://sonarcloud.io/summary/overall?id=agustincperalta_PokemonService)

=======================


## Descripción

 Servicio SOAP en Java utilizando Spring Boot para consumir la API REST de [PokeAPI](https://pokeapi.co/api/v2/pokemon/). Obtiene información sobre un Pokémon en tiempo real: **habilidades, experiencia base, objetos retenidos, ID, nombre y áreas de encuentro**.

El servicio guarda los detalles de cada solicitud en una base de datos e incluye análisis de calidad de código con **SonarQube**, **pruebas unitarias**, y **pruebas con Cucumber.**


## Stack

 Java 17     <img src="https://img.icons8.com/color/48/000000/java-coffee-cup-logo.png" width="24" height="24"> | Spring Boot  <img src="https://img.icons8.com/color/48/000000/spring-logo.png" width="24" height="24"> |
 SOAP        <img src="https://img.icons8.com/material-outlined/24/xml.png" width="24" height="24"> | REST (WebClient)  <img src="https://img.icons8.com/color/48/000000/api-settings.png" width="24" height="24"> |
 H2    <img src="https://dbdb.io/media/logos/h2-logo.svg" width="24" height="24"> | Docker  <img src="https://img.icons8.com/color/48/000000/docker.png" width="24" height="24"> |
 SonarQube   <img src="https://cdn.worldvectorlogo.com/logos/sonarcloud-1.svg" width="24" height="24"> | Github Actions  <img src="https://avatars.githubusercontent.com/u/44036562?s=48&v=4" width="24" height="24"> |
 Cucumber    <img src="https://avatars.githubusercontent.com/u/320565?s=280&v=4" width="24" height="24"> | JUnit 5  <img src="https://w7.pngwing.com/pngs/928/911/png-transparent-junit-software-testing-spring-framework-unit-testing-java-others-miscellaneous-text-trademark-thumbnail.png" width="24" height="24"> |
 Mockito     <img src="https://cdn.icon-icons.com/icons2/219/PNG/256/Mojito_25455.png" width="24" height="24"> |             

## Requisitos previos

- JDK 17 o superior
- Maven
- Docker (opcional si deseas dockerizar el proyecto)

## Instalación

 Clona el repositorio:

   ```bash
   git clone https://github.com/agustincperalta/PokemonService
   cd PokemonService
   ```
## Construye el proyecto

```bash
mvn clean install
```

## Ejecuta el servicio

```bash
mvn spring-boot:run
```

O si prefieres usar Docker:
Crea una imagen de PokemonService:
```
mvn clean install -Dimage
```
Y ejecuta la aplicacion
```bash
docker run -d -p 8080:8080 pokemon-service
```
## Operaciones SOAP Disponibles

El servicio ofrece los siguientes métodos SOAP para interactuar con la información de los Pokémon. Para obtener más detalles sobre cada operación y su ejecución, consulta la documentación en la wiki.

[Operaciones SOAP](https://github.com/agustincperalta/PokemonService/wiki/Operaciones-SOAP-Disponibles).

## Base de datos

El servicio guarda las siguientes variables en la base de datos cada vez que se realiza una solicitud:

- IP de origen
- Fecha de la solicitud
- Método ejecutado

### Acceso a la consola de H2

La consola de H2 está expuesta para que se puedan verificar los datos almacenados en la base de datos. Para acceder a la consola y ejecutar consultas, usa la siguiente URL y credenciales:

- **URL de la consola**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Usuario**: `sa`
- **Contraseña**: `password`

### Consulta de los datos

La información se almacena en la tabla `REQUEST_LOG`. Se puede ejecutar la siguiente consulta SQL para ver los registros:

```sql
SELECT * FROM REQUEST_LOG;
```
![img.png](h2console.png)
## Análisis con SonarQube

El análisis de calidad de código está integrado mediante **SonarQube** utilizando **SonarCloud**, ejecutado automáticamente a través de **GitHub Actions**.

Puedes consultar los resultados del **análisis** y el **coverage** tanto en GitHub como directamente en SonarCloud haciendo clic en el siguiente badge:


[![Quality gate](https://sonarcloud.io/api/project_badges/quality_gate?project=agustincperalta_PokemonService)](https://sonarcloud.io/summary/overall?id=agustincperalta_PokemonService)

## Pruebas

### Unitarias (JUnit)

Ejecuta las pruebas unitarias usando el siguiente comando:

```bash
mvn test
```

### Cucumber

El proyecto incluye pruebas de integración con Cucumber. Para ejecutarlas:

```bash
mvn verify
```
Consulta los informes de Cucumber en [este enlace](https://reports.cucumber.io/report-collections/89dd6010-534c-45b1-b59e-9fbb638fd687).

## Estrategia de branching

- `main`: Contiene el código listo para producción.
- `develop`: Rama donde se realiza la integración de las nuevas características.
- `feature/<nombre-feature>`: Se utiliza para el desarrollo de nuevas características.
- `hotfix/<nombre-bugfix>`: Se utiliza para la corrección de errores.
