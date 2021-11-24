package br.com.microservice.carteira.service;

import static java.util.Objects.nonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.microservice.carteira.dto.UsuarioDTO;
import br.com.microservice.carteira.model.Carteira;
import br.com.microservice.carteira.model.Usuario;
import br.com.microservice.carteira.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired private CarteiraService carteiraService;
	@Autowired private UsuarioRepository usuarioRepository;

	public Integer cadastrar(String usuarioString) {
		UsuarioDTO usuarioDTO = montaJson(usuarioString);
		if(nonNull(usuarioDTO.getNome())) {
			Usuario usuario = new Usuario(usuarioDTO);
			salvar(usuario);
			Carteira carteira = new Carteira(usuarioDTO,usuario.getId());
			carteiraService.salvar(carteira);
			return usuario.getId();
		}
		return null;
	}
	
	private void salvar(Usuario usuario) {
		usuarioRepository.save(usuario);	
	}

	private UsuarioDTO montaJson(String usuario) {
		Gson gson = new Gson();
		return gson.fromJson(usuario, UsuarioDTO.class);
	}

}
