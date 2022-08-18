package br.com.petshop.petshop.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

@Component
public class PetForm {

	@NotNull 
	private Long idCliente;
	
	
	@NotNull @NotEmpty @Length(min = 5 , max = 100)
	private String nome;

	private String especie;
	
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
	
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
	public String getEspecie() {
		return this.especie;
	}
}
