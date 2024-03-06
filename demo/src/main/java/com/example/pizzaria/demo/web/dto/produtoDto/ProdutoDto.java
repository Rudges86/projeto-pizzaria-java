package com.example.pizzaria.demo.web.dto.produtoDto;

import com.example.pizzaria.demo.web.dto.categoriaDto.CategoriaDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDto {
    private String nome;
    private String descricao;
    private String banner;
    private String price;
    private CategoriaDto categoria;
}
