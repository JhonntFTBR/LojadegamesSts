package com.generation.lojadegames.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.lojadegames.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
	
	public List<Produtos> findAllByNomeContainingIgnoreCase(String nome);
	
	public List<Produtos> findByPrecoGreaterThanOrderByPreco(BigDecimal preco);

	public List<Produtos> findByPrecoLessThanOrderByPreco(BigDecimal preco);
}