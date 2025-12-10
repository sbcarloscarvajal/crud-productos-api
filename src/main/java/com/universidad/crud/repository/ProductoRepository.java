package com.universidad.crud.repository;

import com.universidad.crud.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repositorio para la entidad Producto.
 * Proporciona operaciones CRUD mediante Spring Data JPA.
 * Extiende JpaRepository para heredar métodos estándar de persistencia.
 * 
 * @author Carlos Andres Carvajal Rivera
 * @version 1.0.0
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Busca productos cuyo nombre contenga el texto especificado.
     * La búsqueda es case-insensitive.
     * 
     * @param nombre Texto a buscar en el nombre del producto
     * @return Lista de productos que coinciden con el criterio
     */
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    /**
     * Busca productos cuyo precio esté dentro de un rango especificado.
     * 
     * @param precioMin Precio mínimo del rango
     * @param precioMax Precio máximo del rango
     * @return Lista de productos dentro del rango de precios
     */
    @Query("SELECT p FROM Producto p WHERE p.precio BETWEEN :precioMin AND :precioMax ORDER BY p.precio ASC")
    List<Producto> findByPrecioBetween(@Param("precioMin") BigDecimal precioMin, 
                                        @Param("precioMax") BigDecimal precioMax);

    /**
     * Verifica si existe un producto con el nombre especificado.
     * 
     * @param nombre Nombre del producto a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByNombreIgnoreCase(String nombre);
}

