package br.com.petshop.petshop.serviceinterface;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.petshop.petshop.dto.ClienteDto;
import br.com.petshop.petshop.form.ClienteForm;



public interface ClienteServiceInterface {

	 boolean salvar(ClienteForm clienteForm) ;
	 boolean atualizar(Long id, ClienteForm clienteForm) ;
	 boolean deletar(ClienteForm clienteForm);
	 ClienteDto consultar(long id);
	 List<ClienteDto> listar(String nome) ;
	 List<ClienteDto> listar();
	 Page<ClienteDto> listar(int pagina, int quantidade);
	 ClienteDto consultar(ClienteForm clienteForm);
}
