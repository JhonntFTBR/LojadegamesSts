package com.generation.lojadegames.repository;

import java.util.Optional;

import org.springframework.data.jpa.mapping.JpaPersistentProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.lojadegames.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	public Optional<Usuario> findByUsuario(String Usuario);
// método para buscar por um usuário específico.
}