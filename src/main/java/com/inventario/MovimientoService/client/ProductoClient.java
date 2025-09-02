package com.inventario.MovimientoService.client;

import com.inventario.MovimientoService.dto.ProductoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductoClient {
    private static final String BASE_URL = "http://localhost:8084";
    private final RestTemplate restTemplate;

    public ProductoClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<ProductoDTO> getProductoById(Long id) {
        String url = BASE_URL + "/productos/" + id;
        return restTemplate.getForEntity(url, ProductoDTO.class);
    }
}
