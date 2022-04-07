package com.generation.lojadegames.service;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import com.generation.lojadegames.model.Usuario;
import com.generation.lojadegames.model.UsuarioLogin;
import com.generation.lojadegames.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Usuario>cadastrarusuario(Usuario usuario){
		if(repository.findByUsuario(usuario.getUsuario()).isPresent()) {
			return Optional.empty();
		}
		if (idade(usuario.getDatadenasc()) < 18) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Você precisa ter pelo menos 18 anos para se cadastrar", null);}
		
		if(usuario.getFoto().isBlank()) {
			usuario.setFoto("https://i.imgur.com/IhQhKMH.jpg");
		}
		usuario.setSenha(criptografarSenha(usuario.getSenha()));

		return Optional.ofNullable(repository.save(usuario));
		}
	
	public Optional<Usuario> atualizarusuario(Usuario usuario){
		if(repository.existsById(usuario.getId())) {
			Optional<Usuario> existiusuario = repository.findByUsuario(usuario.getUsuario());
			// vou checar se existe o usuario e se o id desse usuário são iguais.	
			if(existiusuario.isPresent() && existiusuario.get().getId() != usuario.getId()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse usuário já existe", null);
			}
			else {
				usuario.setSenha(criptografarSenha(usuario.getSenha()));

				return Optional.ofNullable(repository.save(usuario));
			}
		}
		return Optional.empty();
	}
		
	
	// agora vou autenticar o login para verificar se está tudo correto
	
public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin){
		
		Optional<Usuario> buscaUsuario = repository.findByUsuario(usuarioLogin.get().getUsuario());
		
		if(buscaUsuario.isPresent()) {
			
			if( verificarsenha(usuarioLogin.get().getSenha(), buscaUsuario.get().getSenha()) ) {
				
				usuarioLogin.get().setId(buscaUsuario.get().getId());
				usuarioLogin.get().setNome(buscaUsuario.get().getNome());
				usuarioLogin.get().setFoto(buscaUsuario.get().getFoto());
				usuarioLogin.get().setDataNascimento(buscaUsuario.get().getDatadenasc());
				usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(buscaUsuario.get().getSenha());
				
				return usuarioLogin;
			}
			
		}
		
		return Optional.empty();
	
	}
	// esse método é para gerar o token
	private String gerarBasicToken(String usuario, String senha) {
		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

		
		
	}

	// abaixo estão todas as funções criadas.
	
	// aqui estou criptografando a senha usando o bean que criei na classe de security.
	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
		
	}

	private int idade(LocalDate datadenasc) {
		return Period.between(datadenasc, LocalDate.now()).getYears();
	}
	private boolean verificarsenha(String senha, String senhabanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senha, senhabanco);
		// o matches analisa se as senhas são iguais

	}
	

	
	

}