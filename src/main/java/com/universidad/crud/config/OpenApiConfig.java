package com.universidad.crud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuración de OpenAPI/Swagger para la documentación de la API.
 * Personaliza la información mostrada en la interfaz de Swagger UI.
 * 
 * @author Carlos Andres Carvajal Rivera
 * @version 1.0.0
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configura la información personalizada de OpenAPI.
     * 
     * @return Objeto OpenAPI configurado
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST de Productos")
                        .version("1.0.0")
                        .description("API RESTful para la gestión de productos. " +
                                "Implementa operaciones CRUD (Crear, Leer, Actualizar, Eliminar) " +
                                "utilizando Spring Boot y JPA/Hibernate como ORM. " +
                                "Desarrollado como parte de la Actividad Sumativa - Unidad 2 " +
                                "del módulo Arquitectura de Aplicaciones Web.")
                        .contact(new Contact()
                                .name("Estudiante")
                                .email("estudiante@universidad.edu.co")
                                .url("https://github.com/estudiante"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de Desarrollo Local"),
                        new Server()
                                .url("https://api-productos.azurewebsites.net")
                                .description("Servidor de Producción en Azure")
                ));
    }
}

