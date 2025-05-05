package com.vinicristhian.projeto_final_bloco_02.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vinicristhian.projeto_final_bloco_02.model.Produto;
import com.vinicristhian.projeto_final_bloco_02.repository.CategoriaRepository;
import com.vinicristhian.projeto_final_bloco_02.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public ResponseEntity<List<Produto>> buscarTudo() {
	return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());
    }

    public ResponseEntity<Produto> buscarPorId(Long id) {
	return produtoRepository.findById(id)
		.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
		.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    public ResponseEntity<List<Produto>> buscarPorNome(String nome) {
        return ResponseEntity.status(HttpStatus.OK)
        	.body(produtoRepository.findByNomeContainingIgnoreCase(nome));
    }
    
    public ResponseEntity<Produto> cadastrarProduto(Produto produto) {
	if (categoriaRepository.existsById(produto.getCategoria().getId())) {
	    return ResponseEntity.status(HttpStatus.CREATED)
		.body(produtoRepository.save(produto));
	}
	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não existe!", null);
    }
	
    
    public ResponseEntity<Produto> atualizarProduto(Produto produto) {
	if (produtoRepository.existsById(produto.getId())) {
	    if (categoriaRepository.existsById(produto.getCategoria().getId())) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(produtoRepository.save(produto));
	    }
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não existe!", null);
	}
	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    public void deletar(Long id) {
	Optional<Produto> categoria = produtoRepository.findById(id);
	
	if (categoria.isEmpty()) {
	    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não existe!");
	}
	
	produtoRepository.deleteById(id);
    }
}
