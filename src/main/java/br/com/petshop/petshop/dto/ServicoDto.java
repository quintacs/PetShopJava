package br.com.petshop.petshop.dto;

import br.com.petshop.petshop.modelo.Servico;

public class ServicoDto {

	private Long id;
	private String nome;
	private String descricao;
	
	public ServicoDto() {}
	
	public ServicoDto(Servico servico) {
		super();
		this.id = servico.getId();
		this.nome = servico.getNome();
		this.descricao = servico.getDescricao();
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescicao(String descricao) {
		this.descricao = descricao;
	}
}
