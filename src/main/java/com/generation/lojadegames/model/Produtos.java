package com.generation.lojadegames.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produtos")
public class Produtos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O atributo nome é obrigatório e não pode estar vazio")
	@Size(min = 4, max = 50, message = "O atributo nome precisa ter entre 4 a 50 caracteres")
	private String nome;

	@NotBlank(message = "O atributo descrição é obrigatório e não pode estar vazio")
	@Size(min = 10, max = 250, message = "O atributo descrição precisa ter entre 10 a 250 caracteres")
	private String descricao;

	@NotBlank(message = "O atributo plataforma é obrigatório e não pode estar vazio")
	@Size(min = 2, max = 25, message = "O atributo plataforma precisa ter entre 2 e 25 caracteres")
	private String plataforma;

	@Size(min = 1, max = 4, message = "O atributo quantidade precisa ter entre 1 a 4 caracteres")
	private int quantidade;

	@Column(name = "data_de_lancamento")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDeLancamento;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@NotNull(message = "O atributo preço é obrigatório")
	@Positive(message = "O preço deve ser maior do que 0")
	private BigDecimal preco;

	private String foto;

	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDate getDataDeLancamento() {
		return dataDeLancamento;
	}

	public void setDataDeLancamento(LocalDate dataDeLancamento) {
		this.dataDeLancamento = dataDeLancamento;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}