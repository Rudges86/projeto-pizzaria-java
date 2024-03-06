package com.example.pizzaria.demo.web.controller;

import com.example.pizzaria.demo.entity.Produto;
import com.example.pizzaria.demo.service.ProdutoService;
import com.example.pizzaria.demo.web.dto.produtoDto.ProdutoDto;
import com.example.pizzaria.demo.web.dto.produtoDto.ProdutoResponseDto;
import com.example.pizzaria.demo.web.dto.mapper.ProdutoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<ProdutoResponseDto>> buscarProduto(){
        List<Produto> produtos = new ArrayList<>();
        produtos = produtoService.buscarAllProdutos();
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoMapper.toListDto(produtos));
    }
    @PostMapping(value="/cadastrarProduto")
    public ResponseEntity<ProdutoResponseDto> salvar(@RequestParam("imagem") MultipartFile arquivo, @ModelAttribute ProdutoDto produtoDto) {
        Produto produto = produtoService.salvarProduto(ProdutoMapper.toProduto(produtoDto), arquivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoMapper.toResponseDto(produto));
    }

    @GetMapping("/buscarProdutosPorCategoria")
    public ResponseEntity<List<ProdutoResponseDto>> buscarProdutosPorCategoria(@RequestParam String name){
        List<Produto> produtos = produtoService.buscarProdutoPorCategoria(name);
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoMapper.toListDto(produtos));
    }
}
