package com.api.spring.doc.app.canal.dao;

import java.util.List;

import com.api.spring.doc.app.canal.entity.ClienteMapping;

public interface ClienteMappingDao{

	public List<ClienteMapping> buscaTodosCliente(Integer pagina);
}
