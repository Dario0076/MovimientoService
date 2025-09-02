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

    @Column(name = "tipo", nullable = false, length = 20)
    private String tipo;
    
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
    
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;
    
    @Column(name = "producto_id", nullable = false)
    private Long productoId;
    
    @Column(name = "descripcion", length = 500)
    private String descripcion;
    
    @Column(name = "usuario", length = 100)
    private String usuario;
    
    @Column(name = "usuario_email", length = 100)
    private String usuarioEmail;
}

