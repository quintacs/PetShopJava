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

import br.com.petshop.petshop.dto.PetDto;
import br.com.petshop.petshop.form.PetForm;
import br.com.petshop.petshop.modelo.Pet;
import br.com.petshop.petshop.service.ClienteService;
import br.com.petshop.petshop.serviceinterface.PetServiceInterface;

@RestController
@RequestMapping("/api/Pets")
public class PetController {

	@Autowired
	private PetServiceInterface petService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private Pet pet;
	
	@GetMapping
	public ResponseEntity<List<PetDto>> lista() {//@RequestParam long id
		List<PetDto> listPetDto = new ArrayList<PetDto>();
		List<Pet> lsPet = petService.listar();
		if(!lsPet.isEmpty()) {
			for (Pet pet : lsPet) {
				listPetDto.add(new PetDto(pet));
			}
			return  ResponseEntity.ok(listPetDto);
		}else {
			
			return ResponseEntity.notFound().build();
		}
		
		/*if (id == 0) {
			List<Pet> lsPet = petService.listar();
			for (Pet pet : lsPet) {
				listPetDto.add(new PetDto(pet));
			}
			return listPetDto;
		} else {
			List<Pet> lsPet = petService.listar(id);
			for (Pet pet : lsPet) {
				listPetDto.add(new PetDto(pet));
			}
			return listPetDto;
		}*/
	}
	
	@PostMapping
	public ResponseEntity<PetDto> cadastrar(@RequestBody @Valid PetForm form, UriComponentsBuilder uriBuilder) {
		
	    pet = new Pet(form);
	    
	    pet.setCliente(clienteService.consultar(form.getIdCliente()));
	    
		petService.salvar(pet);
		
		URI uri = uriBuilder.path("/pets/{id}").buildAndExpand(pet.getId()).toUri();
		return ResponseEntity.created(uri).body(new PetDto(pet));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PetDto> detalhar(@PathVariable Long id) {
			 pet = petService.consultar(id);
		if (pet != null) {
			return ResponseEntity.ok(new PetDto(pet));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PetDto> atualizar(@PathVariable Long id, @RequestBody @Valid PetForm form) {
		
		 pet = petService.consultar(id);
		if (pet != null) {
			pet.setNome(form.getNome());
			pet.setEspecie(form.getEspecie());
			petService.atualizar(pet);
			return ResponseEntity.ok(new PetDto(pet));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id ) {
		 pet = petService.consultar(id);
		if (pet != null) {
			//petService.deletar(pet);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
}
