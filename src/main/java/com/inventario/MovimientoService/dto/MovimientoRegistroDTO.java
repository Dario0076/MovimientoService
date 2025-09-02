package com.inventario.MovimientoService.dto;

import com.inventario.MovimientoService.entity.Movimiento;
import lombok.Data;

@Data
public class MovimientoRegistroDTO {
    private Movimiento movimiento;
    private StockDTO stock;
    private Long usuarioId;
}
