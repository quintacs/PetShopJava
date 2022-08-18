package br.com.petshop.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.petshop.petshop.modelo.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);
	
//	Cliente findByNome(String nome);
	
	
	/*@Modifying
	@Query("UPDATE cliente set nome= :nome, especie = :especie WHERE id = :id")
	void update(@Param("nome") String nome, @Param("especie") String especie, @Param("id") long id);*/
	
	@Modifying
	@Query(value = "DELETE cliente where id = :id" , nativeQuery = true)
	void delete(@Param("id")long id);
}