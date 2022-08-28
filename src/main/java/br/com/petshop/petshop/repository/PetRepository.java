package br.com.petshop.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.petshop.petshop.modelo.Cliente;
import br.com.petshop.petshop.modelo.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long>{

	List<Pet> findByNome(String nome);
	
	List<Pet> findByCliente(Cliente cliente);
	
	List<Pet> findByEspecie(String especie);
	
	@Query(value = "SELECT p FROM pet p  WHERE p.cliente_id = :id AND p.nome = :nome" , nativeQuery = true)
	List<Pet> findByIdClienteAndNomePet( @Param("id") long id, @Param("nome") String nome);

	@Query(value = "SELECT p FROM pet p  WHERE p.cliente_id = :id AND p.nome = :nome" , nativeQuery = true)
	Pet buscaPorIdClienteAndNomePet( @Param("id") long id, @Param("nome") String nome);
	
	@Modifying
	@Query(value = "UPDATE pet set nome= :nome, especie = :especie WHERE id = :id" , nativeQuery = true)
	void update(@Param("nome") String nome, @Param("especie") String especie, @Param("id") long id);
	
	@Modifying
	@Query(value = "DELETE pet where id = :id", nativeQuery = true)
	void delete(@Param("id")long id);
	
	@Query(value = "SELECT p FROM pet p  WHERE p.nome = :nome AND p.especie = :especie" , nativeQuery = true)
	Pet consultarPet( @Param("nome") String nome, @Param("especie") String especie);
	
	@Query(value = "SELECT p FROM pet p  WHERE p.nome = :nome AND p.especie = :especie" , nativeQuery = true)
	List<Pet> listarPet( @Param("nome") String nome, @Param("especie") String especie);

	List<Pet> findByClienteId(long id);
	
}
