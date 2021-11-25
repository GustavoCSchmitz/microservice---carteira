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
	@Autowired private TransferenciaService transferenciaService;
		
	public String saque (String saque) {
		InfoOperacaoBancariaDTO infos = montaJson(saque);
		if(nonNull(getInfosDaCarteira(infos.getCarteira().getTitular()))) {
			Carteira carteira = getInfosDaCarteira(infos.getCarteira().getTitular());
			if(verificaSeTemSaldo(carteira.getSaldo(),infos.getValor())) {
				carteira.setSaldo(carteira.getSaldo() - infos.getValor());
				salvar(carteira);
				infos.setTipo(TipoMovimentacaoENUM.SAQUE.getId());
				movimentacaoFinanceiraService.registrarMovimentacao(infos, carteira);
				return "Saque no valor de R$"+infos.getValor().toString()+" realizado com sucesso!";
			}else
				return "A carteira não possui saldo suficiente para completar o saque!";
		}		
		return "A carteira informada não foi cadastrada!";
	}
	
	public String deposito(String deposito) {
		InfoOperacaoBancariaDTO infos = montaJson(deposito);
		if(nonNull(getInfosDaCarteira(infos.getCarteira().getTitular()))) {
			Carteira carteira = getInfosDaCarteira(infos.getCarteira().getTitular());
			carteira.setSaldo(carteira.getSaldo() + infos.getValor());
			salvar(carteira);
			infos.setTipo(TipoMovimentacaoENUM.DEPOSITO.getId());
			movimentacaoFinanceiraService.registrarMovimentacao(infos, carteira);
			return "Depósito no valor de R$"+ infos.getValor() +" realizado com sucesso"; 
		}
		return "A carteira informada não foi cadastrada!";
	}
	
	public String transferencia(String transferencia) {
		TransferenciaDTO transferenciaDTO = montaJsonTransferencia(transferencia);		
		if(nonNull(verificaSeCarteirasExistem(transferenciaDTO))) {
			Carteira carteiraOrigem = getInfosDaCarteira(transferenciaDTO.getOrigem().getTitular());
			Carteira carteiraDestino = getInfosDaCarteira(transferenciaDTO.getDestino().getTitular());
			
			if(transferenciaService.verficaSeTransferenciaEhValida(carteiraOrigem.getSaldo(), transferenciaDTO.getValor())) {
				carteiraOrigem.setSaldo(carteiraOrigem.getSaldo() - transferenciaDTO.getValor());
				carteiraDestino.setSaldo(carteiraDestino.getSaldo() + transferenciaDTO.getValor());
				
				salvar(carteiraOrigem);
				salvar(carteiraDestino);
				
				movimentacaoFinanceiraService.registrarMovimentacaoTransferencia(transferenciaDTO, carteiraOrigem);
				movimentacaoFinanceiraService.registrarMovimentacaoTransferencia(transferenciaDTO, carteiraDestino);
				return "Transferência no valor de R$" + transferenciaDTO.getValor() + " de " + transferenciaDTO.getNomeTitularOrigem() +
				" para " + transferenciaDTO.getNomeTitularDestino() + " realizada com sucesso";
			
			}
			return "A carteira de origem não possui o valor desejado para a transferência";
		}
		return "Uma das carteiras informadas não está cadastrada no banco de dados";
	}
	
	public void pagamento(PagamentoDTO pagamento) {
		if(nonNull(getInfosDaCarteira(pagamento.getNomeTitularCarteira()))) {
			Carteira carteira = getInfosDaCarteira(pagamento.getNomeTitularCarteira());
			carteira.setSaldo(carteira.getSaldo() - pagamento.getValor());
			salvar(carteira);
			pagamento.setTipo(TipoMovimentacaoENUM.PAGAMENTO.getId());
			movimentacaoFinanceiraService.registrarMovimentacao(pagamento, carteira);
		}		
		
	}

	private boolean verificaSeTemSaldo(Double saldoCarteira, Double valorSaque) {
		return saldoCarteira- valorSaque >= 0;
	}

	public Carteira getInfosDaCarteira(String nomeTitular) {
		return carteiraRepository.findByTitularNome(nomeTitular);
	}
	
	public void salvar(Carteira carteira) {
		carteiraRepository.save(carteira);		
	}

	private InfoOperacaoBancariaDTO montaJson(String saque) {
		Gson gson = new Gson();
		return gson.fromJson(saque, InfoOperacaoBancariaDTO.class);
	}

	private boolean verificaSeCarteirasExistem(TransferenciaDTO transferenciaDTO) {
		return nonNull(getInfosDaCarteira(transferenciaDTO.getOrigem().getTitular())) 
				&& nonNull(getInfosDaCarteira(transferenciaDTO.getDestino().getTitular()));
		
	}

	private TransferenciaDTO montaJsonTransferencia(String saque) {
		Gson gson = new Gson();
		return gson.fromJson(saque, TransferenciaDTO.class);
	}
}