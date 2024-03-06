package com.example.pizzaria.demo.web.dto.mapper;

import com.example.pizzaria.demo.entity.Categoria;
import com.example.pizzaria.demo.web.dto.categoriaDto.CategoriaDto;
import com.example.pizzaria.demo.web.dto.categoriaDto.CategoriaResponseDto;
import com.example.pizzaria.demo.web.dto.categoriaDto.CreateCategoriaDto;
import com.example.pizzaria.demo.web.dto.categoriaDto.EditeCategoriaDto;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaMapper {
    public static Categoria toCategoria(CategoriaResponseDto createDto) {
        return new ModelMapper().map(createDto, Categoria.class);
    }
    public static Categoria toCategoria(CreateCategoriaDto createDto) {
        return new ModelMapper().map(createDto, Categoria.class);
    }
    public static Categoria toCategoria(EditeCategoriaDto createDto) {
        return new ModelMapper().map(createDto, Categoria.class);
    }
    public static CategoriaResponseDto toDto(Categoria categoria) {
        return new ModelMapper().map(categoria, CategoriaResponseDto.class);
    }

    public static List<CategoriaResponseDto> toListDto(List<Categoria> categorias) {
        return categorias.stream().map(categoria -> toDto(categoria)).collect(Collectors.toList());
    }
}
