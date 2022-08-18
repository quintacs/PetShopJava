package br.com.petshop.petshop.dto;

import br.com.petshop.petshop.modelo.Pet;

public class PetDto {

	private Long idCliente;
	private Long id;
	private String nome;
	private String nomeCliente;
	private String especie;
	
	
	public PetDto(Pet pet) {
		this.id = pet.getId();
		this.nome =pet.getNome();
		this.nomeCliente = pet.getCliente().getNome();
		this.idCliente = pet.getCliente().getId();
		this.especie = pet.getEspecie();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setCliente(String nome) {
		this.nomeCliente = nome;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
	public String getEspecie() {
		return this.especie;
	}
}
