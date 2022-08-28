package br.com.petshop.petshop.controller;

import java.net.URI;
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

import br.com.petshop.petshop.dto.ServicoDto;
import br.com.petshop.petshop.form.ServicoForm;
import br.com.petshop.petshop.serviceinterface.ServicoServiceInterface;

@RestController
@RequestMapping("/api/Servicos")
public class ServicoController {

	@Autowired
	private ServicoServiceInterface servicoService;
	

	@GetMapping
	public ResponseEntity<List<ServicoDto>> lista() {
		
		List<ServicoDto> listServicoDto  = servicoService.listar();
			
			if(!listServicoDto.isEmpty()) {				
				return  ResponseEntity.ok(listServicoDto);
			}else {				
				return ResponseEntity.notFound().build();
			}
	}
	
	@PostMapping
	public ResponseEntity<ServicoDto> cadastrar(@RequestBody @Valid ServicoForm form, UriComponentsBuilder uriBuilder) {

		servicoService.salvar(form);
		ServicoDto servicoDto = servicoService.consultar(form);

		URI uri = uriBuilder.path("/Clientes/{id}").buildAndExpand(servicoDto.getId()).toUri();
		return ResponseEntity.created(uri).body(servicoDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ServicoDto> detalhar(@PathVariable Long id) {
		ServicoDto servicoDto = servicoService.consultar(id);
		if (servicoDto != null) {
			return ResponseEntity.ok(servicoDto);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ServicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid ServicoForm form) {
		
		if (servicoService.atualizar(id,form) ) {
			return ResponseEntity.ok(servicoService.consultar(form));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id ) {
		ServicoDto servicoDto = servicoService.consultar(id);
		if (servicoDto != null) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
