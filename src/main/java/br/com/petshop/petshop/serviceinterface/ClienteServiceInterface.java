package br.com.petshop.petshop.serviceinterface;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.petshop.petshop.modelo.Cliente;


@Service
public interface ClienteServiceInterface {

	public boolean salvar(Cliente cliente) ;
	public boolean atualizar(Cliente cliente) ;
	public boolean deletar(Cliente cliente);
	public Cliente consultar(long id);
	public List<Cliente> listar(long id) ;
	public List<Cliente> listar();
}
