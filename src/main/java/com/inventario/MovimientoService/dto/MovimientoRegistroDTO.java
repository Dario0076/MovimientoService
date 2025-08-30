package com.inventario.MovimientoService.dto;

import com.inventario.MovimientoService.entity.Movimiento;
import com.inventario.MovimientoService.dto.StockDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoRegistroDTO {
    private Movimiento movimiento;
    private StockDTO stock;
    private Long usuarioId;
}
