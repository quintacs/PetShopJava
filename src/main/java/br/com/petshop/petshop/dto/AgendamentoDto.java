package br.com.petshop.petshop.dto;

import java.sql.Timestamp;
import java.util.List;

import br.com.petshop.petshop.modelo.Agendamento;
import br.com.petshop.petshop.modelo.Cliente;
import br.com.petshop.petshop.modelo.Pet;

public class AgendamentoDto  {

	private Long id;
	private String nomeCliente;
	private String enderecoCliente;
	private String telefoneCliente;
	private String celularCliente;
	private String emailCliente;
	private List<Pet> pets;
	
	private Timestamp dataAgendamento;
	
	public AgendamentoDto() {}
	
	public AgendamentoDto(Agendamento agendamento) {
		Cliente cliente = agendamento.getCliente();
		this.id = cliente.getId();
		this.nomeCliente = cliente.getNome();
		this.enderecoCliente = cliente.getEndereco();
		this.telefoneCliente = cliente.getTelefone();
		this.celularCliente = cliente.getCelular();
		this.emailCliente = cliente.getEmail();
		this.pets = cliente.getPets();
		this.dataAgendamento = agendamento.getDataAgendamento();
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public String getCelularCliente() {
		return celularCliente;
	}

	public void setCelularCliente(String celularCliente) {
		this.celularCliente = celularCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public Timestamp getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Timestamp dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
}
