package br.com.microservice.carteira.dto;

import static java.util.Objects.nonNull;

public class PagamentoDTO {

	private CarteiraDTO carteira;
	private Double valor;
	private Integer codigo;
	private String tipo;
	
	public CarteiraDTO getCarteira() {
		return carteira;
	}
	public void setCarteira(CarteiraDTO carteira) {
		this.carteira = carteira;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	
	
	public String getNomeTitularCarteira() {
		return nonNull(carteira.getTitular()) ? carteira.getTitular() : "";
	}
	
}

