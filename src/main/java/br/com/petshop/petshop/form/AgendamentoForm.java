package br.com.petshop.petshop.form;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.petshop.petshop.modelo.Cliente;
import br.com.petshop.petshop.modelo.Servico;
import br.com.petshop.util.DateUtil;

public class AgendamentoForm {

	@NotNull 
	private Cliente cliente;
	
	@NotNull
	@NotEmpty
	private List<Servico> servicos;
	
	@NotNull
	private String dataAgendamento;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setIdServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public String getDataAgendamento() {
		return dataAgendamento;
	}
	
	public Timestamp getDataAgendamentoTimestamp() {

		try {
			return DateUtil.getTimestamp(dataAgendamento);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	
}
