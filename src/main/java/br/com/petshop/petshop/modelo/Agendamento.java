package br.com.petshop.petshop.modelo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import br.com.petshop.petshop.form.AgendamentoForm;
import br.com.petshop.util.DateUtil;

@Entity
public class Agendamento {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name = "data_agendamento")
	private Timestamp dataAgendamento;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToMany
	@JoinColumn(name = "servico_id")
	private List<Servico> servicos;
	
	public Agendamento() {}
	
	public Agendamento(AgendamentoForm agendamentoForm) {

				parse(agendamentoForm);
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	
	private void parse(AgendamentoForm agendamentoForm) {
		
		try {
			
			this.cliente = new Cliente();
			cliente.setId(agendamentoForm.getIdCliente());
			
			this.servicos = new ArrayList<>();
			for(Long idServico: agendamentoForm.getIdServicos()) {
				Servico servico = new Servico();
				servico.setId(idServico);
				servicos.add(servico);
			}
			this.dataAgendamento = DateUtil.getTimestampDMYHMS(agendamentoForm.getDataAgendamento());

			
		}catch (Exception e) {
			System.out.println("erro "+e.getMessage());
		}
	}
}
