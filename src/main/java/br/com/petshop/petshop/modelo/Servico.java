package br.com.petshop.petshop.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.petshop.petshop.form.ServicoForm;

@Entity
public class Servico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	
	public Servico() {}
	
	public Servico(ServicoForm servicoForm) {
		this.nome = servicoForm.getNome();
		this.descricao = servicoForm.getDescricao();
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
