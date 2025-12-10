package com.universidad.crud.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad que representa un Producto en el sistema.
 * Mapeada a la tabla 'productos' en la base de datos mediante JPA/Hibernate.
 * 
 * @author Estudiante
 * @version 1.0.0
 */
@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    /**
     * Identificador único del producto.
     * Generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del producto.
     * Campo obligatorio con longitud máxima de 100 caracteres.
     */
    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * Descripción breve del producto.
     * Campo obligatorio con longitud máxima de 500 caracteres.
     */
    @NotBlank(message = "La descripción del producto es obligatoria")
    @Size(min = 10, max = 500, message = "La descripción debe tener entre 10 y 500 caracteres")
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    /**
     * Precio del producto.
     * Debe ser un valor positivo mayor a cero.
     */
    @NotNull(message = "El precio del producto es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener máximo 10 dígitos enteros y 2 decimales")
    @Column(name = "precio", nullable = false, precision = 12, scale = 2)
    private BigDecimal precio;

    /**
     * Fecha y hora de creación del registro.
     * Se establece automáticamente al crear el producto.
     */
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    /**
     * Fecha y hora de la última actualización del registro.
     * Se actualiza automáticamente en cada modificación.
     */
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    /**
     * Método ejecutado antes de persistir un nuevo producto.
     * Establece la fecha de creación automáticamente.
     */
    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    /**
     * Método ejecutado antes de actualizar un producto existente.
     * Actualiza la fecha de modificación automáticamente.
     */
    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}

