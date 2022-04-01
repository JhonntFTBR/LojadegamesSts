package com.generation.lojadegames.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojadegames.model.Produtos;
import com.generation.lojadegames.repository.CategoriaRepository;
import com.generation.lojadegames.repository.ProdutosRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutosController {

	@Autowired
	private ProdutosRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public ResponseEntity<List<Produtos>> getAll() {

		return ResponseEntity.ok(produtoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produtos> getById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(resul -> ResponseEntity.ok(resul))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produtos>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<Produtos> postProduto(@Valid @RequestBody Produtos produto) {
		if (produtoRepository.existsById(produto.getId())) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PutMapping
	public ResponseEntity<Produtos> putProduto(@Valid @RequestBody Produtos produto) {
		if (produtoRepository.existsById(produto.getId())) {
			return categoriaRepository.findById(produto.getCategoria().getId())
					.map(resultado -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto)))
					.orElse(ResponseEntity.badRequest().build());
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id) {
		return produtoRepository.findById(id).map(resul -> {
			produtoRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/preco_maior/{preco}")
	public ResponseEntity<List<Produtos>> getPrecoMaiorQue(@PathVariable BigDecimal preco) {
		return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanOrderByPreco(preco));
	}

	@GetMapping("/preco_menor/{preco}")
	public ResponseEntity<List<Produtos>> getPrecoMenorQue(@PathVariable BigDecimal preco) {
		return ResponseEntity.ok(produtoRepository.findByPrecoLessThanOrderByPreco(preco));
	}
}