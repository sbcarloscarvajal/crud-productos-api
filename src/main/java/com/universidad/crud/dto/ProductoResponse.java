package com.universidad.crud.dto;

import com.universidad.crud.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) para enviar datos de producto en las respuestas.
 * Encapsula la información del producto que se devuelve al cliente.
 * 
 * @author Carlos Andres Carvajal Rivera
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoResponse {

    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    /**
     * Método estático de fábrica para convertir una entidad Producto a ProductoResponse.
     * 
     * @param producto Entidad producto a convertir
     * @return ProductoResponse con los datos del producto
     */
    public static ProductoResponse fromEntity(Producto producto) {
        return ProductoResponse.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .fechaCreacion(producto.getFechaCreacion())
                .fechaActualizacion(producto.getFechaActualizacion())
                .build();
    }
}

