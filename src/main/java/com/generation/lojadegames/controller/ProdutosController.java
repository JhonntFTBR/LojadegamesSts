package com.generation.lojadegames.controller;

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
	private ProdutosRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	ResponseEntity<List<Produtos>>getAll(){
		return ResponseEntity.ok().body(repository.findAll());}
	@GetMapping ("/id")
	public ResponseEntity<Produtos> getById(@PathVariable Long id){
		if(repository.existsById(id)) {
			return ResponseEntity.ok(repository.getById(id));
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produtos>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}	
	@PostMapping
	public ResponseEntity<Produtos>post(@Valid @RequestBody Produtos produto){
		if(categoriaRepository.existsById(produto.getCategoria().getId())) {
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	@PutMapping
	public ResponseEntity<Produtos>put(@Valid @RequestBody Produtos produto){
		if(categoriaRepository.existsById(produto.getCategoria().getId())) {
			return ResponseEntity.ok().body(repository.save(produto));
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}
	}
	@DeleteMapping ("/id")
	public ResponseEntity<Produtos>delete(@PathVariable Long id){
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		}
	}

}