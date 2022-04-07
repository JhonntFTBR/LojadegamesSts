package com.generation.lojadegames.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.lojadegames.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
	
	public List<Produtos> findAllByNomeContainingIgnoreCase(String nome);
	// coloquei o nome, para utiliza-lo para fazer uma busca pelo nome dentro do produto.
	
}