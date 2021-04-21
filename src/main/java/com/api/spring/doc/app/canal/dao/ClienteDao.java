package com.api.spring.doc.app.canal.dao;

import java.util.List;
import java.util.Optional;

import com.api.spring.doc.app.canal.entity.Cliente;

public interface ClienteDao {

	Optional<Cliente> buscaClienteid(Long idCliente);

	List<Cliente> buscaTodosCliente();

	Cliente gravaCliente(Cliente clienteObj);
	
	Cliente atualizaCliente(Cliente Cliente);
	
	void deleteCliente(Long clienteId);
	
	Optional<Cliente> existeCliente(Long clienteId);
}
