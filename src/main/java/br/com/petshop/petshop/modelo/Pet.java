package br.com.petshop.petshop.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import br.com.petshop.petshop.form.PetForm;

@Component
@Entity
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String especie;
	
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Pet() {}
	
	public Pet(PetForm form) {
		//this.cliente.setId(form.getIdCliente());
		this.setNome(form.getNome());
		this.setEspecie(form.getEspecie());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
	public String getEspecie() {
		return this.especie;
	}
}
