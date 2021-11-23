package br.com.microservice.carteira.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.microservice.carteira.model.Carteira;

@Repository
public interface CarteiraRepository extends CrudRepository<Carteira, Integer>{

	Carteira findByTitularNome(String nomeTitular);
}