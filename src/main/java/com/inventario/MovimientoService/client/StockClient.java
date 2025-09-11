package com.inventario.MovimientoService.client;

import com.inventario.MovimientoService.dto.StockDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StockClient {
    private final String baseUrl;
    private final RestTemplate restTemplate;

    public StockClient(RestTemplate restTemplate, 
                      @Value("${stockservice.url:https://stockservice-wki5.onrender.com}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
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
