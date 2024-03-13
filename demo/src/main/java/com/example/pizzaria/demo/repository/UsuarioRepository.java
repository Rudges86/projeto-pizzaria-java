package com.example.pizzaria.demo.repository;

import com.example.pizzaria.demo.entity.Usuario;
import com.example.pizzaria.demo.repository.projection.ProdutoProjection;
import com.example.pizzaria.demo.repository.projection.UsuarioProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

  //  @Query("select u.role from Usuario u where u.nome like :nome")
    //Usuario.Role findRoleByNome(String nome);
// Optional<Usuario> findByNome(String nome);
    @Query("select u.role from Usuario u where u.email like :email")
    Usuario.Role findRoleByEmail(String email);
    Optional<Usuario> findByEmail(String email);

  @Query("select c from Usuario c")
  Page<UsuarioProjection> findAllPageable(Pageable pageable);


}
