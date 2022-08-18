package br.com.petshop.petshop.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petshop.petshop.modelo.Cliente;
import br.com.petshop.petshop.modelo.Pet;
import br.com.petshop.petshop.repository.PetRepository;
import br.com.petshop.petshop.serviceinterface.PetServiceInterface;

@Service
public class PetService implements PetServiceInterface{

	@Autowired
	private PetRepository petRepository;
	
	@Transactional
	public void salvar(Pet pet) {
		
		petRepository.save(pet);
	}
	
	@Transactional
	public void atualizar(Pet pet) {
		
		Optional<Pet> optionalPet = petRepository.findById(pet.getId());
		if (optionalPet.isPresent()) {
			petRepository.update(pet.getNome(),pet.getEspecie(),pet.getId());
		}
	}
	
	@Transactional
	public void deletar(Pet pet) {

		Optional<Pet> optionalPet = petRepository.findById(pet.getId());
		if (optionalPet.isPresent()) {
			petRepository.deleteById(pet.getId());
		}
	}
	
	public Pet consultar(long id) {
		
		Optional<Pet> optionalPet = petRepository.findById(id);
		if (optionalPet.isPresent()) {
			Pet vPet = petRepository.getOne(id);
			return vPet;
		}
		return null;
	}
	
	public List<Pet> listar(long id) {
		
		Optional<Pet> optionalPet = petRepository.findById(id);
		if (optionalPet.isPresent()) {
			Pet vPet = petRepository.getOne(id);
			List<Pet> pets = petRepository.findByNome(vPet.getNome());
			return pets;
		}
		return null;
	}
	
	public List<Pet> listar(){
		
		return petRepository.findAll();
	}
	
	public List<Pet> listarPorCliente(Cliente cliente) {
		
		List<Pet> pets = petRepository.findByCliente(cliente);
		if (!pets.isEmpty()) {
			return pets;
		}
		return null;
	}
	
	public List<Pet> listarPorIdClienteAndNomePet(long id, String nome) {
		
		List<Pet> pets = petRepository.findByIdClienteAndNomePet(id, nome);
		if (!pets.isEmpty()) {
			return pets;
		}
		return null;
	}
}
