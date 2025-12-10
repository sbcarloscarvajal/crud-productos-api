package com.universidad.crud.service;

import com.universidad.crud.dto.ProductoRequest;
import com.universidad.crud.dto.ProductoResponse;

import java.util.List;

/**
 * Interfaz que define los servicios de negocio para la gestión de productos.
 * Define las operaciones CRUD y métodos adicionales de búsqueda.
 * 
 * @author Estudiante
 * @version 1.0.0
 */
public interface ProductoService {

    /**
     * Obtiene todos los productos registrados.
     * 
     * @return Lista de todos los productos
     */
    List<ProductoResponse> obtenerTodos();

    /**
     * Obtiene un producto por su identificador único.
     * 
     * @param id Identificador del producto
     * @return Datos del producto encontrado
     * @throws com.universidad.crud.exception.ResourceNotFoundException si el producto no existe
     */
    ProductoResponse obtenerPorId(Long id);

    /**
     * Crea un nuevo producto en el sistema.
     * 
     * @param request Datos del producto a crear
     * @return Datos del producto creado con su ID asignado
     */
    ProductoResponse crear(ProductoRequest request);

    /**
     * Actualiza un producto existente.
     * 
     * @param id Identificador del producto a actualizar
     * @param request Nuevos datos del producto
     * @return Datos del producto actualizado
     * @throws com.universidad.crud.exception.ResourceNotFoundException si el producto no existe
     */
    ProductoResponse actualizar(Long id, ProductoRequest request);

    /**
     * Elimina un producto del sistema.
     * 
     * @param id Identificador del producto a eliminar
     * @throws com.universidad.crud.exception.ResourceNotFoundException si el producto no existe
     */
    void eliminar(Long id);

    /**
     * Busca productos por nombre.
     * 
     * @param nombre Texto a buscar en el nombre del producto
     * @return Lista de productos que coinciden con el criterio
     */
    List<ProductoResponse> buscarPorNombre(String nombre);
}

