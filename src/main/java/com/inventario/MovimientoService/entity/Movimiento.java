package com.inventario.MovimientoService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private Integer cantidad;
    private LocalDateTime fecha;
    private Long productoId;
    private String observacion;
    private String usuario; // Nombre del usuario que realizó el movimiento
}

