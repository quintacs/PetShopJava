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

import br.com.petshop.petshop.dto.PetDto;
import br.com.petshop.petshop.form.PetForm;
import br.com.petshop.petshop.serviceinterface.PetServiceInterface;

@RestController
@RequestMapping("/api/Pets")
public class PetController {

	@Autowired
	private PetServiceInterface petService;
	
	@GetMapping
	public ResponseEntity<List<PetDto>> lista() {
		
		List<PetDto> listPetDto = petService.listar();
		
		if(!listPetDto.isEmpty()) {
			
			return  ResponseEntity.ok(listPetDto);
		}else {
			
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<PetDto> cadastrar(@RequestBody @Valid PetForm form, UriComponentsBuilder uriBuilder) {
		
		petService.salvar(form);
		PetDto petDto = petService.consultar(form);
		
		URI uri = uriBuilder.path("/pets/{id}").buildAndExpand(petDto.getId()).toUri();
		return ResponseEntity.created(uri).body(petDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PetDto> detalhar(@PathVariable Long id) {
		PetDto pet = petService.consultar(id);
		if (pet != null) {
			return ResponseEntity.ok(pet);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PetDto> atualizar(@PathVariable Long id, @RequestBody @Valid PetForm form) {
		
		PetDto pet = petService.consultar(id);
		if (pet != null) {
			petService.atualizar(id,form);
			return ResponseEntity.ok(pet);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id ) {
		PetDto pet = petService.consultar(id);
		if (pet != null) {
			//petService.deletar(pet);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
}
