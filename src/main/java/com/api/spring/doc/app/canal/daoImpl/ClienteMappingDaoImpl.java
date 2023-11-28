package com.api.spring.doc.app.canal.daoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.api.spring.doc.app.canal.dao.ClienteMappingDao;
import com.api.spring.doc.app.canal.entity.ClienteMapping;

import lombok.Data;

@Data
@Repository
public class ClienteMappingDaoImpl implements ClienteMappingDao{

	@PersistenceContext
	private final EntityManager em;

	@Override
	public List<ClienteMapping> buscaTodosCliente(Integer pagina) {
		
		int pageSize = 10;
		
		String sql = "SELECT id, "
				+ "cep, "
				+ "email,"
				+ "endereco, "
				+ "idade, "
				+ "nome, "
				+ "telefone "
				+ "FROM CLIENTE ";
		
		
		Query query = em.createNativeQuery(sql, "clienteMapping");
		query.setFirstResult((pagina-1) * pageSize); 
		query.setMaxResults(pageSize);

		return query.getResultList();
		
	}

}
