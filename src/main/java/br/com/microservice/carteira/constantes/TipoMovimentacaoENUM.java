package br.com.microservice.carteira.constantes;

public enum TipoMovimentacaoENUM {
	
	//interface funcional
	SAQUE("S","Saque"),
	DEPOSITO("D", "Depósito"),
	TRANSFERENCIA_SAIDA("TS","Transferência saída"),
	TRANSFERENCIA_ENTRADA("TE","Transferência entrada"),
	PAGAMENTO("P", "Pagamento de conta");
		
	private String id;
	private String descricao;
	
	TipoMovimentacaoENUM(String id, String descricao) {
		setId(id);
		setDescricao(descricao);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
