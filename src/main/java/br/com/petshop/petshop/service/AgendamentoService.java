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
import br.com.petshop.util.DateUtil;

@Service
public class AgendamentoService implements AgendamentoServiceInterface {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ServicoRepository servicoRepository;

	@Override
	public boolean salvar(AgendamentoForm agendamentoForm) {

		Agendamento agendamento = new Agendamento(agendamentoForm);

		agendamentoRepository.save(agendamento);
		return true;
	}

	@Override
	public boolean deletar(AgendamentoForm agendamentoForm) {

		try {
			Agendamento agendamento = agendamentoRepository
					.consultaAgendamento(DateUtil.getTimestampDMYHMS(agendamentoForm.getDataAgendamento()));

			Optional<Agendamento> optionalAgendamento = agendamentoRepository.findById(agendamento.getId());
			if (optionalAgendamento.isPresent()) {
				// agendamentoRepository.deleteById(agendamento.getId());
				return true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public AgendamentoDto consultar(long id) {

		Agendamento agendamento = agendamentoRepository.consultarAgendamentoId(id);
		if (agendamento != null) {
			return new AgendamentoDto(agendamento);
		}
		return null;
	}

	@Override
	public List<AgendamentoDto> listar(long id) {

		List<AgendamentoDto> listAgendamentoDto = new ArrayList<>();

		agendamentoRepository.consultaAgendamentosCliente(id)
				.forEach(agendamento -> listAgendamentoDto.add(new AgendamentoDto(agendamento)));
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

			if (agendamentoForm.getIdCliente() != 0)
				vAgendamento.setCliente(clienteRepository.getById(agendamentoForm.getIdCliente()));

			if (agendamentoForm.getIdServicos().size() >= 1 && agendamentoForm.getIdServicos().get(0) != 0) {

				List<Servico> lsServicos = new ArrayList<>();

				for (Long idServico : agendamentoForm.getIdServicos()) {

					Servico servico = servicoRepository.getOne(idServico);
					lsServicos.add(servico);
				}

				vAgendamento.setServicos(lsServicos);
			}

			try {
				if (agendamentoForm.getDataAgendamento() != null)
					vAgendamento.setDataAgendamento(DateUtil.getTimestampDMYHMS(agendamentoForm.getDataAgendamento()));
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			agendamentoRepository.save(vAgendamento);

			return true;
		}

		return false;
	}

	@Override
	public AgendamentoDto consultar(AgendamentoForm agendamentoForm) {

		try {
			Agendamento agendamento = agendamentoRepository
					.consultaAgendamento(DateUtil.getTimestampDMYHMS(agendamentoForm.getDataAgendamento()));

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

		} catch (Exception e) {
		}

		return null;
	}
}
