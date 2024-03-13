package com.example.pizzaria.demo.web.controller;


import com.example.pizzaria.demo.entity.Usuario;
import com.example.pizzaria.demo.jwt.JwtUserDetails;
import com.example.pizzaria.demo.repository.projection.UsuarioProjection;
import com.example.pizzaria.demo.service.UsuarioService;
import com.example.pizzaria.demo.web.dto.mapper.PageableMapper;
import com.example.pizzaria.demo.web.dto.pageable.PageableDto;
import com.example.pizzaria.demo.web.dto.pageable.UsuarioProjectionDto;
import com.example.pizzaria.demo.web.dto.usuarioDto.CreateUsuarioDto;
import com.example.pizzaria.demo.web.dto.usuarioDto.UsuarioResponseDto;
import com.example.pizzaria.demo.web.dto.mapper.UsuarioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResponseDto> createUser(@RequestBody @Valid CreateUsuarioDto createUsuarioDto) {
        Usuario usuario = UsuarioMapper.toUsuario(createUsuarioDto);
        usuarioService.salvar(usuario);
        return ResponseEntity.status(201).body(UsuarioMapper.toDto(usuario));
    }


    @GetMapping("/me")
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<UsuarioResponseDto> detalhesUsuario(@AuthenticationPrincipal JwtUserDetails userDetails) {
      Usuario usuario = usuarioService.buscarPorEmail(userDetails.getUsername());
            return ResponseEntity.status(200).body(UsuarioMapper.toDto(usuario));
    }


    @GetMapping("/listarUsuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageableDto> buscarTodosOsUsuarios(@PageableDefault(size = 4, sort= {"nome"}) Pageable pageable){
        Page<UsuarioProjection> usuarios = usuarioService.buscarTodosUsuarios(pageable);

        return ResponseEntity.ok(PageableMapper.toDto(usuarios));
    }

}
