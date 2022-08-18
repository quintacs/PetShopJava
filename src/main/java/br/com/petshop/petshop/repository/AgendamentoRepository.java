package br.com.petshop.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petshop.petshop.modelo.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

}
