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


## <u>ADRS </u>
En los siguientes enlaces tenemos los documentos respectivos.
- [ADR-001: Arquitectura Hexagonal](./docs/adrs/ADR-001.md)
- [ADR-002: Sistema de Persistencia](./docs/adrs/ADR-002.md)

## <u>Diagrama de la arquitectura </u>
[Diagrama](./docs/img/diagrama.png)