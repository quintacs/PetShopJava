package br.com.petshop.petshop.serviceinterface;

import java.util.List;

import br.com.petshop.petshop.dto.ClienteDto;
import br.com.petshop.petshop.form.ClienteForm;



public interface ClienteServiceInterface {

	public boolean salvar(ClienteForm clienteForm) ;
	public boolean atualizar(Long id, ClienteForm clienteForm) ;
	public boolean deletar(ClienteForm clienteForm);
	public ClienteDto consultar(long id);
	public List<ClienteDto> listar(String nome) ;
	public List<ClienteDto> listar();
	public ClienteDto consultar(ClienteForm clienteForm);
}
