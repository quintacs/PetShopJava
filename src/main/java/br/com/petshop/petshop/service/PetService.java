package br.com.petshop.petshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petshop.petshop.dto.PetDto;
import br.com.petshop.petshop.form.PetForm;
import br.com.petshop.petshop.modelo.Cliente;
import br.com.petshop.petshop.modelo.Pet;
import br.com.petshop.petshop.repository.PetRepository;
import br.com.petshop.petshop.serviceinterface.PetServiceInterface;

@Service
public class PetService implements PetServiceInterface{

	@Autowired
	private PetRepository petRepository;
	
	
	public void salvar(PetForm petForm) {
		
		petRepository.save(new Pet(petForm));
	}
	
	public void deletar(PetForm petForm) {

		Pet pet = petRepository.buscaPorIdClienteAndNomePet(petForm.getIdCliente(), petForm.getNome());
				
		Optional<Pet> optionalPet = petRepository.findById(pet.getId());
		if (optionalPet.isPresent()) {
			petRepository.deleteById(pet.getId());
		}
	}
	
	public PetDto consultar(long id) {
		
		Optional<Pet> optionalPet = petRepository.findById(id);
		if (optionalPet.isPresent()) {
			return new PetDto(petRepository.getOne(id));
		}
		return null;
	}
	
	public List<PetDto> listar(long id) {
		
		Optional<Pet> optionalPet = petRepository.findById(id);
		if (optionalPet.isPresent()) {
			
			List<PetDto> pets = new ArrayList<PetDto>();
			
			petRepository.findByClienteId(id).forEach(pet -> pets.add(new PetDto(pet)));;
			
			return pets;
		}
		return null;
	}
	
	public List<PetDto> listar(){
		
		List<PetDto> pets = new ArrayList<PetDto>();
		
		petRepository.findAll().forEach(pet -> pets.add(new PetDto(pet)));;
		
		return pets;
	}
	
	public List<PetDto> listarPorCliente(Cliente cliente) {
		
		List<Pet> pets = petRepository.findByCliente(cliente);
		if (!pets.isEmpty()) {
			
			List<PetDto> petDto = new ArrayList<PetDto>();
			
			pets.forEach(pet -> petDto.add(new PetDto(pet)));
			
			return petDto;
		}
		return null;
	}
	
	public List<PetDto> listarPorIdClienteAndNomePet(long id, String nome) {

		List<Pet> pets = petRepository.findByIdClienteAndNomePet(id, nome);
		if (!pets.isEmpty()) {
			List<PetDto> petDto = new ArrayList<PetDto>();

			pets.forEach(pet -> petDto.add(new PetDto(pet)));

			return petDto;
		}
		return null;
	}

	@Override
	public PetDto consultar(PetForm petForm) {
		
		Pet pet = petRepository.consultarPet(petForm.getNome(), petForm.getEspecie());
		
		Optional<Pet> optionalPet = petRepository.findById(pet.getId());
		if (optionalPet.isPresent()) {
			return new PetDto(pet);
		}
		return null;
	}

	@Override
	public PetDto consultar(String nome, String especie) {
		Pet pet = petRepository.consultarPet(nome, especie);

		Optional<Pet> optionalPet = petRepository.findById(pet.getId());
		if (optionalPet.isPresent()) {
			return new PetDto(pet);
		}
		return null;
	}

	@Override
	public void atualizar(Long id, PetForm petForm) {
		
		Optional<Pet> optionalPet = petRepository.findById(id);
		if (optionalPet.isPresent()) {
			Pet pet = petRepository.getOne(id);
			pet.setNome(petForm.getNome());
			pet.setEspecie(petForm.getEspecie());
			pet.setRaca(petForm.getRaca());
			pet.setId(id);
			petRepository.save(pet);
		}
	}
}
