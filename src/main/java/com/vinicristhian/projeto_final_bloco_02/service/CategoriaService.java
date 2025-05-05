package com.vinicristhian.projeto_final_bloco_02.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vinicristhian.projeto_final_bloco_02.model.Categoria;
import com.vinicristhian.projeto_final_bloco_02.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public ResponseEntity<List<Categoria>> buscarTudo() {
	return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.findAll());
    }

    public ResponseEntity<Categoria> buscarPorId(Long id) {
	return categoriaRepository.findById(id)
		.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
		.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    public ResponseEntity<List<Categoria>> buscarPorDescricao(String descricao) {
        return ResponseEntity.status(HttpStatus.OK)
        	.body(categoriaRepository.findByDescricaoContainingIgnoreCase(descricao));
    }
    
    public ResponseEntity<Categoria> cadastrarCategoria(Categoria categoria) {
	return ResponseEntity.status(HttpStatus.CREATED)
		.body(categoriaRepository.save(categoria));
    }
    
    public ResponseEntity<Categoria> atualizarCategoria(Categoria categoria) {
	return categoriaRepository.findById(categoria.getId())
		.map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
			.body(categoriaRepository.save(categoria)))
		.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    public void deletar(Long id) {
	Optional<Categoria> categoria = categoriaRepository.findById(id);
	
	if (categoria.isEmpty()) {
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não existe!", null);
	}
	
	categoriaRepository.deleteById(id);
    }
}
