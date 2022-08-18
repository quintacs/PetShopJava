package br.com.petshop.petshop.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.petshop.petshop.dto.ClienteDto;
import br.com.petshop.petshop.form.ClienteForm;
import br.com.petshop.petshop.modelo.Cliente;
import br.com.petshop.petshop.serviceinterface.ClienteServiceInterface;

@RestController 
@RequestMapping("/api/Clientes")
public class ClienteController {

	@Autowired
	private ClienteServiceInterface clienteService;
	
	@Autowired
	private Cliente cliente;
	
	@GetMapping
	public ResponseEntity<List<ClienteDto>> lista() {//@PathVariable long id
		List<ClienteDto> listClienteDto = new ArrayList<ClienteDto>();
		//if (id == 0) {
			List<Cliente> lsCliente = clienteService.listar();
			
			if(!lsCliente.isEmpty()) {
				
				for (Cliente cliente : lsCliente) {
					listClienteDto.add(new ClienteDto(cliente));
				}
				return  ResponseEntity.ok(listClienteDto);
				
			}else {
				
				return ResponseEntity.notFound().build();
			}
			//return clienteService.listar();
		/*} else {
			List<Cliente> lsCliente = clienteService.listar(id);
			for (Cliente cliente : lsCliente) {
				listClienteDto.add(new ClienteDto(cliente));
			}
			return listClienteDto;
			//return clienteService.listar(id);
		}*/
	}
	
	@PostMapping
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
		  cliente = new Cliente(form);
		clienteService.salvar(cliente);
		
		URI uri = uriBuilder.path("/Clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> detalhar(@PathVariable Long id) {
			 cliente = clienteService.consultar(id);
		if (cliente != null) {
			return ResponseEntity.ok(new ClienteDto(cliente));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteForm form) {
		
		cliente = new Cliente(form);
		cliente.setId(id);
		
		if (clienteService.atualizar(cliente) ) {
			return ResponseEntity.ok(new ClienteDto(cliente));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id ) {
		 cliente = clienteService.consultar(id);
		if (cliente != null) {
			//clienteService.deletar(cliente);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
