package com.api.spring.doc.app.canal.entity;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SqlResultSetMapping(
		name = "clienteMapping",
		classes = @ConstructorResult(
				targetClass = ClienteMapping.class,
				columns = {
						@ColumnResult(name = "id",				 	 type = Long.class),
						@ColumnResult(name = "nome",				 type = String.class),
						@ColumnResult(name = "endereco",			 type = String.class),
						@ColumnResult(name = "cep",				 	 type = String.class),
						@ColumnResult(name = "telefone",			 type = String.class),
						@ColumnResult(name = "idade",				 type = Integer.class),
						@ColumnResult(name = "email",			 	 type = String.class)

						
				}))
public class ClienteMapping {

	@Id
	private Long id;
	private String nome;
	private String endereco;
	private String cep;
	private String telefone;
	private Integer idade;
	private String email;
}
