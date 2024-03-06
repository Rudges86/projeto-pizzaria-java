package com.example.pizzaria.demo.web.dto.usuarioDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateUsuarioDto {
    @NotBlank(message = "{NotBlank.usuarioCreateDto.username}")
    private String nome;
    @Email(message = "{NotBlank.usuarioCreateDto.email}",regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String email;
    @NotBlank(message = "{NotBlank.usuarioCreateDto.password}")
    @Size(max = 6, min = 6)
    private String password;

}
