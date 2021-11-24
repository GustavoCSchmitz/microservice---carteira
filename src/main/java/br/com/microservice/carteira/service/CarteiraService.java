package br.com.microservice.carteira.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import static java.util.Objects.nonNull;

import br.com.microservice.carteira.constantes.TipoMovimentacaoENUM;
import br.com.microservice.carteira.dto.InfoOperacaoBancariaDTO;
import br.com.microservice.carteira.dto.PagamentoDTO;
import br.com.microservice.carteira.dto.TransferenciaDTO;
import br.com.microservice.carteira.model.Carteira;
import br.com.microservice.carteira.repository.CarteiraRepository;

@Service
public class CarteiraService {

	@Autowired private CarteiraRepository carteiraRepository;
	@Autowired private MovimentacaoFinanceiraService movimentacaoFinanceiraService;
	
	public Carteira getInfosDaCarteira(String nomeTitular) {
		return carteiraRepository.findByTitularNome(nomeTitular);
	}

	public void saque(String saque) {
		InfoOperacaoBancariaDTO infos = montaJson(saque);
		if(nonNull(getInfosDaCarteira(infos.getCarteira().getTitular()))) {
			//utilizar builder nas linhas 29 e 30 usar lombok
			Carteira carteira = getInfosDaCarteira(infos.getCarteira().getTitular());
			carteira.setSaldo(carteira.getSaldo() - infos.getValor());
			salvar(carteira);
			infos.setTipo(TipoMovimentacaoENUM.SAQUE.getId());
			movimentacaoFinanceiraService.registrarMovimentacao(infos, carteira);
		}		
	}

	public void salvar(Carteira carteira) {
		carteiraRepository.save(carteira);		
	}

	private InfoOperacaoBancariaDTO montaJson(String saque) {
		Gson gson = new Gson();
		return gson.fromJson(saque, InfoOperacaoBancariaDTO.class);
	}

	public void deposito(String deposito) {
		InfoOperacaoBancariaDTO infos = montaJson(deposito);
		if(nonNull(getInfosDaCarteira(infos.getCarteira().getTitular()))) {
			Carteira carteira = getInfosDaCarteira(infos.getCarteira().getTitular());
			carteira.setSaldo(carteira.getSaldo() + infos.getValor());
			salvar(carteira);
			infos.setTipo(TipoMovimentacaoENUM.DEPOSITO.getId());
			movimentacaoFinanceiraService.registrarMovimentacao(infos, carteira);
		}		
	}

	public void transferencia(String transferencia) {
		TransferenciaDTO transferenciaDTO = montaJsonTransferencia(transferencia);		
		if(nonNull(verificaSeCarteirasExistem(transferenciaDTO))) {
			Carteira carteiraOrigem = getInfosDaCarteira(transferenciaDTO.getOrigem().getTitular());
			Carteira carteiraDestino = getInfosDaCarteira(transferenciaDTO.getDestino().getTitular());
			
			carteiraOrigem.setSaldo(carteiraOrigem.getSaldo() - transferenciaDTO.getValor());
			carteiraDestino.setSaldo(carteiraDestino.getSaldo() + transferenciaDTO.getValor());
			
			salvar(carteiraOrigem);
			salvar(carteiraDestino);
			
			movimentacaoFinanceiraService.registrarMovimentacaoTransferencia(transferenciaDTO, carteiraOrigem);
			movimentacaoFinanceiraService.registrarMovimentacaoTransferencia(transferenciaDTO, carteiraDestino);
		}else {
			//lançar excessão aqui
		}
	}
	
	private boolean verificaSeCarteirasExistem(TransferenciaDTO transferenciaDTO) {
		return nonNull(getInfosDaCarteira(transferenciaDTO.getOrigem().getTitular())) 
				&& nonNull(getInfosDaCarteira(transferenciaDTO.getDestino().getTitular()));
		
	}

	private TransferenciaDTO montaJsonTransferencia(String saque) {
		Gson gson = new Gson();
		return gson.fromJson(saque, TransferenciaDTO.class);
	}

	public void pagamento(PagamentoDTO pagamento) {
		if(nonNull(getInfosDaCarteira(pagamento.getCarteira().getTitular()))) {
			Carteira carteira = getInfosDaCarteira(pagamento.getCarteira().getTitular());
			carteira.setSaldo(carteira.getSaldo() - pagamento.getValor());
			salvar(carteira);
			pagamento.setTipo(TipoMovimentacaoENUM.PAGAMENTO.getId());
			movimentacaoFinanceiraService.registrarMovimentacao(pagamento, carteira);
		}		
		
	}


}