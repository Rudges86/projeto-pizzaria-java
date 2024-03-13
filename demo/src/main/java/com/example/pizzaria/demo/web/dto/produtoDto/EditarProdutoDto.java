package com.example.pizzaria.demo.web.dto.produtoDto;

import com.example.pizzaria.demo.web.dto.categoriaDto.CategoriaDto;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EditarProdutoDto {
    @NotNull
    private String nome;
    private String novoNome;
    private String descricao;
    private String novaDescricao;
    private String banner;
    private String novoBanner;
    private String price;
    private String novoPrice;
    private CategoriaDto categoria;
    private CategoriaDto novaCategoria;

}
