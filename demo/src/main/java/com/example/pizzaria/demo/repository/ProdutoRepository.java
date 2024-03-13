package com.example.pizzaria.demo.repository;


import com.example.pizzaria.demo.entity.Categoria;
import com.example.pizzaria.demo.entity.Produto;
import com.example.pizzaria.demo.repository.projection.ProdutoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    //Optional<List<Produto>> findByCategoriaName(String categoriaName);
    List<Produto> findByCategoria(Categoria categoria);
    //Page<ProdutoProjection> findByCategoria(Categoria categoria, Pageable pageable);
    @Query("select c from Produto c")
    Page<ProdutoProjection> findAllPageable(Pageable pageable);
}
