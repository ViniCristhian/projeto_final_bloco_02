package com.vinicristhian.projeto_final_bloco_02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vinicristhian.projeto_final_bloco_02.model.Categoria;
import com.vinicristhian.projeto_final_bloco_02.service.CategoriaService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() {
	return categoriaService.buscarTudo();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id) {
	return categoriaService.buscarPorId(id);
    }
    
    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Categoria>> findByCategoria(@PathVariable String descricao) {
        return categoriaService.buscarPorDescricao(descricao);
    }
    
    @PostMapping
    public ResponseEntity<Categoria> postCategeria(@Valid @RequestBody Categoria categoria) {
	return categoriaService.cadastrarCategoria(categoria);
    }
    
    @PutMapping
    public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoria) {
	return categoriaService.atualizarCategoria(categoria);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
	categoriaService.deletar(id);
    }
}
