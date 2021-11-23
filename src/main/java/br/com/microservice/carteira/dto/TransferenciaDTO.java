package br.com.microservice.carteira.dto;

public class TransferenciaDTO {

	private CarteiraDTO origem;
	private CarteiraDTO destino;
	private Double valor;
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public CarteiraDTO getDestino() {
		return destino;
	}
	
	public void setDestino(CarteiraDTO destino) {
		this.destino = destino;
	}
	
	public CarteiraDTO getOrigem() {
		return origem;
	}
	
	public void setOrigem(CarteiraDTO origem) {
		this.origem = origem;
	}
}

