package br.com.microservice.carteira.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.microservice.carteira.model.MovimentacaoFinanceira;

@Repository
public interface MovimentacaoFinanceiraReposity extends CrudRepository<MovimentacaoFinanceira, Integer>{

	List<MovimentacaoFinanceira> findByUsuarioIdOrderByDataMovimentacaoDesc(Integer idTitular);
	
}
