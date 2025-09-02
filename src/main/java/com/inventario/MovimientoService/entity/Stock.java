package com.inventario.MovimientoService.entity;

import lombok.Data;

@Data
public class Stock {
    private Long id;
    private Long productoId;
    private Integer cantidad;
    private Integer umbralMinimo;
}

