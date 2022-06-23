# api-pokedex
 Pokedex Demo Project API Implementation

## Descripción
API Rest usando Java Spring Boot que expone la información como el Pokedex

- El API retorna información básica del pokemon tales como: Id, Descripción, Tipo, Foto, Habilidades
- Como Frontend (mecanismo de prueba) se entregaran llamadas por **curl** y JMeter

## Tabla de contenido
**[Repositorio](#repositorio)**<br>
**[Arquitectura](#arquitectura)**<br>
**[Caracteristicas](#caracteristicas)**<br>
**[Manual de usuario](#manual-de-usuario)**<br>
**[Learn More](#learn-more)**<br>

## Repositorio
url: [https://github.com/ergoproxy007/api-pokedex](https://github.com/ergoproxy007/api-pokedex)
######
git clone https://github.com/ergoproxy007/api-pokedex.git

## Arquitectura
Detalle del diseño y arquitectura definidos en la aplicación de api-pokedex:

#### Diseño
Se utiliza el enfoque de desarrollo **DDD (Domain-Driven Design)** para cumplir con necesidades complejas mediante una profunda conexión entre la implementación y los conceptos del modelo y núcleo del negocio.
######
**CQRS** son las siglas de Command Query Responsibility Segregation. Es un patrón que esencia busca la separación del un modelo usado para actualizar la información y otro modelo usado para leer la información.
######
Más información de estos conceptos [https://martinfowler.com/tags/domain%20driven%20design.html](https://martinfowler.com/tags/domain%20driven%20design.html)
######
Se busco con mucho empeño en este proyecto que la lógica de dominio y de negocio estuviera separada de implementaciones amarradas a uso de librerias o frameworks, de manera que se pudiera representar que en este aplicativo seria sencillo cambiar o evolucionar las librerias (infrastructure package) si necesidad de modificar la lógica de negocio (domain and query package)
######
Internamente en la aplicación se puede encontrar implementaciones patrones tales como Dao, Factory, Builder, Computación Asincrónica a través del uso de CompletionStage y Futuros (promesas).
Implementaciones usando principios SOLID como clases con responsabilidad unica, encapsulamiento, abstracciones con interface, herencia con clases abstractas,
uso de Generics, Jeraquia de Excepciones, AOP, Stream, programación funcional, entre otros.
######
Las pruebas unitarias desarrolladas también cumplen con el mismo estandar de calidad con que se construyo el código funcional, buscando probar todos los caminos y una alta cobertura.
######
Las pruebas estan totalmente desacopladas de dependencias, como conexiones a base de datos o consumo de servicios externos, por lo que puede ser ejecutada con total simpleza y tranquilidad.
######
La aplicación es una RESTful API basada en arquitectura de Micro Servicios, desarrollada con el framework Spring Boot y Spring Core.
######
Para ver mas detalle sobre los Json de entrada y ejemplo puede revisar la sección de **Manual de usuario y Learn More.**

#### Diagrama de Componentes
![Screenshot](https://github.com/ergoproxy007/api-pokedex/blob/main/assets/documentation/diagrama_componentes.png?raw=true)

#### Diagrama de Clases
![Screenshot](https://github.com/ergoproxy007/api-pokedex/blob/main/assets/documentation/diagrama_clases.png?raw=true)

## Caracteristicas
---
###### Características de la aplicación
### Librerias y/o Dependencias más importantes:

   Dependency Name | Usage                                            | Version   | License                |
   -------------   |--------------------------------------------------|-----------|------------------------
   Java            | Java Library                                     | 11        | OpenJDK Open Source    |
   Spring Boot     | Web and DI Java framework                        | 2.4.3     | Apache License 2.0     |
   Spring Data     | Starter for using Spring Data JPA with Hibernate | 2.5.3     | Apache License 2.0     |
   H2              | in-memory database, embedded                     | 1.4       | Eclipse Public License |
   Lombok          | annotation processor to automate code generation | 1.18.18  | MIT License           |
   JUnit           | Unit Testing Library                             | 5.jupiter | Eclipse Public License |
   Mockito         | Test                                             |           | MIT license            |
   JaCoCo          | Java code coverage tools                         | 0.8.7     | GPL v2                 |
######
#### URL Paths
El recurso o Json de respuesta se maneja con el siguiente formato:
```json
{
  "success": " valida si la respuesta fue exitosa o no, retornando true o false "
  "code": " códido de estado Http, ejemplo "200" o "404" ",
  "data": " aquí es donde se encapsula el Json de respuesta o contenido de la petición que luego sera leido y operado con un cliente/dispositivo"
}
```
En este momento cuenta con 2 servicios rest tipo Get con las siguientes firmas:
<pre><code>GET api/pokemons/{name}</code></pre>
###### - retorna la información del pokemon con los datos básicos, ejemplo de una respuesta del recurso:
```json
{"success":true,"code":"200","data":{"id":25,"name":"pikachu","weight":60,"type":"electric","image":"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png","abilities":[{"name":"static","description":"Whenever a move makes contact with this Pokémon, the move's user has a 30% chance of being paralyzed"},{"name":"lightning-rod","description":"All other Pokémon's single-target electric-type moves are redirected to this Pokémon if it is an eligible target"}]}}
```
<pre><code>GET api/pokemons/{name}</code></pre>

######
Para más detalle del consumo de los servicios, ir a la secciones de Manual de usuario y Learn More.
## Google Cloud Platform Alojamiento:
Se realiza creación, configuración y despliegue de la aplicación en GCP, con los siguiente datos:
- Account = torresruizdaniel23@gmail.com
- Project = api-pokedex-354204
- Nube: privada
- Zona: southamerica-east1
- Host: https://api-pokedex-354204.rj.r.appspot.com
###### GCP Diagram 
![Screenshot](https://github.com/ergoproxy007/api-pokedex/blob/main/assets/documentation/gcp_diagram.PNG?raw=true)

###### Comandos
- gcloud init
- gcloud app deploy
![Screenshot](https://github.com/ergoproxy007/api-pokedex/blob/main/assets/documentation/console_gcp.png?raw=true)

## Manual de usuario
###### Como ejecutar este programa
Puede ejecutar el programa en ambiente local una vez descargado el codigo fuente en [api-pokedex.git](https://github.com/ergoproxy007/api-pokedex.git):

En ambiente local, tienen dos opciones para ejecutar y/o probar el programa:
- 1.- Correr el proyecto directamente desde IntelliJ o Elipse usando Run Configurations
- 2.- O también puede correr el proyecto con el siguiente comando en el subproyecto /api-pokedex: **mvn spring-boot:run**

### 1. Usando CURL
1.1. Descarga curl [aquí](https://curl.se/windows/)
######
1.2. Descargar git bash [aquí](https://gitforwindows.org/)
######
1.3. Estos son 2 ejemplos para la ejecución de algunos de los servicios:
######
1.4.  Consumo en ambiente local:
######
1.4.1  curl api/pokemons/{name} =>
<pre><code>curl -X GET "http://localhost:8080/api/pokemons/pikachu" -H  "accept: application/json" -H "Content-Type: application/json"</code></pre>
######
1.4.2  curl api/pokemons/{name}?queryAbility={queryParam} =>
<pre><code>curl -X GET "http://localhost:8080/api/pokemons/pikachu?queryAbility=N" -H  "accept: application/json" -H "Content-Type: application/json"</code></pre>
######
1.5  Consumo en ambiente productivo:
1.5.1  curl https://api-pokedex-354204.rj.r.appspot.com/api/pokemons/{name} =>
<pre><code>curl -X GET "https://api-pokedex-354204.rj.r.appspot.com/api/pokemons/psyduck" -H  "accept: application/json" -H "Content-Type: application/json"</code></pre>
###### Respuesta ejemplo:
![Screenshot](https://github.com/ergoproxy007/api-pokedex/blob/main/assets/documentation/curl_gcp.png?raw=true)

### 2. A través de los archivos de Jmeter
La creación de los archivos en Jmeter tiene como objetivo simplificar el trabajo de ejecución de los servicios rest,
pensando a futuro poder agregar assets de validación o baterias de pruebas de rendimiento, entre otras cosas, que te brinda un programa como Jmeter.

2.1 Descarga Jmeter [aquí](https://jmeter.apache.org/download_jmeter.cgi)
######
2.2 Abrir el archivo en la ruta api-pokedex/assets/performance
######
- Archivo pokedexPDN.jmx para ambiente GCP
- Archivo pokedexLocal.jmx para ambiente Local
######
2.3 Ejecutar los servicios como disponga (en View Results Tree podrá ver los resultados):
###### imagen
![Screenshot](https://github.com/ergoproxy007/api-pokedex/blob/main/assets/documentation/jmeter_gcp.png?raw=true)

## Learn More
Commands:
mvn clean test
mvn jacoco:report
Path: path_project/target/site/jacoco/index.html