package br.com.petshop.petshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petshop.petshop.dto.AgendamentoDto;
import br.com.petshop.petshop.form.AgendamentoForm;
import br.com.petshop.petshop.modelo.Agendamento;
import br.com.petshop.petshop.modelo.Servico;
import br.com.petshop.petshop.repository.AgendamentoRepository;
import br.com.petshop.petshop.repository.ClienteRepository;
import br.com.petshop.petshop.repository.ServicoRepository;
import br.com.petshop.petshop.serviceinterface.AgendamentoServiceInterface;

@Service
public class AgendamentoService implements AgendamentoServiceInterface{

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Override
	public boolean salvar(AgendamentoForm agendamentoForm) {
		
		agendamentoRepository.save(new Agendamento(agendamentoForm));
		return true;
	}

	@Override
	public boolean deletar(AgendamentoForm agendamentoForm) {
		
		Agendamento agendamento = agendamentoRepository.consultaAgendamentoCliente(agendamentoForm.getIdCliente());
		
		Optional<Agendamento> optionalAgendamento = agendamentoRepository.findById(agendamento.getId());
		if (optionalAgendamento.isPresent()) {
			//agendamentoRepository.deleteById(agendamento.getId());
			return true;
		}
		return false;
		
	}

	@Override
	public AgendamentoDto consultar(long id) {
		
		Optional<Agendamento> optionalAgendamento = agendamentoRepository.findById(id);
		if (optionalAgendamento.isPresent()) {
			 Agendamento agendamento = agendamentoRepository.getOne(id);
			 return new AgendamentoDto(agendamento);
		}
		return null;
	}

	@Override
	public List<AgendamentoDto> listar(long id) {
		
		List<AgendamentoDto> listAgendamentoDto = new ArrayList<>();
		
		agendamentoRepository.consultaAgendamentosCliente(id).forEach(agendamento -> 
																listAgendamentoDto.add(
																		new AgendamentoDto(agendamento)));
		return listAgendamentoDto;
	}

	@Override
	public List<AgendamentoDto> listar() {

		List<AgendamentoDto> listAgendamentoDto = new ArrayList<>();

		agendamentoRepository.findAll().forEach(agendamento -> listAgendamentoDto.add(new AgendamentoDto(agendamento)));
		return listAgendamentoDto;

	}

	@Override
	public boolean atualizar(Long id, AgendamentoForm agendamentoForm) {
		
		Optional<Agendamento> optionalAgendamento = agendamentoRepository.findById(id);
		if (optionalAgendamento.isPresent()) {
			Agendamento vAgendamento = agendamentoRepository.getOne(id);
			vAgendamento.setCliente(clienteRepository.getById(agendamentoForm.getIdCliente()));
			
			List<Servico> lsServicos = new ArrayList<>();
			for(Long idServico: agendamentoForm.getServicos()) {
				lsServicos.add(servicoRepository.getOne(idServico));
			}
			vAgendamento.setServicos(lsServicos);
			
			vAgendamento.setDataAgendamento(agendamentoForm.getDataAgendamentoTimestamp());
			agendamentoRepository.save(vAgendamento);
			
			return true;
		}
		
		return false;
	}

	@Override
	public AgendamentoDto consultar(AgendamentoForm agendamentoForm) {
		
		Agendamento agendamento = agendamentoRepository
				.consultaAgendamentoCliente(agendamentoForm.getIdCliente());

		if (agendamento != null) {

			/*
			 * Agendamento vAgendamento = agendamentoRepository.getOne(agendamento.getId());
			 * vAgendamento.setCliente(agendamentoForm.getCliente());
			 * vAgendamento.setServicos(agendamentoForm.getServicos());
			 * vAgendamento.setDataAgendamento(agendamentoForm.getDataAgendamentoTimestamp()
			 * ); agendamentoRepository.save(vAgendamento);
			 */

			return new AgendamentoDto(agendamento);
		}

		return null;
	}
}
