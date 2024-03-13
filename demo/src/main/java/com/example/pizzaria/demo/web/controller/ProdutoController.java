package com.example.pizzaria.demo.web.controller;

import com.example.pizzaria.demo.entity.Produto;
import com.example.pizzaria.demo.repository.projection.ProdutoProjection;
import com.example.pizzaria.demo.service.ProdutoService;
import com.example.pizzaria.demo.web.dto.mapper.PageableMapper;
import com.example.pizzaria.demo.web.dto.pageable.PageableDto;
import com.example.pizzaria.demo.web.dto.produtoDto.EditarProdutoDto;
import com.example.pizzaria.demo.web.dto.produtoDto.ProdutoDto;
import com.example.pizzaria.demo.web.dto.produtoDto.ProdutoResponseDto;
import com.example.pizzaria.demo.web.dto.mapper.ProdutoMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') OR hasRole('CLIENTE')")
    public ResponseEntity<PageableDto> buscarProduto(@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable){
        /*List<Produto> produtos = new ArrayList<>();
        produtos = produtoService.buscarAllProdutos();
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoMapper.toListDto(produtos));*/
        Page<ProdutoProjection> produtos = produtoService.buscarAllProdutos(pageable);
        return ResponseEntity.ok(PageableMapper.toDto(produtos));
    }
    @PostMapping(value="/cadastrarProduto")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('CLIENTE')")
    public ResponseEntity<ProdutoResponseDto> salvar(@RequestParam("imagem") MultipartFile arquivo, @ModelAttribute ProdutoDto produtoDto) {
        Produto produto = produtoService.salvarProduto(ProdutoMapper.toProduto(produtoDto), arquivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoMapper.toResponseDto(produto));
    }

    //Mudar para pageable
    @GetMapping("/buscarProdutosPorCategoria/{name}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADMIN')")
    public ResponseEntity<List<ProdutoResponseDto>> buscarProdutosPorCategoria(@PathVariable String name){
        List<Produto> produtos = produtoService.buscarProdutoPorCategoria(name);
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoMapper.toListDto(produtos));
    }

    @DeleteMapping("deletar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{message: Produto deletado com Sucesso}");
    }

    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProdutoResponseDto> editarProduto(@PathVariable Long id, @RequestBody @Valid EditarProdutoDto produtoDto) {
        Produto produto = produtoService.editarProduto(id, produtoDto);

        return ResponseEntity.ok().body(ProdutoMapper.toResponseDto(produto));
    }
}
