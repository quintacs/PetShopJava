package br.com.petshop.petshop.dto;

import br.com.petshop.petshop.modelo.Servico;

public class ServicoDto {

	private Long id;
	private String nome;
	private String descicao;
	
	public ServicoDto() {}
	
	public ServicoDto(Servico servico) {
		super();
		this.id = servico.getId();
		this.nome = servico.getNome();
		this.descicao = servico.getDescicao();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescicao() {
		return descicao;
	}
	public void setDescicao(String descicao) {
		this.descicao = descicao;
	}
}
