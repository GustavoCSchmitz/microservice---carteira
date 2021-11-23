package br.com.microservice.carteira.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.microservice.carteira.dto.InfoOperacaoBancariaDTO;
import br.com.microservice.carteira.dto.PagamentoDTO;

@Entity
public class MovimentacaoFinanceira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String tipo;
	
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	private Double valor;
	
	private LocalDateTime dataMovimentacao;
	
	//Construtor para movimentações de transferências
	public MovimentacaoFinanceira(Carteira carteira,InfoOperacaoBancariaDTO infos) {
		setValor(infos.getValor());
		setTipo(infos.getTipo());
		setUsuario(new Usuario(carteira.getTitular().getId()));
		setDataMovimentacao(LocalDateTime.now());
	}
	
	//Construtor para movimentações de saque e depósito
	public MovimentacaoFinanceira(Carteira carteira, Double valor, String tipoMovimentacao) {
		setValor(valor);
		setTipo(tipoMovimentacao);
		setUsuario(new Usuario(carteira.getTitular().getId()));
		setDataMovimentacao(LocalDateTime.now());
	}
	
	public MovimentacaoFinanceira() {
		
	}

	//Construtor para pagamentos
	public MovimentacaoFinanceira(Carteira carteira, PagamentoDTO infos) {
		setValor(infos.getValor());
		setTipo(infos.getTipo());
		setUsuario(new Usuario(carteira.getTitular().getId()));
		setDataMovimentacao(LocalDateTime.now());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}
}
