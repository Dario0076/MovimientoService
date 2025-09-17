package com.inventario.MovimientoService.repository;

import com.inventario.MovimientoService.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
	// Buscar movimientos por producto
	java.util.List<Movimiento> findByProductoId(Long productoId);

	// Buscar movimientos por rango de fechas
	java.util.List<Movimiento> findByFechaBetween(java.time.LocalDateTime fechaInicio, java.time.LocalDateTime fechaFin);

	// Buscar movimientos por producto y rango de fechas
	java.util.List<Movimiento> findByProductoIdAndFechaBetween(Long productoId, java.time.LocalDateTime fechaInicio, java.time.LocalDateTime fechaFin);
}

