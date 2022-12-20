package br.com.petshop.petshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.petshop.petshop.dto.ClienteDto;
import br.com.petshop.petshop.form.ClienteForm;
import br.com.petshop.petshop.modelo.Cliente;
import br.com.petshop.petshop.repository.ClienteRepository;
import br.com.petshop.petshop.serviceinterface.ClienteServiceInterface;

@Service
public class ClienteService implements ClienteServiceInterface{

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public boolean salvar(ClienteForm clienteForm) {
		
		
		clienteRepository.save(new Cliente(clienteForm));
		clienteRepository.flush();
		return true;
	}
	
	
	public boolean deletar(ClienteForm clienteForm) {

		Cliente cliente = clienteRepository.consultaCliente(clienteForm.getCpf(), clienteForm.getNome());
		
		Optional<Cliente> optionalCliente = clienteRepository.findById(cliente.getId());
		if (optionalCliente.isPresent()) {
			//clienteRepository.deleteById(cliente.getId());
			return true;
		}
		return false;
	}
	
	public ClienteDto consultar(long id) {
		
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		if (optionalCliente.isPresent()) {
			Cliente vCliente = clienteRepository.getOne(id);
			return new ClienteDto(vCliente);
		}
		return null;
	}
	
	public List<ClienteDto> listar(String nome) {
		
		Optional<Cliente> optionalCliente = Optional.ofNullable(clienteRepository.consultaClienteNome(nome));
		if (optionalCliente.isPresent()) {
			List<ClienteDto> clientesDto = new ArrayList<ClienteDto>();
			clienteRepository.findByNome(nome).forEach(cliente -> clientesDto.add(new ClienteDto(cliente)));
			return clientesDto;
		}
		return null;
	}
	
	public Page<ClienteDto> listar(int pagina, int quantidade) {
		
		/*if(quantidade == 0) {
			quantidade = 1;
		}
		/*if(pagina == 0 ) {
			pagina = 1;
		}*/
		
		Pageable paginacao = PageRequest.of(pagina, quantidade);
		
		Page<ClienteDto> clienteDto = clienteRepository.findAll(paginacao).map(ClienteDto::new);
		return clienteDto;
	}
	
	public List<ClienteDto> listar(){
		
		List<ClienteDto> clientesDto = new ArrayList<ClienteDto>();
		
		clienteRepository.findAll().forEach(cliente -> clientesDto.add(new ClienteDto(cliente)));
		
		return clientesDto;
	}

	@Override
	public ClienteDto consultar(ClienteForm clienteForm) {
		
		Cliente cliente = clienteRepository.consultaCliente(clienteForm.getCpf(), clienteForm.getNome());

		Optional<Cliente> optionalCliente = clienteRepository.findById(cliente.getId());
		if (optionalCliente.isPresent()) {
			return new ClienteDto(cliente);
		}
		return null;
	}

	@Override
	public boolean atualizar(Long id, ClienteForm clienteForm) {
		
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		if (optionalCliente.isPresent()) {
			Cliente vCliente = clienteRepository.getOne(id);
			vCliente.setNome(clienteForm.getNome());
			vCliente.setCpf(clienteForm.getCpf());
			vCliente.setEmail(clienteForm.getEmail());
			vCliente.setEndereco(clienteForm.getEndereco());
			vCliente.setTelefone(clienteForm.getTelefone());
			vCliente.setCelular(clienteForm.getCelular());
			clienteRepository.save(vCliente);
			
			return true;
		}
		
		return false;
	}

}
