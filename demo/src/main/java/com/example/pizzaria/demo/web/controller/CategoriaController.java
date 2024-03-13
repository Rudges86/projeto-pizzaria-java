package com.example.pizzaria.demo.web.controller;

import com.example.pizzaria.demo.entity.Categoria;
import com.example.pizzaria.demo.service.CategoriaService;
import com.example.pizzaria.demo.web.dto.categoriaDto.CategoriaDto;
import com.example.pizzaria.demo.web.dto.categoriaDto.CategoriaResponseDto;
import com.example.pizzaria.demo.web.dto.categoriaDto.CreateCategoriaDto;
import com.example.pizzaria.demo.web.dto.categoriaDto.EditeCategoriaDto;
import com.example.pizzaria.demo.web.dto.mapper.CategoriaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') OR hasRole('CLIENTE')")
    public ResponseEntity<CategoriaResponseDto> cadastrarCategoria(@Valid @RequestBody CreateCategoriaDto createCategoria) {
        Categoria categoria = categoriaService.salvarCategoria(CategoriaMapper.toCategoria(createCategoria));
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaMapper.toDto(categoria));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('CLIENTE')")
    public ResponseEntity<List<CategoriaResponseDto>> buscarTodasCategorias(){
        List<Categoria> categorias = categoriaService.buscarTodasAsCategoria();
        return ResponseEntity.status(HttpStatus.OK).body(CategoriaMapper.toListDto(categorias));
    }


    @DeleteMapping("/excluirCategoria")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deletarCategoria(@Valid @RequestBody CategoriaDto dto) {
            categoriaService.deleteCategoria(dto);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{message: Categoria deletada com sucesso}");
    }

    @GetMapping("/categoriaName")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('CLIENTE')")
    public ResponseEntity<CategoriaResponseDto> buscar(@RequestParam String name) {
            Categoria categoria = categoriaService.buscarCategoriaPorNome(name);
        return ResponseEntity.ok().body(CategoriaMapper.toDto(categoria));
    }

    @PatchMapping("/editarCategoria")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Categoria> editar(@Valid @RequestBody EditeCategoriaDto dto) {
        Categoria categoria = categoriaService.editarCategoria(dto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
