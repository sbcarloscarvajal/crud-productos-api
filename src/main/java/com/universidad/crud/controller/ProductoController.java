package com.universidad.crud.controller;

import com.universidad.crud.dto.ProductoRequest;
import com.universidad.crud.dto.ProductoResponse;
import com.universidad.crud.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de productos.
 * Expone los endpoints para realizar operaciones CRUD sobre productos.
 * 
 * @author Carlos Andres Carvajal Rivera
 * @version 1.0.0
 */
@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Productos", description = "API para la gestión de productos - Operaciones CRUD")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProductoController {

    private final ProductoService productoService;

    /**
     * Obtiene la lista de todos los productos.
     * 
     * @return Lista de productos
     */
    @Operation(
        summary = "Obtener todos los productos",
        description = "Retorna una lista con todos los productos registrados en el sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente",
            content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ProductoResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> obtenerTodos() {
        log.info("GET /api/v1/productos - Obteniendo todos los productos");
        List<ProductoResponse> productos = productoService.obtenerTodos();
        return ResponseEntity.ok(productos);
    }

    /**
     * Obtiene un producto por su ID.
     * 
     * @param id Identificador del producto
     * @return Datos del producto
     */
    @Operation(
        summary = "Obtener producto por ID",
        description = "Retorna un producto específico basado en su identificador único"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente",
            content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ProductoResponse.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado",
            content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerPorId(
            @Parameter(description = "ID del producto a buscar", required = true)
            @PathVariable Long id) {
        log.info("GET /api/v1/productos/{} - Obteniendo producto por ID", id);
        ProductoResponse producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(producto);
    }

    /**
     * Crea un nuevo producto.
     * 
     * @param request Datos del producto a crear
     * @return Producto creado con código 201
     */
    @Operation(
        summary = "Crear nuevo producto",
        description = "Crea un nuevo producto en el sistema con los datos proporcionados"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
            content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ProductoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
            content = @Content)
    })
    @PostMapping
    public ResponseEntity<ProductoResponse> crear(
            @Parameter(description = "Datos del producto a crear", required = true)
            @Valid @RequestBody ProductoRequest request) {
        log.info("POST /api/v1/productos - Creando nuevo producto: {}", request.getNombre());
        ProductoResponse productoCreado = productoService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
    }

    /**
     * Actualiza un producto existente.
     * 
     * @param id Identificador del producto a actualizar
     * @param request Nuevos datos del producto
     * @return Producto actualizado
     */
    @Operation(
        summary = "Actualizar producto",
        description = "Actualiza los datos de un producto existente identificado por su ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente",
            content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ProductoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado",
            content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizar(
            @Parameter(description = "ID del producto a actualizar", required = true)
            @PathVariable Long id,
            @Parameter(description = "Nuevos datos del producto", required = true)
            @Valid @RequestBody ProductoRequest request) {
        log.info("PUT /api/v1/productos/{} - Actualizando producto", id);
        ProductoResponse productoActualizado = productoService.actualizar(id, request);
        return ResponseEntity.ok(productoActualizado);
    }

    /**
     * Elimina un producto del sistema.
     * 
     * @param id Identificador del producto a eliminar
     * @return Respuesta sin contenido (204)
     */
    @Operation(
        summary = "Eliminar producto",
        description = "Elimina un producto del sistema basado en su identificador único"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado",
            content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del producto a eliminar", required = true)
            @PathVariable Long id) {
        log.info("DELETE /api/v1/productos/{} - Eliminando producto", id);
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Busca productos por nombre.
     * 
     * @param nombre Texto a buscar en el nombre
     * @return Lista de productos que coinciden
     */
    @Operation(
        summary = "Buscar productos por nombre",
        description = "Busca productos cuyo nombre contenga el texto especificado (búsqueda parcial)"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente",
            content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ProductoResponse.class)))
    })
    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoResponse>> buscarPorNombre(
            @Parameter(description = "Nombre o parte del nombre a buscar", required = true)
            @RequestParam String nombre) {
        log.info("GET /api/v1/productos/buscar?nombre={} - Buscando productos", nombre);
        List<ProductoResponse> productos = productoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(productos);
    }
}

