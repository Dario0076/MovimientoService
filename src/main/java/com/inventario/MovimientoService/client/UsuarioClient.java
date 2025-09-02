package com.inventario.MovimientoService.client;

import com.inventario.MovimientoService.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UsuarioClient {
    private static final String BASE_URL = "http://localhost:8083";
    private final RestTemplate restTemplate;

    public UsuarioClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<UsuarioDTO> getUsuarioById(Long id) {
        String url = BASE_URL + "/api/usuarios/internal/" + id;
        return restTemplate.getForEntity(url, UsuarioDTO.class);
    }
}
