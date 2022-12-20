package br.com.petshop.petshop.serviceinterface;

import java.util.List;

import br.com.petshop.petshop.dto.ServicoDto;
import br.com.petshop.petshop.form.ServicoForm;


public interface ServicoServiceInterface {

	public boolean salvar(ServicoForm servicoForm) ;
	public boolean atualizar(Long id, ServicoForm servicoForm) ;
	public boolean deletar(ServicoForm servicoForm);
	public ServicoDto consultar(long id);
	public ServicoDto consultar(ServicoForm servicoForm);
	public List<ServicoDto> listar(long id) ;
	public List<ServicoDto> listar();
}
