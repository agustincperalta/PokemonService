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
 Mockito     <img src="data:image/webp;base64,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" width="24" height="24"> |             

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
- `bugfix/<nombre-bugfix>`: Se utiliza para la corrección de errores.
