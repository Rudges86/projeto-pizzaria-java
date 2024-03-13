package com.example.pizzaria.demo.repository.projection;

import com.example.pizzaria.demo.entity.Categoria;

public interface ProdutoProjection {
    Long getId();
    String getNome();
    String getDescricao();
    String getBanner();
    String getPrice();
    Categoria getCategoria();
}
