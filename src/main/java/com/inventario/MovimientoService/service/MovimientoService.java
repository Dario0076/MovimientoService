package com.inventario.MovimientoService.service;

import com.inventario.MovimientoService.client.ProductoClient;
import com.inventario.MovimientoService.client.StockClient;
import com.inventario.MovimientoService.client.UsuarioClient;
import com.inventario.MovimientoService.dto.ProductoDTO;
import com.inventario.MovimientoService.dto.StockDTO;
import com.inventario.MovimientoService.dto.UsuarioDTO;
import com.inventario.MovimientoService.entity.Movimiento;
import com.inventario.MovimientoService.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private StockClient stockClient;
    @Autowired
    private ProductoClient productoClient;
    @Autowired
    private UsuarioClient usuarioClient;

    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public Optional<Movimiento> getMovimientoById(Long id) {
        return movimientoRepository.findById(id);
    }

    public Movimiento saveMovimiento(Movimiento movimiento, StockDTO stockDTO, Long usuarioId) {
        // Validar que el producto existe y está activo
        ResponseEntity<ProductoDTO> productoResponse = productoClient.getProductoById(movimiento.getProductoId());
        if (!productoResponse.getStatusCode().is2xxSuccessful() || productoResponse.getBody() == null || !Boolean.TRUE.equals(productoResponse.getBody().getActivo())) {
            throw new IllegalArgumentException("El producto no existe o está inactivo");
        }
        
        // Validar que el usuario existe y está activo
        ResponseEntity<UsuarioDTO> usuarioResponse = usuarioClient.getUsuarioById(usuarioId);
        if (!usuarioResponse.getStatusCode().is2xxSuccessful() || usuarioResponse.getBody() == null || !Boolean.TRUE.equals(usuarioResponse.getBody().getActivo())) {
            throw new IllegalArgumentException("El usuario no existe o está inactivo");
        }
        
        // Obtener el stock actual del producto
        ResponseEntity<StockDTO> stockActualResponse = stockClient.getStockByProductoId(movimiento.getProductoId());
        if (!stockActualResponse.getStatusCode().is2xxSuccessful() || stockActualResponse.getBody() == null) {
            throw new IllegalArgumentException("No se pudo obtener el stock actual del producto");
        }
        
        StockDTO stockActual = stockActualResponse.getBody();
        
        // Validar que el stock actual no sea null y calcular nuevo stock
        Integer cantidadActual = stockActual.getCantidadActual();
        if (cantidadActual == null) {
            cantidadActual = 0; // Si es null, inicializar en 0
        }
        
        int nuevaCantidad;
        if ("ENTRADA".equals(movimiento.getTipo())) {
            // Para entrada, sumar la cantidad
            nuevaCantidad = cantidadActual + movimiento.getCantidad();
        } else if ("SALIDA".equals(movimiento.getTipo())) {
            // Para salida, restar la cantidad
            nuevaCantidad = cantidadActual - movimiento.getCantidad();
            if (nuevaCantidad < 0) {
                throw new IllegalArgumentException("No hay suficiente stock para realizar esta salida");
            }
        } else {
            throw new IllegalArgumentException("Tipo de movimiento inválido: " + movimiento.getTipo());
        }
        
        // Actualizar el DTO con la nueva cantidad
        stockActual.setCantidadActual(nuevaCantidad);
        
        // Registrar el movimiento y guardar el nombre del usuario para auditoría
        movimiento.setUsuario(usuarioResponse.getBody().getNombre());
        movimiento.setUsuarioEmail(usuarioResponse.getBody().getCorreo());
        Movimiento mov = movimientoRepository.save(movimiento);
        
        // Actualizar el stock con la nueva cantidad calculada
        stockClient.updateStock(stockActual.getId(), stockActual);
        
        return mov;
    }

    // Método simple para guardar movimiento sin validaciones complejas
    public Movimiento saveMovimiento(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    public void deleteMovimiento(Long id) {
        // No se elimina físicamente ningún movimiento (auditoría)
    }
}
