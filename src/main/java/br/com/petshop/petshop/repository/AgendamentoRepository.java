package br.com.petshop.petshop.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.petshop.petshop.modelo.Agendamento;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{

	
	@Query(value = "SELECT a.id, a.cliente_id, a.data_agendamento, s.servicos_id, b.nome, b.descricao FROM agendamento a JOIN agendamento_servicos s on s.agendamento_id = a.id JOIN servico b on s.servicos_id = b.id WHERE a.data_agendamento = :dataAgendamento" , nativeQuery = true)
	Agendamento consultaAgendamento( @Param("dataAgendamento")Timestamp dataAgendamento );

	/*@Query(value = "SELECT a.id, a.cliente_id, a.data_agendamento, s.servicos_id, b.nome, b.descricao FROM agendamento a JOIN agendamento_servicos s on s.agendamento_id = a.id JOIN servico b on s.servicos_id = b.id WHERE a.cliente_id = :id " , nativeQuery = true)
	Agendamento consultaAgendamentoCliente(@Param("id") Long id);*/
	
	@Query(value = "SELECT a.id, a.cliente_id, a.data_agendamento, s.servicos_id, b.nome, b.descricao FROM agendamento a JOIN agendamento_servicos s on s.agendamento_id = a.id JOIN servico b on s.servicos_id = b.id WHERE a.id = :idAgendamento" , nativeQuery = true)
	Agendamento consultaAgendamento( @Param("idAgendamento")Long idAgendamento );
	
	@Query(value = "SELECT a.id, a.cliente_id, a.data_agendamento, s.servicos_id, b.nome, b.descricao FROM agendamento a JOIN agendamento_servicos s on s.agendamento_id = a.id JOIN servico b on s.servicos_id = b.id WHERE a.cliente_id = :id " , nativeQuery = true)
	List<Agendamento> consultaAgendamentosCliente(@Param("id") Long id);
	
	@Query(value = "SELECT a.id, a.cliente_id, a.data_agendamento, s.servicos_id, b.nome, b.descricao FROM agendamento a JOIN agendamento_servicos s on s.agendamento_id = a.id JOIN servico b on s.servicos_id = b.id WHERE a.cliente_id = :id  AND a.id = :idAgendamento" , nativeQuery = true)
	List<Agendamento> consultaAgendamentosClientePorData(@Param("id") Long id,  @Param("idAgendamento")Long idAgendamento);
	
	@Query(value = "SELECT a.id, a.data_agendamento, cliente_id FROM agendamento a WHERE a.id = :idAgendamento" , nativeQuery = true)
	Agendamento consultarAgendamentoId(@Param("idAgendamento")Long id);
	
	
}
