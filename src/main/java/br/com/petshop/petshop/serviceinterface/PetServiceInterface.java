package br.com.petshop.petshop.serviceinterface;

import java.util.List;

import br.com.petshop.petshop.dto.PetDto;
import br.com.petshop.petshop.form.PetForm;
import br.com.petshop.petshop.modelo.Cliente;



public interface PetServiceInterface {
	
	
	public void salvar(PetForm petForm);
	public void atualizar(Long id, PetForm petForm) ;
	public void deletar(PetForm petForm) ;
	public PetDto consultar(PetForm petForm) ;
	public PetDto consultar(String nome, String especie) ;
	public PetDto consultar(long id) ;
	public List<PetDto> listar(long id);
	public List<PetDto> listar();
	public List<PetDto> listarPorCliente(Cliente cliente) ;
	public List<PetDto> listarPorIdClienteAndNomePet(long id, String nome) ;
}
