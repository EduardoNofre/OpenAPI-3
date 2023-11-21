package com.api.spring.doc.app.canal.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;

import com.api.spring.doc.app.canal.dao.ClienteDao;
import com.api.spring.doc.app.canal.dto.ClienteDTO;
import com.api.spring.doc.app.canal.entity.Cliente;

@Service
public class ClienteService {

	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private  ModelMapper modelMapper;

	public ClienteDTO busca(long Id) {

		Optional<Cliente> cliente = clienteDao.buscaClienteid(Id);

		if (cliente.isPresent()) {		
			return modelMapper.map(cliente, new TypeToken<ClienteDTO>() {}.getType());
		}
		return null;
	}
	
	public List<ClienteDTO> buscaTodos() {

		List<Cliente> todosCliente = clienteDao.buscaTodosCliente();

		if (org.springframework.util.CollectionUtils.isEmpty(todosCliente)) {

			return null;
		}

		return modelMapper.map(todosCliente, new TypeToken<List<ClienteDTO>>() {}.getType());
	}
	
	
	public ClienteDTO gravaCliente(String nome,String endereco,String cep,int idade) {
		
		Cliente cliente = new Cliente();
		
		cliente.setNome(nome);
		cliente.setEndereco(endereco);
		cliente.setCep(cep);
		cliente.setIdade(idade);

		Cliente clienteSalvo = clienteDao.gravaCliente(cliente);

		if (!(clienteSalvo.getId() > 0)) {

			return modelMapper.map(clienteSalvo, new TypeToken<ClienteDTO>() {}.getType());
		}
		return null;
	}
	
	public ClienteDTO atualizaCliente(Cliente clienteObj) {
		
		Cliente clienteAtualizado = clienteDao.atualizaCliente(clienteObj);

		if (!(clienteAtualizado.getId() > 0)) {

			return modelMapper.map(clienteAtualizado, new TypeToken<ClienteDTO>() {}.getType());
		}
		return null;
	}
	
	public void deleteCliente(Long idCliente) {

		Cliente clienteExist = existeCliente(idCliente);
		
		if (null != clienteExist) {

			clienteDao.deleteCliente(idCliente);
			
		}

	}
	
	private Cliente existeCliente(Long idCliente) {

		Optional<Cliente> clienteExist = clienteDao.existeCliente(idCliente);
		
		if (clienteExist.isPresent()) {

			return clienteExist.get();
		}
		return null;
	}

}
