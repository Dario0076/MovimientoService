package com.inventario.MovimientoService.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    private Long id;
    private Long productoId;
    private Integer cantidad;
    private Integer umbralMinimo;
}

