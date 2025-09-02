package com.inventario.MovimientoService.client;

import com.inventario.MovimientoService.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UsuarioClient {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8083";

    @Autowired
    public UsuarioClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<UsuarioDTO> getUsuarioById(Long id) {
        String url = baseUrl + "/api/usuarios/internal/" + id;
        return restTemplate.getForEntity(url, UsuarioDTO.class);
    }
}
