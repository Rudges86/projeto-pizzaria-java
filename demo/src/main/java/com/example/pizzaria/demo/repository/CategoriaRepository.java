package com.example.pizzaria.demo.repository;

import com.example.pizzaria.demo.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


    Optional<Categoria> findByName(String name);
    Optional<Categoria> deleteByName(String name);

}
