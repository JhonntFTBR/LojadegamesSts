package com.generation.lojadegames.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo usuario é obrigatório e não pode estar vazio")
	@Size(min = 6, max = 20, message = "O atributo usuario precisa ter entre 6 e 20 caracteres")
	private String usuario;
	
	@NotBlank(message = "O atributo nome é obrigatório e não pode estar vazio")
	@Size(min = 10, max = 45, message = "O atributo nome precisa ter entre 10 e 45 caracteres")
	private String nome;
	
	@NotBlank(message = "O atributo senha é obrigatório e não pode estar vazio")
	@Size(min = 6, max = 60, message = "O atributo senha precisa ter entre 6 a 60 caracteres")
	private String senha;
	
	private String foto;
	
	@Column(name = "data_de_nascimento")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	/*@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Produto> produto;*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}