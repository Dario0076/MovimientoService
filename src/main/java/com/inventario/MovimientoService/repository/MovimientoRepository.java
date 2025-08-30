package com.inventario.MovimientoService.repository;

import com.inventario.MovimientoService.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
}

