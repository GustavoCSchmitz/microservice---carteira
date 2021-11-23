package br.com.microservice.carteira.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.microservice.carteira.adapter.MovimentacaoFinanceiraAdapter;
import br.com.microservice.carteira.constantes.TipoMovimentacaoENUM;
import br.com.microservice.carteira.dto.InfoOperacaoBancariaDTO;
import br.com.microservice.carteira.dto.PagamentoDTO;
import br.com.microservice.carteira.dto.TransferenciaDTO;
import br.com.microservice.carteira.model.Carteira;
import br.com.microservice.carteira.model.MovimentacaoFinanceira;
import br.com.microservice.carteira.repository.MovimentacaoFinanceiraReposity;

@Service
public class MovimentacaoFinanceiraService {
	
	@Autowired private MovimentacaoFinanceiraReposity movimentacaoFinanceiraRepository;

	public void registrarMovimentacao(InfoOperacaoBancariaDTO infos, Carteira carteira) {
		MovimentacaoFinanceira movimentacao = new MovimentacaoFinanceira(carteira, infos);
		movimentacaoFinanceiraRepository.save(movimentacao);		
	}

	public void registrarMovimentacaoTransferencia(TransferenciaDTO transferenciaDTO, Carteira carteira) {
		MovimentacaoFinanceira movimentacao;		
		if(Objects.equals(transferenciaDTO.getOrigem().getTitular(), carteira.getTitular().getNome()))
			movimentacao = new MovimentacaoFinanceira(carteira, transferenciaDTO.getValor(), TipoMovimentacaoENUM.TRANSFERENCIA_SAIDA.getId());
		else
			movimentacao = new MovimentacaoFinanceira(carteira, transferenciaDTO.getValor(), TipoMovimentacaoENUM.TRANSFERENCIA_ENTRADA.getId());
		movimentacaoFinanceiraRepository.save(movimentacao);		
	}
	
	public void registrarMovimentacao(PagamentoDTO infos, Carteira carteira) {
		MovimentacaoFinanceira movimentacao = new MovimentacaoFinanceira(carteira, infos);
		movimentacaoFinanceiraRepository.save(movimentacao);		
	}

	public String getMovimentacoesUsuario(Integer idTitular) {
		List<MovimentacaoFinanceira> movimentacoes = movimentacaoFinanceiraRepository.findByUsuarioIdOrderByDataMovimentacaoDesc(idTitular);
		return montaJsonMovimentacaoFinanceira(movimentacoes);
	}
	
	public String montaJsonMovimentacaoFinanceira(List<MovimentacaoFinanceira> movimentacoes) {
		Gson gson = new GsonBuilder().registerTypeAdapter(MovimentacaoFinanceira.class, new MovimentacaoFinanceiraAdapter()).create();				
		return gson.toJson(movimentacoes);
	}

	
}
