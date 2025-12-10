package com.universidad.crud.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase que representa la estructura de respuesta de error.
 * Proporciona información detallada sobre los errores ocurridos.
 * 
 * @author Estudiante
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    /**
     * Código de estado HTTP del error.
     */
    private int status;

    /**
     * Mensaje descriptivo del error.
     */
    private String mensaje;

    /**
     * Timestamp del momento en que ocurrió el error.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    /**
     * Ruta del endpoint donde ocurrió el error.
     */
    private String path;

    /**
     * Lista de errores de validación (si aplica).
     */
    private List<String> errores;
}

