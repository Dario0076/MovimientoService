package com.inventario.MovimientoService.dto;

import lombok.Data;

@Data
public class StockDTO {
    private Long id;
    private Long productoId;
    private Integer cantidadActual;
    private Integer umbralMinimo;
}

