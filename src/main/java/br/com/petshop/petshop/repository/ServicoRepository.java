package br.com.petshop.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petshop.petshop.modelo.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

	Servico findByNome(String Nome);
}
