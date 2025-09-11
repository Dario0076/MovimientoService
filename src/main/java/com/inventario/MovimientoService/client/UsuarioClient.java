package com.inventario.MovimientoService.client;

import com.inventario.MovimientoService.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UsuarioClient {
    private final String baseUrl;
    private final RestTemplate restTemplate;

    public UsuarioClient(RestTemplate restTemplate, 
                        @Value("${usuariosservice.url:https://usuariosservice.onrender.com}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public ResponseEntity<UsuarioDTO> getUsuarioById(Long id) {
        String url = baseUrl + "/api/usuarios/internal/" + id;
        return restTemplate.getForEntity(url, UsuarioDTO.class);
    }
}
