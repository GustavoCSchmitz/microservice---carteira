package br.com.microservice.carteira.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.microservice.carteira.dto.UsuarioDTO;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;

	public Usuario(Integer id) {
		setId(id);
	}
	
	public Usuario() {
	}

	public Usuario(UsuarioDTO usuarioDTO) {
		setNome(usuarioDTO.getNome());
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
