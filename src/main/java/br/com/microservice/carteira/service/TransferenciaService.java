package br.com.microservice.carteira.service;

import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {

	public boolean verficaSeTransferenciaEhValida(Double saldoCarteiraOrigem, Double valorTransferencia) {		
		return saldoCarteiraOrigem - valorTransferencia >= 0;
	}

	
}
