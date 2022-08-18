package br.com.petshop.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petshop.petshop.modelo.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
