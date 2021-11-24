package br.com.microservice.carteira.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.microservice.carteira.constantes.RabbitmqConstantes;
import br.com.microservice.carteira.dto.PagamentoDTO;
import br.com.microservice.carteira.service.CarteiraService;

@Component
public class CarteiraConsumer {
	
	@Autowired private CarteiraService carteiraService;
	
	@RabbitListener(queues = RabbitmqConstantes.FILA_PAGAMENTO)
	private void consumidor(String pagamento) {
		Gson gson = new Gson();
		PagamentoDTO pagamentoDTO = gson.fromJson(pagamento,PagamentoDTO.class);
		try {
			carteiraService.pagamento(pagamentoDTO);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
