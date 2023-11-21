package com.api.spring.doc.app.canal.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.spring.doc.app.canal.dao.ClienteDao;
import com.api.spring.doc.app.canal.dto.ClienteDTO;
import com.api.spring.doc.app.canal.entity.Cliente;
import com.api.spring.doc.app.canal.hanlde.ServiceException;
import com.api.spring.doc.app.canal.hanlde.ServiceNoContentExcetion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.api.spring.doc.app.canal.util.ConstantesUtil.*;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteService {

	@Autowired
	private ClienteDao clienteDao;

	@Autowired
	private ModelMapper modelMapper;

	public ClienteDTO busca(long id) throws ServiceException {

		Optional<Cliente> cliente = clienteDao.buscaClienteid(id);

		if (cliente.isPresent()) {
			
			return modelMapper.map(cliente.get(), new TypeToken<ClienteDTO>() {
			}.getType());
		}

		throw new ServiceException(USUARIO_NAO_ENCONTRADO);
	}

	public List<ClienteDTO> buscaTodos() throws ServiceNoContentExcetion {

		List<Cliente> todosCliente = clienteDao.buscaTodosCliente();

		if (org.springframework.util.CollectionUtils.isEmpty(todosCliente)) {

			throw new ServiceNoContentExcetion();
		}

		return modelMapper.map(todosCliente, new TypeToken<List<ClienteDTO>>() {
		}.getType());
	}

	public ClienteDTO gravaCliente(String nome, String endereco, String cep, int idade,String email, String telefone) throws ServiceException {

		Cliente cliente = new Cliente();

		cliente.setNome(nome);
		cliente.setEndereco(endereco);
		cliente.setCep(cep);
		cliente.setIdade(idade);
		cliente.setEmail(email);
		cliente.setTelefone(telefone);

		Cliente clienteSalvo = clienteDao.gravaCliente(cliente);

		if (clienteSalvo.getId() > 0) {

			return modelMapper.map(clienteSalvo, new TypeToken<ClienteDTO>() {
			}.getType());
		}
		throw new ServiceException(USUARIO_ERRO_PERSISITIR);
	}

	public ClienteDTO atualizaCliente(Cliente clienteObj) throws ServiceException {

		Cliente clienteAtualizado = clienteDao.atualizaCliente(clienteObj);

		if (clienteAtualizado.getId() > 0) {

			return modelMapper.map(clienteAtualizado, new TypeToken<ClienteDTO>() {
			}.getType());
		}
		throw new ServiceException(USUARIO_ERRO_ATUALIZAR);
	}

	public void deleteCliente(Long idCliente) throws ServiceException {

		existeCliente(idCliente);

		clienteDao.deleteCliente(idCliente);

	}

	private Cliente existeCliente(Long idCliente) throws ServiceException {

		Optional<Cliente> clienteExist = clienteDao.existeCliente(idCliente);

		if (clienteExist.isPresent()) {

			return clienteExist.get();
		}
		throw new ServiceException(USUARIO_NAO_ENCONTRADO);
	}

}
