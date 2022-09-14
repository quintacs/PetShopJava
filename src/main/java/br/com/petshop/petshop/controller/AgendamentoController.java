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

import br.com.petshop.petshop.dto.AgendamentoDto;
import br.com.petshop.petshop.form.AgendamentoForm;
import br.com.petshop.petshop.serviceinterface.AgendamentoServiceInterface;

@RestController 
@RequestMapping("/api/Agendamentos")
public class AgendamentoController {
	
	@Autowired
	private AgendamentoServiceInterface agendamentoService;
	

	@GetMapping
	public ResponseEntity<List<AgendamentoDto>> listar() {
		
		List<AgendamentoDto> listAgendamentoDto  = agendamentoService.listar();
			
			if(!listAgendamentoDto.isEmpty()) {				
				return  ResponseEntity.ok(listAgendamentoDto);
			}else {				
				return ResponseEntity.notFound().build();
			}
	}
	
	@PostMapping
	public ResponseEntity<AgendamentoDto> cadastrar(@RequestBody @Valid AgendamentoForm form, UriComponentsBuilder uriBuilder) {

		agendamentoService.salvar(form);
		AgendamentoDto agendamentoDto = agendamentoService.consultar(form);

		URI uri = uriBuilder.path("/Agendamentos/{id}").buildAndExpand(agendamentoDto.getId()).toUri();
		return ResponseEntity.created(uri).body(agendamentoDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AgendamentoDto> detalhar(@PathVariable Long id) {
		AgendamentoDto agendamentoDto = agendamentoService.consultar(id);
		if (agendamentoDto != null) {
			return ResponseEntity.ok(agendamentoDto);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AgendamentoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AgendamentoForm form) {
		
		if (agendamentoService.atualizar(id,form) ) {
			return ResponseEntity.ok(agendamentoService.consultar(form));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id ) {
		AgendamentoDto agendamentoDto = agendamentoService.consultar(id);
		if (agendamentoDto != null) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
