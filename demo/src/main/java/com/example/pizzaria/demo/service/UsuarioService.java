package com.example.pizzaria.demo.service;

import com.example.pizzaria.demo.entity.Usuario;
import com.example.pizzaria.demo.exception.EntityNotFoundException;
import com.example.pizzaria.demo.exception.UsernameUniqueViolationException;
import com.example.pizzaria.demo.repository.UsuarioRepository;
import com.example.pizzaria.demo.repository.projection.UsuarioProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public void salvar(Usuario usuario) {
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new UsernameUniqueViolationException(usuario.getNome());
        }

    }
    @Transactional(readOnly = true)
    public Usuario.Role buscarRolePorEmail(String email) {
        return usuarioRepository.findRoleByEmail(email);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorEmail (String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("Usuario",email)
        );
    }

    @Transactional(readOnly = true)
    public Page<UsuarioProjection> buscarTodosUsuarios(Pageable pageable){
        return usuarioRepository.findAllPageable(pageable);
    }
}
