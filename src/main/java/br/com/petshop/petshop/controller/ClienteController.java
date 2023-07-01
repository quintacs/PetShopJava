package br.com.petshop.petshop.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.petshop.petshop.dto.ClienteDto;
import br.com.petshop.petshop.form.ClienteForm;
import br.com.petshop.petshop.form.validate.ClienteException;
import br.com.petshop.petshop.serviceinterface.ClienteServiceInterface;

@RestController 
@RequestMapping("/api/Clientes")
public class ClienteController {

	@Autowired
	private ClienteServiceInterface clienteService;
	

	@GetMapping(path = "/listaPaginada")
	public ResponseEntity<Page<ClienteDto>>  listar(@RequestParam int pagina, @RequestParam int quantidade) throws Exception {
		
		   if(quantidade < 1 ) {
			   throw new ClienteException("A quantidade não pode ser menor que um ");
			}
			
			Page<ClienteDto> listClienteDto  = clienteService.listar (pagina, quantidade);
				
				if(!listClienteDto.isEmpty()) {				
					return  ResponseEntity.ok(listClienteDto);
				}
				
		
		  return ResponseEntity.notFound().build();
	}
	
	@GetMapping(path = "/lista")
	public ResponseEntity<List<ClienteDto>> lista(){
		
		return ResponseEntity.ok(clienteService.listar()) ;
	}
	
	@PostMapping
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {

		clienteService.salvar(form);
		ClienteDto clienteDto = clienteService.consultar(form);

		URI uri = uriBuilder.path("/Clientes/{id}").buildAndExpand(clienteDto.getId()).toUri();
		return ResponseEntity.created(uri).body(clienteDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> detalhar(@PathVariable Long id) {
		ClienteDto clienteDto = clienteService.consultar(id);
		if (clienteDto != null) {
			return ResponseEntity.ok(clienteDto);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDto> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteForm form) {
		
		if (clienteService.atualizar(id,form) ) {
			return ResponseEntity.ok(clienteService.consultar(form));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id ) {
		ClienteDto clienteDto = clienteService.consultar(id);
		if (clienteDto != null) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
