package com.example.pizzaria.demo.repository;

import com.example.pizzaria.demo.entity.Categoria;
import com.example.pizzaria.demo.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {


    List<Produto> findByCategoria(Categoria categoria);

}
