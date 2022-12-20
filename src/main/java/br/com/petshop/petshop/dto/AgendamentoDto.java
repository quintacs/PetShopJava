package br.com.petshop.petshop.dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import br.com.petshop.petshop.modelo.Agendamento;
import br.com.petshop.petshop.modelo.Cliente;
import br.com.petshop.petshop.modelo.Servico;
import br.com.petshop.util.DateUtil;

public class AgendamentoDto  {

	
	private Long id;
	private Timestamp dataAgendamento;
	
	private String dataAgendamentoFormatada;
	
	private Long idCliente;
	private String nomeCliente;
	private String telefoneCliente;
	private String celularCliente;
	private String emailCliente;
	
	
	private List<Servico> servicos; 
	
	public AgendamentoDto() {}
	
	public AgendamentoDto(Agendamento agendamento) {
		parse(agendamento);
	}


	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Timestamp dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	
	public String getDataAgendamentoFormatada() {
		return dataAgendamentoFormatada;
	}

	public void setDataAgendamentoFormatada(String dataAgendamentoFormatada) {
		this.dataAgendamentoFormatada = dataAgendamentoFormatada;
	}
	
	
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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
	
	private void parse(Agendamento agendamento) {
		
		try {
			
			this.id = agendamento.getId();
			this.dataAgendamento = agendamento.getDataAgendamento();
			
			this.dataAgendamentoFormatada = DateUtil.formatDMYHHMMSS(new Date(dataAgendamento.getTime()));
			
			if(agendamento.getCliente()!=null && agendamento.getCliente().getId() != 0) {
				
				Cliente cli = agendamento.getCliente();
				
				this.idCliente = cli.getId();
				this.nomeCliente = cli.getNome();
				this.telefoneCliente = cli.getTelefone();
				this.celularCliente = cli.getCelular();
				this.emailCliente = cli.getEmail();
			}
			if(agendamento.getServicos() != null)
				this.servicos =agendamento.getServicos();
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
