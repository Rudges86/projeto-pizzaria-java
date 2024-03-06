package com.example.pizzaria.demo.web.dto.categoriaDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoriaDto {
    @NotBlank
    private String name;
}
