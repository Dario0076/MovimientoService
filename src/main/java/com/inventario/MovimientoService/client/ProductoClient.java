package com.inventario.MovimientoService.client;

import com.inventario.MovimientoService.dto.ProductoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductoClient {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8084";

    @Autowired
    public ProductoClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<ProductoDTO> getProductoById(Long id) {
        String url = baseUrl + "/productos/" + id;
        return restTemplate.getForEntity(url, ProductoDTO.class);
    }
}
