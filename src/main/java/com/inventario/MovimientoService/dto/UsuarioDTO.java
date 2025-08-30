package com.inventario.MovimientoService.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String rol;
    private Boolean activo;
}

