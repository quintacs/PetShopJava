package br.com.petshop.petshop.serviceinterface;

import java.util.List;

import br.com.petshop.petshop.modelo.Cliente;
import br.com.petshop.petshop.modelo.Pet;


public interface PetServiceInterface {
	
	
	public void salvar(Pet pet);
	public void atualizar(Pet pet) ;
	public void deletar(Pet pet) ;
	public Pet consultar(long id) ;
	public List<Pet> listar(long id);
	public List<Pet> listar();
	public List<Pet> listarPorCliente(Cliente cliente) ;
	public List<Pet> listarPorIdClienteAndNomePet(long id, String nome) ;
}
