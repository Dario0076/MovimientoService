package com.inventario.MovimientoService.client;

import com.inventario.MovimientoService.dto.ProductoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductoClient {
    private final String baseUrl;
    private final RestTemplate restTemplate;

    public ProductoClient(RestTemplate restTemplate, 
                         @Value("${productosservice.url:https://productosservices.onrender.com}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public ResponseEntity<ProductoDTO> getProductoById(Long id) {
        String url = baseUrl + "/productos/" + id;
        return restTemplate.getForEntity(url, ProductoDTO.class);
    }
}
