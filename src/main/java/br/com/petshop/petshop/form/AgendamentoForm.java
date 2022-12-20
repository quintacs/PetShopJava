package br.com.petshop.petshop.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AgendamentoForm {

	@NotNull 
	private Long idCliente;
	
	@NotNull
	@NotEmpty
	private List<Long> idServicos;
	
	@NotNull
	private String dataAgendamento;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long cliente) {
		this.idCliente = cliente;
	}

	public List<Long> getIdServicos() {
		return idServicos;
	}

	public void setIdServicos(List<Long> servicos) {
		this.idServicos = servicos;
	}

	public String getDataAgendamento() {
		return dataAgendamento;
	}
	
	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	
}
