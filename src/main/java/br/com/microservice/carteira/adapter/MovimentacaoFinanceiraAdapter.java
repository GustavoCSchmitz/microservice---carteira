package br.com.microservice.carteira.adapter;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import static java.util.Objects.nonNull;
import br.com.microservice.carteira.constantes.TipoMovimentacaoENUM;
import br.com.microservice.carteira.model.MovimentacaoFinanceira;

public class MovimentacaoFinanceiraAdapter implements JsonSerializer<MovimentacaoFinanceira>{

	@Override
	public JsonElement serialize(MovimentacaoFinanceira movimentacaoFinanceira, Type typeOfSrc, JsonSerializationContext context) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("id", new JsonPrimitive(movimentacaoFinanceira.getId()));
        jsonObject.addProperty("tipo", getTipoMovimentacao(movimentacaoFinanceira));
        jsonObject.addProperty("valor", movimentacaoFinanceira.getValor());
		jsonObject.addProperty("dataMovimentacao", movimentacaoFinanceira.getDataMovimentacao().toString());
		return jsonObject;

	}
	
	private String getTipoMovimentacao(MovimentacaoFinanceira movimentacaoFinanceira) {
		String tipo = "";
		if(nonNull(movimentacaoFinanceira.getTipo())) {
			switch(movimentacaoFinanceira.getTipo()) {
				case "S":
					tipo = TipoMovimentacaoENUM.SAQUE.getDescricao();
					break;
				case "D":
					tipo = TipoMovimentacaoENUM.DEPOSITO.getDescricao();
					break;
				case "TE":
					tipo = TipoMovimentacaoENUM.TRANSFERENCIA_ENTRADA.getDescricao();
					break;
				case "TS":
					tipo = TipoMovimentacaoENUM.TRANSFERENCIA_SAIDA.getDescricao();
					break;
				case "P":
					tipo = TipoMovimentacaoENUM.PAGAMENTO.getDescricao();
					break;
				default:
					break;								
			}
		}
		return tipo;
	}	

}
