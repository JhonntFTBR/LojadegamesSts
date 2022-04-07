package com.generation.lojadegames.controller;

import java.util.List;
import java.util.Optional;

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

import com.generation.lojadegames.repository.UsuarioRepository;
import com.generation.lojadegames.service.UsuarioService;
import com.generation.lojadegames.model.Usuario;
import com.generation.lojadegames.model.UsuarioLogin;


@RestController
@RequestMapping ("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping ("/tudo")
	ResponseEntity<List<Usuario>> getall(){
		return ResponseEntity.ok(repository.findAll());
		
	}
	@GetMapping ("/{id}")
	ResponseEntity<Usuario>getid(@PathVariable Long id){
		return repository.findById(id).
				map(resposta -> ResponseEntity.ok(resposta)).
				orElse(ResponseEntity.notFound().build());
	}
	
@PostMapping ("/logar")
public ResponseEntity<UsuarioLogin> loginUsuario(@RequestBody Optional <UsuarioLogin> usuarioLogin){
	
	return service.autenticarUsuario(usuarioLogin)
		.map(respostaLog -> ResponseEntity.status(HttpStatus.OK).body(respostaLog))
		.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
}

		
	
	@PostMapping ("/cadastrar")
	ResponseEntity<Usuario> cadastro(@Valid @RequestBody Usuario usuario){
		return service.cadastrarusuario(usuario)
		.map(respostacadas -> ResponseEntity.status(HttpStatus.CREATED).body(respostacadas))
		.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
		return service.atualizarusuario(usuario)
			.map(respostaatu -> ResponseEntity.status(HttpStatus.OK).body(respostaatu))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	@DeleteMapping ("{id}")
	ResponseEntity<?>deletar(@PathVariable Long id){
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	}