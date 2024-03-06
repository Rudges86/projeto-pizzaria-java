package com.example.pizzaria.demo.web.dto.usuarioDto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDto {
    private Long id;
    private String nome;
    private String email;
    private String role;
}
