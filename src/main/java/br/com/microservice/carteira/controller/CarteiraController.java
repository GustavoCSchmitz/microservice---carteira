package br.com.microservice.carteira.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microservice.carteira.service.CarteiraService;
import br.com.microservice.carteira.service.MovimentacaoFinanceiraService;

@RestController
public class CarteiraController {

	@Autowired private CarteiraService carteiraService;
	@Autowired private MovimentacaoFinanceiraService movimentacaoFinanceiraService;
	
	//utilizar UUID
	@GetMapping(value="/movimentacoes/{idTitular}", produces="application/json")
	public ResponseEntity<String> movimentacoesUsuario(@PathVariable Integer idTitular) throws IOException{
		try {
			String movimentacoes = movimentacaoFinanceiraService.getMovimentacoesUsuario(idTitular);
			return new ResponseEntity<String>(movimentacoes,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Ocorreu um erro durante a realização do saque",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/saque")
	public ResponseEntity<String> saque(@RequestBody String saque) throws IOException{
		try {
			carteiraService.saque(saque);
			return new ResponseEntity<String>("ok",HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Ocorreu um erro durante a realização do saque",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/deposito")
	public ResponseEntity<String> deposito(@RequestBody String deposito) throws IOException{
		try {
			carteiraService.deposito(deposito);
			return new ResponseEntity<String>("ok",HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Ocorreu um erro durante a realização do saque",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/transferencia")
	public ResponseEntity<String> transferencia(@RequestBody String transferencia) throws IOException{
		try {
			carteiraService.transferencia(transferencia);
			return new ResponseEntity<String>("ok",HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Ocorreu um erro durante a realização do saque",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/pagamento")
	public ResponseEntity<String> pagamento(@RequestBody String pagamento) throws IOException{
		try {
			carteiraService.pagamento(pagamento);
			return new ResponseEntity<String>("ok",HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Ocorreu um erro durante a realização do saque",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}