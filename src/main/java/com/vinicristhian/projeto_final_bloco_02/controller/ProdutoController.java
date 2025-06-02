package com.vinicristhian.projeto_final_bloco_02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vinicristhian.projeto_final_bloco_02.model.Produto;
import com.vinicristhian.projeto_final_bloco_02.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
	return produtoService.buscarTudo();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
	return produtoService.buscarPorId(id);
    }
    
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Produto>> findByNome(@PathVariable String nome) {
        return produtoService.buscarPorNome(nome);
    }
    
    @PostMapping
    public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto) {
	return produtoService.cadastrarProduto(produto);
    }
    
    @PutMapping
    public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto) {
	return produtoService.atualizarProduto(produto);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
	produtoService.deletar(id);
    }
}
