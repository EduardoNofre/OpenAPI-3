package com.api.spring.doc.app.canal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.spring.doc.app.canal.dao.ClienteDao;
import com.api.spring.doc.app.canal.entity.Cliente;

@Service
public class ClienteService {

	@Autowired
	private ClienteDao clienteDao;

	public Cliente busca(long Id) {

		Optional<Cliente> Cliente = clienteDao.buscaClienteid(Id);

		if (Cliente.isPresent()) {

			return Cliente.get();
		}
		return null;
	}
	
	public List<Cliente> buscaTodos() {

		List<Cliente> todosCliente = clienteDao.buscaTodosCliente();

		if (!(todosCliente.isEmpty())) {

			return todosCliente;
		}
		return null;
	}
	
	
	public Cliente gravaCliente(String nome,String endereco,String cep,int idade) {
		
		Cliente cliente = new Cliente();
		
		cliente.setNome(nome);
		cliente.setEndereco(endereco);
		cliente.setCep(cep);
		cliente.setIdade(idade);

		Cliente clienteSalvo = clienteDao.gravaCliente(cliente);

		if (!(clienteSalvo.getId() > 0)) {

			return clienteSalvo;
		}
		return null;
	}
	
	public Cliente atualizaCliente(Cliente clienteObj) {
		
		Cliente clienteAtualizado = clienteDao.atualizaCliente(clienteObj);

		if (!(clienteAtualizado.getId() > 0)) {

			return clienteAtualizado;
		}
		return null;
	}
	
	public Cliente deleteCliente(Long idCliente) {

		Cliente clienteExist = existeCliente(idCliente);
		
		if (null != clienteExist) {

			clienteDao.deleteCliente(idCliente);
			
			return clienteExist;
		}
		return null;
	}
	
	public Cliente existeCliente(Long idCliente) {

		Optional<Cliente> clienteExist = clienteDao.existeCliente(idCliente);
		
		if (clienteExist.isPresent()) {

			return clienteExist.get();
		}
		return null;
	}

}
