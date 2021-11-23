package br.com.microservice.carteira.dto;

public class InfoOperacaoBancariaDTO {

	private CarteiraDTO carteira;	
	private Double valor;
	private String tipo;
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public CarteiraDTO getCarteira() {
		return carteira;
	}
	
	public void setCarteira(CarteiraDTO carteira) {
		this.carteira = carteira;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
