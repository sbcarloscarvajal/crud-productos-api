# 🛒 API REST de Productos

API RESTful para la gestión de productos desarrollada con **Spring Boot 3.2** y **JPA/Hibernate** como ORM.

> **Actividad Sumativa - Unidad 2**  
> Módulo: Arquitectura de Aplicaciones Web

---

## 📋 Descripción

Esta aplicación implementa un backend completo con servicios API REST que permiten realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre una entidad **Producto**. El proyecto sigue las mejores prácticas de desarrollo y una arquitectura en capas bien organizada.

---

## 🏗️ Arquitectura del Proyecto

```
src/main/java/com/universidad/crud/
├── config/                 # Configuraciones (Swagger/OpenAPI)
├── controller/             # Controladores REST (endpoints)
├── dto/                    # Objetos de transferencia de datos
├── exception/              # Manejo global de excepciones
├── model/                  # Entidades JPA
├── repository/             # Repositorios Spring Data JPA
└── service/                # Lógica de negocio
```

---

## 🚀 Tecnologías Utilizadas

| Tecnología | Versión | Descripción |
|------------|---------|-------------|
| Java | 17 | Lenguaje de programación |
| Spring Boot | 3.2.0 | Framework backend |
| Spring Data JPA | 3.2.0 | ORM con Hibernate |
| H2 Database | Runtime | Base de datos en memoria (desarrollo) |
| Azure SQL | - | Base de datos en la nube (producción) |
| SpringDoc OpenAPI | 2.3.0 | Documentación Swagger |
| Lombok | - | Reducción de código boilerplate |
| Maven | 3.9+ | Gestión de dependencias |

---

## 📦 Entidad Producto

| Atributo | Tipo | Descripción |
|----------|------|-------------|
| `id` | Long | Identificador único (autogenerado) |
| `nombre` | String | Nombre del producto (2-100 caracteres) |
| `descripcion` | String | Descripción del producto (10-500 caracteres) |
| `precio` | BigDecimal | Precio del producto (mayor a 0) |
| `fechaCreacion` | LocalDateTime | Fecha de creación (automática) |
| `fechaActualizacion` | LocalDateTime | Fecha de última actualización (automática) |

---

## 🔗 Endpoints de la API

Base URL: `http://localhost:8080/api/v1/productos`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/v1/productos` | Obtener todos los productos |
| `GET` | `/api/v1/productos/{id}` | Obtener producto por ID |
| `GET` | `/api/v1/productos/buscar?nombre=texto` | Buscar productos por nombre |
| `POST` | `/api/v1/productos` | Crear nuevo producto |
| `PUT` | `/api/v1/productos/{id}` | Actualizar producto existente |
| `DELETE` | `/api/v1/productos/{id}` | Eliminar producto |

---

## 📝 Ejemplos de Uso

### Crear un producto (POST)
```json
POST /api/v1/productos
Content-Type: application/json

{
    "nombre": "Laptop Gaming ASUS",
    "descripcion": "Laptop gaming ASUS ROG con procesador AMD Ryzen 7, 16GB RAM, RTX 3060",
    "precio": 4500000.00
}
```

### Respuesta exitosa (201 Created)
```json
{
    "id": 1,
    "nombre": "Laptop Gaming ASUS",
    "descripcion": "Laptop gaming ASUS ROG con procesador AMD Ryzen 7, 16GB RAM, RTX 3060",
    "precio": 4500000.00,
    "fechaCreacion": "2024-12-09T10:30:00",
    "fechaActualizacion": "2024-12-09T10:30:00"
}
```

### Actualizar un producto (PUT)
```json
PUT /api/v1/productos/1
Content-Type: application/json

{
    "nombre": "Laptop Gaming ASUS ROG",
    "descripcion": "Laptop gaming ASUS ROG Strix con procesador AMD Ryzen 9, 32GB RAM, RTX 4070",
    "precio": 6500000.00
}
```

---

## ⚙️ Instalación y Ejecución

### Prerrequisitos
- Java 17 o superior
- Maven 3.9 o superior
- Git

### Pasos de instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/tu-usuario/crud-productos-api.git
cd crud-productos-api
```

2. **Compilar el proyecto**
```bash
mvn clean install
```

3. **Ejecutar la aplicación**
```bash
mvn spring-boot:run
```

4. **Acceder a la aplicación**
- API: http://localhost:8080/api/v1/productos
- Swagger UI: http://localhost:8080/swagger-ui.html
- Consola H2: http://localhost:8080/h2-console

---

## 🔧 Configuración de Azure SQL Database

Para usar Azure SQL Database en producción:

1. Crear una cuenta en [Azure Portal](https://portal.azure.com)
2. Crear un recurso "Azure SQL Database"
3. Configurar las credenciales en `application-azure.properties`:

```properties
spring.datasource.url=jdbc:sqlserver://tu-servidor.database.windows.net:1433;database=productosdb;encrypt=true;
spring.datasource.username=tu-usuario
spring.datasource.password=tu-contraseña
```

4. Cambiar el perfil activo en `application.properties`:
```properties
spring.profiles.active=azure
```

---

## 📚 Documentación de la API

La documentación interactiva de la API está disponible a través de **Swagger UI**:

🔗 **URL Swagger:** http://localhost:8080/swagger-ui.html

Desde Swagger UI puedes:
- Ver todos los endpoints disponibles
- Probar las operaciones CRUD directamente
- Ver los esquemas de datos (DTOs)
- Descargar la especificación OpenAPI

---

## 🧪 Pruebas con Postman/Insomnia

Puedes importar la colección de pruebas o realizar las siguientes peticiones:

### GET - Obtener todos los productos
```
GET http://localhost:8080/api/v1/productos
```

### GET - Obtener producto por ID
```
GET http://localhost:8080/api/v1/productos/1
```

### POST - Crear producto
```
POST http://localhost:8080/api/v1/productos
Content-Type: application/json

{
    "nombre": "Producto de prueba",
    "descripcion": "Descripción del producto de prueba para la API",
    "precio": 99.99
}
```

### PUT - Actualizar producto
```
PUT http://localhost:8080/api/v1/productos/1
Content-Type: application/json

{
    "nombre": "Producto actualizado",
    "descripcion": "Descripción actualizada del producto de prueba",
    "precio": 149.99
}
```

### DELETE - Eliminar producto
```
DELETE http://localhost:8080/api/v1/productos/1
```

---

## ⚠️ Manejo de Errores

La API implementa un manejo global de errores que retorna respuestas consistentes:

### Error 404 - Recurso no encontrado
```json
{
    "status": 404,
    "mensaje": "Producto no encontrado con id: '999'",
    "timestamp": "2024-12-09 10:30:00",
    "path": "/api/v1/productos/999"
}
```

### Error 400 - Validación fallida
```json
{
    "status": 400,
    "mensaje": "Error de validación en los datos de entrada",
    "timestamp": "2024-12-09 10:30:00",
    "path": "/api/v1/productos",
    "errores": [
        "El nombre del producto es obligatorio",
        "El precio debe ser mayor a 0"
    ]
}
```

---

## 👨‍💻 Autor

**Carlos Andres Carvajal Rivera**  
Modulo: Arquitectura de Aplicaciones Web  
Unidad 2 - Actividad Sumativa

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

