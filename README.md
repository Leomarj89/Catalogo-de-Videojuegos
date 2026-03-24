# Catálogo de Videojuegos

Aplicación web desarrollada con Spring Boot para la gestión y seguimiento de videojuegos completados. Este proyecto permite registrar, visualizar y administrar un catálogo personal de videojuegos, integrando persistencia de datos y una interfaz web dinámica.

## Descripción

Este sistema tiene como objetivo centralizar el registro de videojuegos completados, permitiendo almacenar información relevante y consultarla de manera estructurada a través de una aplicación web.

El proyecto está desarrollado bajo el enfoque MVC (Modelo-Vista-Controlador) y utiliza tecnologías modernas del ecosistema Java.

## Tecnologías utilizadas

### Backend

* Java 17
* Spring Boot 3
* Spring Data JPA
* Spring Validation

### Frontend

* Thymeleaf
* HTML5
* CSS3

### Base de datos

* Microsoft SQL Server
* JDBC Driver (mssql-jdbc)

### Herramientas y dependencias

* Maven
* Lombok
* Spring Boot DevTools

### Testing

* Spring Boot Starter Test

## Arquitectura

El proyecto sigue el patrón MVC:

* Model: Entidades JPA que representan los datos
* Repository: Interfaces para acceso a base de datos
* Service: Lógica de negocio
* Controller: Manejo de solicitudes HTTP
* View: Plantillas Thymeleaf

## Funcionalidades

* Registro de videojuegos completados
* Visualización del catálogo
* Validación de datos en formularios
* Persistencia en base de datos SQL Server
* Interfaz web dinámica con Thymeleaf

## Requisitos

* Java 17 o superior
* Maven
* SQL Server
* IDE compatible (IntelliJ IDEA, Eclipse, VS Code)

## Instalación y ejecución

1. Clonar el repositorio:

```bash
git clone https://github.com/Leomarj89/Catalogo-de-Videojuegos.git
```

2. Acceder al proyecto:

```bash
cd Catalogo-de-Videojuegos
```

3. Configurar la base de datos en el archivo `application.properties`

4. Ejecutar la aplicación:

```bash
mvn spring-boot:run
```

5. Acceder desde el navegador:

```
http://localhost:8080
```

## Estructura del proyecto

```
src/
 ├── main/
 │   ├── java/com/leomar/videojuegos/
 │   │   ├── controller/
 │   │   ├── service/
 │   │   ├── repository/
 │   │   └── model/
 │   └── resources/
 │       ├── templates/
 │       └── application.properties
```

## Estado del proyecto

Proyecto en desarrollo activo. Se continúan agregando mejoras y nuevas funcionalidades.

## Posibles mejoras futuras


* Integración con una API externa de videojuegos
* Carga automática de información de portadas y metadatos
* Autenticación de usuarios
* Paginación en listados
* Ordenamiento avanzado
* Mejoras visuales del dashboard
* Exportación de datos
* Pruebas unitarias e integración más completas

## Autor

Leomar Contreras

## Licencia

Este proyecto es de uso personal y educativo.