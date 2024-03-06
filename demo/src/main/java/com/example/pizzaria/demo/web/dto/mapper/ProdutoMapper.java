package com.example.pizzaria.demo.web.dto.mapper;

import com.example.pizzaria.demo.entity.Produto;
import com.example.pizzaria.demo.web.dto.produtoDto.ProdutoDto;
import com.example.pizzaria.demo.web.dto.produtoDto.ProdutoResponseDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoMapper {
    public static Produto toProduto(ProdutoResponseDto produtoResponseDto) {
        return new ModelMapper().map(produtoResponseDto, Produto.class);
    }
    public static Produto toProduto(ProdutoDto produtoDto) {
        return new ModelMapper().map(produtoDto, Produto.class);
    }
    public static ProdutoDto toDto(Produto produto) {
        return new ModelMapper().map(produto, ProdutoDto.class);
    }

    public static ProdutoResponseDto toResponseDto(Produto produto) {
        return new ModelMapper().map(produto, ProdutoResponseDto.class);
    }
    public static List<ProdutoResponseDto> toListDto(List<Produto> produtos ) {
        return produtos.stream().map(produto -> toResponseDto(produto)).collect(Collectors.toList());
    }
}
