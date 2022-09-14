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

	public List<Long> getServicos() {
		return idServicos;
	}

	public void setIdServicos(List<Long> servicos) {
		this.idServicos = servicos;
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
