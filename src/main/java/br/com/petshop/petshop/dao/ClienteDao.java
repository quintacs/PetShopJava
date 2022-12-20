package br.com.petshop.petshop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import br.com.petshop.petshop.modelo.Cliente;

public class ClienteDao implements RowMapper<Cliente>{
	
	//static EntityManager em = Persistence.createEntityManagerFactory("petsop").createEntityManager();
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Cliente buscarCliente(long id) {
	
		String sql = "select * from cliente where id = ?";
		
		return jdbcTemplate.queryForObject(sql, Cliente.class);
	}

	public List<Cliente> buscarClientes(){
		
		String sql = "select * from clientes";
		
		List<Cliente> lsClientes = jdbcTemplate.query(sql, new ClienteDao());
		
		return lsClientes;
	}
	
	@Override
	public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Cliente cliente = new Cliente();
		cliente.setId(rs.getLong("id"));
		cliente.setCelular(rs.getString("celular"));
		cliente.setCpf(rs.getString("cpf"));
		cliente.setEmail(rs.getString("email"));
		cliente.setEndereco(rs.getString("endereco"));
		cliente.setNome(rs.getString("nome"));
		cliente.setTelefone(rs.getString("telefone"));
		return  cliente;
	}


}
