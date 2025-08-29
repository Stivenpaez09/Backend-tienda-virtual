#BACKEND - Sistema de inventario (API REST)

Este proyecto es el backend del sistema de inventario y ventas desarrollado con Java y Spring Boot.  
Está construido bajo una arquitectura multicapa que garantiza escalabilidad, seguridad y mantenimiento a largo plazo.  

Incluye seguridad con JWT (Spring Security), conexión a MySQL con JPA/Hibernate, y generación de reportes en PDF utilizando la librería OpenPDF.  

Tecnologías principales
- Java 17  
- Spring Boot (Spring Web, Spring Security, Spring Data JPA, Validation)  
- JWT (JSON Web Token) para autenticación y autorización  
- MySQL como base de datos relacional (`tienda_virtual`)  
- Hibernate como proveedor JPA  
- Maven para gestión de dependencias  
- OpenPDF para generación de reportes PDF  
- Lombok para simplificación de código

Arquitectura del proyecto
El proyecto sigue una arquitectura multicapa:

src/main/java/com/shop/tienda_virtual
│── config/          # Configuración general (ApplicationConfig, SecurityConfig)
│── controller/      # Controladores REST (exponen la API)
│── dto/             # Objetos de transferencia (protección de datos sensibles)
│── exception/       # Manejo centralizado de excepciones personalizadas
│── jwt/             # Lógica de seguridad con JWT (Authentication Filter, Utils)
│── model/           # Entidades y modelos de dominio
│── repository/      # Repositorios JPA para acceso a la base de datos
│── service/         # Lógica de negocio

Este diseño permite:  
- Separación clara de responsabilidades
- Código más limpio y mantenible
- Facilidad para pruebas unitarias e integración  

Seguridad
La aplicación implementa seguridad con **JWT y Spring Security**:  
- Autenticación mediante **tokens firmados**.  
- Autorización basada en roles y permisos.  
- Protección de endpoints sensibles.  
- Filtros de seguridad personalizados (`JWTAuthenticationFilter`).  

Funcionalidades principales
- Gestión de productos.  
- Registro de ventas con control de stock.  
- Administración de clientes.  
- Generación de reportes PDF con todas las ventas realizadas.  
- Manejo centralizado de excepciones y mensajes de error claros.  
- DTOs para proteger datos sensibles en las respuestas de la API.  

Configuración del proyecto
# Requisitos previos
- [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)  
- [Maven](https://maven.apache.org/)  
- [MySQL](https://dev.mysql.com/downloads/)  

# Configuración de la base de datos
En `src/main/resources/application.properties`:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/tienda_virtual
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

# Ejecución
Clonar el repositorio y ejecutar:

- Clonar el repositorio
git clone https://github.com/Stivenpaez09/Backend-tienda-virtual.git

- Entrar en el proyecto
cd backend-inventario

- Compilar y ejecutar
mvn spring-boot:run

El backend quedará disponible en:  
 `http://localhost:8080`

# Reportes en PDF
El sistema genera reportes de ventas en formato PDF utilizando la librería OpenPDF.  
el endpoint es: GET /api/ventas/generarpdf

Devuelve un archivo PDF con el historial de ventas.  

# Buenas prácticas implementadas
- Arquitectura multicapa con separación de responsabilidades.  
- Uso de DTOs para proteger datos sensibles.  
- Manejo centralizado de excepciones con mensajes claros.  
- Seguridad robusta con JWT + Spring Security.  
- Uso de Hibernate para persistencia con JPA.  
- Código limpio con Lombok y convenciones de nomenclatura.  

Autor
Proyecto desarrollado por Stiven Paez  
Contacto: johanstivenpaeztrujillo@gmail.com  
LinkedIn: www.linkedin.com/in/johan-stiven-paez-trujillo
