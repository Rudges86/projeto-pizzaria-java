package com.example.pizzaria.demo.service;

import com.example.pizzaria.demo.entity.Categoria;
import com.example.pizzaria.demo.entity.Produto;
import com.example.pizzaria.demo.exception.EntityNotFoundException;
import com.example.pizzaria.demo.exception.ProdutosNotCategoriaFoundException;
import com.example.pizzaria.demo.exception.UsernameUniqueViolationException;
import com.example.pizzaria.demo.repository.ProdutoRepository;

import com.example.pizzaria.demo.repository.projection.ProdutoProjection;
import com.example.pizzaria.demo.util.UploadFileUtil;
import com.example.pizzaria.demo.web.dto.produtoDto.EditarProdutoDto;
import com.example.pizzaria.demo.web.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import reactor.netty.http.server.HttpServer;

import java.util.List;
import java.util.Optional;

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
    public Page<ProdutoProjection> buscarAllProdutos(Pageable pageable) {
        return produtoRepository.findAllPageable(pageable);
    }

    @Transactional(readOnly = true)
    public List<Produto> buscarProdutoPorCategoria(String name) {
            Categoria categoria = categoriaService.buscarCategoriaPorNome(name);

            List<Produto> produtos = produtoRepository.findByCategoria(categoria);

            if(produtos.isEmpty()) {
                throw new ProdutosNotCategoriaFoundException("Não há produtos na categoria");
            }
            return produtos;
    }

    @Transactional (readOnly = true)
    public Produto buscarPorId(Long id) {
         Produto produto = produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto"));

        return produto;
    }

    @Transactional
    public void deletarProduto(Long id) {
            produtoRepository.deleteById(id);
    }

    @Transactional
    public Produto editarProduto(Long id, EditarProdutoDto produtoEditar) {
        Produto produto = buscarPorId(id);
        if(!produtoEditar.getNovoNome().isEmpty() || !produtoEditar.getNovoNome().isBlank()) {
            produto.setNome(produtoEditar.getNome());
        }

        if(!produtoEditar.getNovaDescricao().isEmpty() || !produtoEditar.getNovaDescricao().isBlank()) {
            produto.setDescricao(produtoEditar.getNovaDescricao());
        }
        if(!produtoEditar.getNovoPrice().isEmpty() || !produtoEditar.getNovoPrice().isBlank()) {
            produto.setPrice(produtoEditar.getNovoPrice());
        }
        if(!produtoEditar.getNovoBanner().isEmpty() || !produtoEditar.getNovoBanner().isBlank()) {
            produto.setBanner(produtoEditar.getNovoBanner());
        }

        if(!produtoEditar.getNovaCategoria().getName().isEmpty() || !produtoEditar.getNovaCategoria().getName().isEmpty()) {
            Categoria categoria = categoriaService.buscarCategoriaPorNome(produtoEditar.getNovaCategoria().getName());
            produto.setCategoria(categoria);
        }

        return produto;

    }
}
