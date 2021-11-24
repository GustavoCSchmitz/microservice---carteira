package br.com.microservice.carteira.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.microservice.carteira.dto.UsuarioDTO;

@Entity
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "id_titular")
	private Usuario titular;
	
	private Double saldo;

	public Carteira(UsuarioDTO usuarioDTO, Integer idUsuario) {
		setTitular(new Usuario(idUsuario));
		setSaldo(Double.parseDouble(usuarioDTO.getSaldoInicial()));
	}
	
	public Carteira() {
		
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getTitular() {
		return titular;
	}

	public void setTitular(Usuario titular) {
		this.titular = titular;
	}
	
}