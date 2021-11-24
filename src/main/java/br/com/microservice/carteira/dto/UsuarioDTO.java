package br.com.microservice.carteira.dto;

public class UsuarioDTO {
	
	private String nome;
	private String saldoInicial;
	
	
	public String getSaldoInicial() {
		return saldoInicial;
	}
	
	public void setSaldoInicial(String saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}	

}
