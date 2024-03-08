package com.example.pizzaria.demo.service;

import com.example.pizzaria.demo.entity.Categoria;
import com.example.pizzaria.demo.exception.EntityNotFoundException;
import com.example.pizzaria.demo.exception.UsernameUniqueViolationException;
import com.example.pizzaria.demo.repository.CategoriaRepository;
import com.example.pizzaria.demo.web.dto.categoriaDto.CategoriaDto;
import com.example.pizzaria.demo.web.dto.categoriaDto.EditeCategoriaDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Transactional
    public Categoria salvarCategoria(@Valid Categoria categoria) {
        try{
            return categoriaRepository.save(categoria);
        } catch (DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(categoria.getName());
        }
    }

    @Transactional(readOnly = true)
    public List<Categoria> buscarTodasAsCategoria() {
       List<Categoria> categoria = categoriaRepository.findAll();
        return categoria;
    }

    @Transactional(readOnly = true)
    public Categoria buscarCategoriaPorNome(String nome) {
        Categoria categoria = categoriaRepository.findByName(nome)
                .orElseThrow(() -> new EntityNotFoundException(nome));
        return categoria;
    }
    @Transactional
    public void deleteCategoria(CategoriaDto dto) {
        categoriaRepository.deleteByName(dto.getName()).orElseThrow(()->  new EntityNotFoundException("Categoria não encontrada"));
    }
    @Transactional
    public Categoria editarCategoria(EditeCategoriaDto categoriaDto) {
        Categoria category = buscarCategoriaPorNome(categoriaDto.getName());
        if(category != null) {
            category.setName(categoriaDto.getNovoNome());
            categoriaRepository.save(category);
        }
        return null;
    }
}
