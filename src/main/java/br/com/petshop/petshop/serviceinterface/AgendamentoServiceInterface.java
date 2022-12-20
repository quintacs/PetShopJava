package br.com.petshop.petshop.serviceinterface;

import java.util.List;

import br.com.petshop.petshop.dto.AgendamentoDto;
import br.com.petshop.petshop.form.AgendamentoForm;


public interface AgendamentoServiceInterface {

		public boolean salvar(AgendamentoForm agendamentoForm) ;
		public boolean atualizar(Long id, AgendamentoForm agendamentoForm) ;
		public boolean deletar(AgendamentoForm agendamentoForm);
		public AgendamentoDto consultar(long id);
		public AgendamentoDto consultar(AgendamentoForm agendamentoForm);
		public List<AgendamentoDto> listar(long id) ;
		public List<AgendamentoDto> listar();

	
}
