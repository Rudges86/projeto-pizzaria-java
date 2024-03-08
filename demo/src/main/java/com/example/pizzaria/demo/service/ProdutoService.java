package com.example.pizzaria.demo.service;

import com.example.pizzaria.demo.entity.Categoria;
import com.example.pizzaria.demo.entity.Produto;
import com.example.pizzaria.demo.exception.EntityNotFoundException;
import com.example.pizzaria.demo.exception.UsernameUniqueViolationException;
import com.example.pizzaria.demo.repository.ProdutoRepository;

import com.example.pizzaria.demo.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaService categoriaService;

    @Transactional
    public Produto salvarProduto(Produto produto, MultipartFile file) {
        try {
            if (UploadFileUtil.uploadFile(file)) {
                Categoria categoria = categoriaService.buscarCategoriaPorNome(produto.getCategoria().getName());
                if (categoria != null) {
                    produto.setCategoria(categoria);
                    produto.setBanner(file.getOriginalFilename());
                    return produtoRepository.save(produto);
                }
            }
        } catch (DataIntegrityViolationException e) {
            throw new UsernameUniqueViolationException(produto.getNome());
        }

        return  null;
    }

    @Transactional(readOnly = true)
    public List<Produto> buscarAllProdutos() {
        return produtoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Produto> buscarProdutoPorCategoria(String name) {
        List<Produto> produto = produtoRepository.findByCategoriaName(name);
        return produto;
    }
}
