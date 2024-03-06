package com.example.pizzaria.demo.repository;

import com.example.pizzaria.demo.entity.Usuario;
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
}
