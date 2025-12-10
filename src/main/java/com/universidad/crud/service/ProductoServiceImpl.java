package com.universidad.crud.service;

import com.universidad.crud.dto.ProductoRequest;
import com.universidad.crud.dto.ProductoResponse;
import com.universidad.crud.exception.ResourceNotFoundException;
import com.universidad.crud.model.Producto;
import com.universidad.crud.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de los servicios de negocio para la gestión de productos.
 * Contiene la lógica de negocio y coordina las operaciones entre el controlador y el repositorio.
 * 
 * @author Estudiante
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponse> obtenerTodos() {
        log.info("Obteniendo lista de todos los productos");
        List<Producto> productos = productoRepository.findAll();
        log.info("Se encontraron {} productos", productos.size());
        return productos.stream()
                .map(ProductoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public ProductoResponse obtenerPorId(Long id) {
        log.info("Buscando producto con ID: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Producto no encontrado con ID: {}", id);
                    return new ResourceNotFoundException("Producto", "id", id);
                });
        log.info("Producto encontrado: {}", producto.getNombre());
        return ProductoResponse.fromEntity(producto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductoResponse crear(ProductoRequest request) {
        log.info("Creando nuevo producto: {}", request.getNombre());
        
        Producto producto = Producto.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .build();
        
        Producto productoGuardado = productoRepository.save(producto);
        log.info("Producto creado exitosamente con ID: {}", productoGuardado.getId());
        
        return ProductoResponse.fromEntity(productoGuardado);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductoResponse actualizar(Long id, ProductoRequest request) {
        log.info("Actualizando producto con ID: {}", id);
        
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Producto no encontrado para actualizar con ID: {}", id);
                    return new ResourceNotFoundException("Producto", "id", id);
                });
        
        productoExistente.setNombre(request.getNombre());
        productoExistente.setDescripcion(request.getDescripcion());
        productoExistente.setPrecio(request.getPrecio());
        
        Producto productoActualizado = productoRepository.save(productoExistente);
        log.info("Producto actualizado exitosamente: {}", productoActualizado.getNombre());
        
        return ProductoResponse.fromEntity(productoActualizado);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminar(Long id) {
        log.info("Eliminando producto con ID: {}", id);
        
        if (!productoRepository.existsById(id)) {
            log.error("Producto no encontrado para eliminar con ID: {}", id);
            throw new ResourceNotFoundException("Producto", "id", id);
        }
        
        productoRepository.deleteById(id);
        log.info("Producto eliminado exitosamente con ID: {}", id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponse> buscarPorNombre(String nombre) {
        log.info("Buscando productos por nombre: {}", nombre);
        List<Producto> productos = productoRepository.findByNombreContainingIgnoreCase(nombre);
        log.info("Se encontraron {} productos con el nombre: {}", productos.size(), nombre);
        return productos.stream()
                .map(ProductoResponse::fromEntity)
                .collect(Collectors.toList());
    }
}

