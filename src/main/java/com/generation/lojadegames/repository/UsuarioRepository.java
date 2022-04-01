package com.generation.lojadegames.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.lojadegames.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}