package com.inventario.MovimientoService.client;

import com.inventario.MovimientoService.dto.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StockClient {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8081";

    @Autowired
    public StockClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<StockDTO> updateStock(Long id, StockDTO stock) {
        String url = baseUrl + "/stock/" + id;
        HttpEntity<StockDTO> request = new HttpEntity<>(stock);
        return restTemplate.exchange(url, org.springframework.http.HttpMethod.PUT, request, StockDTO.class);
    }
    
    public ResponseEntity<StockDTO> getStockByProductoId(Long productoId) {
        String url = baseUrl + "/stock/producto/" + productoId;
        return restTemplate.getForEntity(url, StockDTO.class);
    }
}
