package com.api.spring.doc.app.canal.daoImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.spring.doc.app.canal.dao.ClienteDao;
import com.api.spring.doc.app.canal.entity.Cliente;
import com.api.spring.doc.app.canal.repository.ClienteRepository;

@Repository
public class ClienteDaoImpl implements ClienteDao {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Optional<Cliente> buscaClienteid(Long idCliente) {

		Optional<Cliente> ce = clienteRepository.findById(idCliente);
		return ce;
	}

	@Override
	public Cliente gravaCliente(Cliente clienteObj) {

		Cliente clienteisSalvo = clienteRepository.save(clienteObj);
		return clienteisSalvo;
	}

	@Override
	public Cliente atualizaCliente(Cliente clienteObj) {

		Cliente clienteisSalvo = clienteRepository.save(clienteObj);
		return clienteisSalvo;
	}
	
	@Override
	public void deleteCliente(Long clienteId) {

		 clienteRepository.deleteById(clienteId);
	}

	@Override
	public Optional<Cliente> existeCliente(Long clienteId) {

		 Optional<Cliente> ce = clienteRepository.findById(clienteId);
		 
		 return ce;
		 
	}

}
