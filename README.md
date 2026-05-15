# Trabajo final - Arquitectura de software

## <u>Contexto</u>
El proyecto actual consiste en desarrollar un sistema de gestión bancaria ([ver repositorio](https://github.com/jgomezz/arq_m2_s6_tarea)) que permita realizar las siguientes operaciones:
1. Crear cuentas bancarias.
2. Transferir dinero entre cuentas.

## <u>Instalación  </u>
1. Descargar el repositorio.
2. Abrir el proyecto con tu IDE favorito.
3. Crear el archivo `application.properties`.

```properties
spring.application.name=hexagonal

# ========================================
# CONFIGURACION POSTGRESQL
# ========================================

spring.datasource.url=jdbc:postgresql://localhost:5433/bank-proyect
spring.datasource.username=postgres
spring.datasource.password=admin
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
```

4. Entrar a:

```text
./src/main/java/pe.edu.tecsup.hexagonal.app/HexagonalApplication.java
```

5. Ejecutar la clase `HexagonalApplication`.

### REQUISITOS FUNCIONALES
Para utilizar la aplicación, es necesario descargar e importar la colección de Postman ubicada en:

`docs/postman/App-Bank.postman_collection.json`

## <u>ADRS </u>
En los siguientes enlaces tenemos los documentos respectivos.
- [ADR-001: Arquitectura Hexagonal](./docs/adrs/ADR-001.md)
- [ADR-002: Sistema de Persistencia](./docs/adrs/ADR-002.md)

## <u>Diagrama de la arquitectura </u>
[Diagrama](./docs/img/diagrama.png)

## <u>Patrones Implementados</u>

### Adapter Input
Los archivos ubicados en:

`src/main/java/pe/edu/tecsup/hexagonal/app/infrastructure/adapter/input/rest/controller`

actúan como adaptadores de entrada, encargándose de transformar las solicitudes HTTP REST en llamadas hacia los casos de uso y reglas de negocio de la aplicación.

### Adapter Output
Los archivos ubicados en:

`src/main/java/pe/edu/tecsup/hexagonal/app/infrastructure/adapter/output/persistence/repository`

actúan como adaptadores de salida, encargándose de implementar la comunicación entre el dominio de la aplicación y la base de datos PostgreSQL mediante mecanismos de persistencia.

### Singleton

El archivo ubicado en:

`src/main/java/pe/edu/tecsup/hexagonal/app/infrastructure/config`

implementan el patrón Singleton mediante la gestión de dependencias de Spring Boot.  
Las clases configuradas con `@Configuration` y `@Bean` permiten que Spring cree una única instancia compartida de los casos de uso y componentes de la aplicación durante todo el ciclo de vida del sistema.