package com.inventario.MovimientoService.controller;

import com.inventario.MovimientoService.dto.MovimientoRegistroDTO;
import com.inventario.MovimientoService.entity.Movimiento;
import com.inventario.MovimientoService.service.MovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos")
@CrossOrigin("*")
public class MovimientoController {
    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public List<Movimiento> getAllMovimientos() {
        return movimientoService.getAllMovimientos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable Long id) {
        Optional<Movimiento> movimiento = movimientoService.getMovimientoById(id);
        return movimiento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> registrarMovimiento(@RequestBody MovimientoRegistroDTO registroDTO) {
        if (registroDTO == null || registroDTO.getMovimiento() == null || registroDTO.getStock() == null || registroDTO.getUsuarioId() == null) {
            return ResponseEntity.badRequest().body("Datos incompletos para registrar movimiento, actualizar stock y auditar usuario");
        }
        try {
            Movimiento mov = movimientoService.saveMovimiento(
                registroDTO.getMovimiento(),
                registroDTO.getStock(),
                registroDTO.getUsuarioId()
            );
            return ResponseEntity.ok(mov);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Obtener movimientos por producto
    @GetMapping("/producto/{productoId}")
    public List<Movimiento> getMovimientosByProducto(@PathVariable Long productoId) {
        return movimientoService.getMovimientosByProducto(productoId);
    }

    // Obtener movimientos por rango de fechas (formato: yyyy-MM-ddTHH:mm:ss)
    @GetMapping("/fecha")
    public List<Movimiento> getMovimientosByFecha(@RequestParam String inicio, @RequestParam String fin) {
        java.time.LocalDateTime fechaInicio = java.time.LocalDateTime.parse(inicio);
        java.time.LocalDateTime fechaFin = java.time.LocalDateTime.parse(fin);
        return movimientoService.getMovimientosByFecha(fechaInicio, fechaFin);
    }

    // Obtener movimientos por producto y rango de fechas
    @GetMapping("/producto/{productoId}/fecha")
    public List<Movimiento> getMovimientosByProductoAndFecha(
            @PathVariable Long productoId,
            @RequestParam String inicio,
            @RequestParam String fin) {
        java.time.LocalDateTime fechaInicio = java.time.LocalDateTime.parse(inicio);
        java.time.LocalDateTime fechaFin = java.time.LocalDateTime.parse(fin);
        return movimientoService.getMovimientosByProductoAndFecha(productoId, fechaInicio, fechaFin);
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> status = new HashMap<>();
        status.put("status", "UP");
        status.put("service", "MovimientoService");
        status.put("timestamp", System.currentTimeMillis());
        return status;
    }
}
