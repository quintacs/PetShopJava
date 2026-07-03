package br.com.petshop.petshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.petshop.petshop.modelo.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);
	
	@Query(value = "SELECT c.id, c.nome, c.cpf, c.endereco, c.telefone, c.celular, c.email FROM cliente c  WHERE c.cpf = :cpf AND c.nome = :nome" , nativeQuery = true)
	 Cliente consultaCliente(@Param("cpf") String cpf, @Param("nome") String nome);
	
	@Query(value = "SELECT c.id, c.nome, c.cpf, c.endereco, c.telefone, c.celular, c.email FROM cliente c  WHERE c.nome = :nome" , nativeQuery = true)
	Cliente consultaClienteNome( @Param("nome") String nome);
	
	public default Cliente consultaClienteCpfNome(String cpf, String nome) {
		
		Cliente cliente = this.consultaCliente(cpf, nome);
		
		if(cliente  == null) {
			throw new IllegalArgumentException("Id não existente no banco de dados");
		}
		return cliente;
	}
	
//	Cliente findByNome(String nome);
	
	
	/*@Modifying
	@Query("UPDATE cliente set nome= :nome, especie = :especie WHERE id = :id")
	void update(@Param("nome") String nome, @Param("especie") String especie, @Param("id") long id);*/
	
	@Modifying
	@Query(value = "DELETE cliente where id = :id" , nativeQuery = true)
	void delete(@Param("id")long id);
	
	public default  Optional<Cliente> findBy(Long id) {
		
		Optional<Cliente> cliente = this.findById(id);
		
		if(cliente.isPresent()) {
			throw new IllegalArgumentException("Id não existente no banco de dados");
		}
		return cliente;
	}
	
	public default  Cliente getBy(Long id) {
		
		Cliente cliente = this.getById(id);
		
		if(cliente  == null) {
			throw new IllegalArgumentException("Id não existente no banco de dados");
		}
		return cliente;
	}
}
