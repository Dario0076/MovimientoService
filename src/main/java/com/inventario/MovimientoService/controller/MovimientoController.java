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

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> status = new HashMap<>();
        status.put("status", "UP");
        status.put("service", "MovimientoService");
        status.put("timestamp", System.currentTimeMillis());
        return status;
    }
}
