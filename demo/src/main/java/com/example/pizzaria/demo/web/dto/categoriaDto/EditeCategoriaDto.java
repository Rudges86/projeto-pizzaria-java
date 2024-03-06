package com.example.pizzaria.demo.web.dto.categoriaDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditeCategoriaDto {
    @NotBlank
    private String name;
    @NotBlank
    private String novoNome;
}
