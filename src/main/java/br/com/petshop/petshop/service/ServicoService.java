package br.com.petshop.petshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petshop.petshop.dto.ServicoDto;
import br.com.petshop.petshop.form.ServicoForm;
import br.com.petshop.petshop.modelo.Servico;
import br.com.petshop.petshop.repository.ServicoRepository;
import br.com.petshop.petshop.serviceinterface.ServicoServiceInterface;

@Service
public class ServicoService implements ServicoServiceInterface{

	@Autowired
	private ServicoRepository servicoRepository;

	@Override
	public boolean salvar(ServicoForm servicoForm) {
		
		servicoRepository.save(new Servico(servicoForm));
		
		return true;
	}

	@Override
	public boolean atualizar(Long id, ServicoForm servicoForm) {
		
		Optional<Servico> optionalServico = servicoRepository.findById(id);
		if (optionalServico.isPresent()) {
			Servico servico = servicoRepository.getOne(id);
			servico.setNome(servicoForm.getNome());
			servico.setDescicao(servicoForm.getDescicao());
			servicoRepository.save(servico);
		}
		return true;
	}

	@Override
	public boolean deletar(ServicoForm servicoForm) {
		
		Optional<Servico> optionalServico = Optional.ofNullable(servicoRepository.findByNome(servicoForm.getNome()));
		if (optionalServico.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public ServicoDto consultar(long id) {
		
		Optional<Servico> optionalServico = servicoRepository.findById(id);
		if (optionalServico.isPresent()) {
			Servico servico = servicoRepository.getOne(id);
			return new ServicoDto(servico);
		}
		return null;
	}

	@Override
	public ServicoDto consultar(ServicoForm servicoForm) {
	
		Servico servico = servicoRepository.findByNome(servicoForm.getNome());
		if(servico !=null ) {
			return new ServicoDto(servico);
		}
		
		return null;
	}

	@Override
	public List<ServicoDto> listar(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ServicoDto> listar() {
		
		List<ServicoDto> lsServicoDto = new ArrayList<>();
		
		servicoRepository.findAll().forEach(servico -> lsServicoDto.add(new ServicoDto(servico)));
		
		return lsServicoDto;
	}
}
