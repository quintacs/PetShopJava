package br.com.petshop.petshop.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petshop.petshop.modelo.Cliente;
import br.com.petshop.petshop.repository.ClienteRepository;
import br.com.petshop.petshop.serviceinterface.ClienteServiceInterface;

@Service
public class ClienteService implements ClienteServiceInterface{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional
	public boolean salvar(Cliente cliente) {
		clienteRepository.save(cliente);
		return true;
	}
	
	@Transactional
	public boolean atualizar(Cliente cliente) {
		
		Optional<Cliente> optionalCliente = clienteRepository.findById(cliente.getId());
		if (optionalCliente.isPresent()) {
			Cliente vCliente = clienteRepository.getOne(cliente.getId());
			vCliente.setNome(cliente.getNome());
			vCliente.setCpf(cliente.getCpf());
			vCliente.setEmail(cliente.getEmail());
			vCliente.setEndereco(cliente.getEndereco());
			vCliente.setTelefone(cliente.getTelefone());
			vCliente.setCelular(cliente.getCelular());
			clienteRepository.save(vCliente);
			
			return true;
		}
		
		return false;
	}
	
	@Transactional
	public boolean deletar(Cliente cliente) {

		Optional<Cliente> optionalCliente = clienteRepository.findById(cliente.getId());
		if (optionalCliente.isPresent()) {
			clienteRepository.deleteById(cliente.getId());
			return true;
		}
		return false;
	}
	
	public Cliente consultar(long id) {
		
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		if (optionalCliente.isPresent()) {
			Cliente vCliente = clienteRepository.getOne(id);
			return vCliente;
		}
		return null;
	}
	
	public List<Cliente> listar(long id) {
		
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		if (optionalCliente.isPresent()) {
			Cliente vCliente = clienteRepository.getOne(id);
			List<Cliente> clientes = clienteRepository.findByNome(vCliente.getNome());
			return clientes;
		}
		return null;
	}
	
	public List<Cliente> listar(){
		
		return clienteRepository.findAll();
	}
}
