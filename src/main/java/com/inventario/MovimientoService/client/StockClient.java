package com.inventario.MovimientoService.client;

import com.inventario.MovimientoService.dto.StockDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StockClient {
    private static final String BASE_URL = "http://localhost:8081";
    private final RestTemplate restTemplate;

    public StockClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<StockDTO> updateStock(Long id, StockDTO stock) {
        String url = BASE_URL + "/stock/" + id;
        HttpEntity<StockDTO> request = new HttpEntity<>(stock);
        return restTemplate.exchange(url, org.springframework.http.HttpMethod.PUT, request, StockDTO.class);
    }
    
    public ResponseEntity<StockDTO> getStockByProductoId(Long productoId) {
        String url = BASE_URL + "/stock/producto/" + productoId;
        return restTemplate.getForEntity(url, StockDTO.class);
    }
}
