package com.api.spring.doc.app.canal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ClienteDTO {

	@Schema(name = "id", description = "Identificação do cliente", example = "123", type = "Long")
	private Long id;
	@Schema(name = "nome", description = "Nome do cliente", example = "carlos", type = "String")
	private String nome;
	@Schema(name = "endereco", description = "Endereco do cliente", example = "carlos", type = "String")
	private String endereco;
	@Schema(name = "cep", description = "CEP do cliente", example = "08720-860", type = "String")
	private String cep;
	@Schema(name = "telefone", description = "Telefone do cliente", example = "(11) 5555-55555", type = "String")
	private String telefone;
	@Schema(name = "idade", description = "Idade do cliente", example = "31", type = "int")
	private int idade;
	@Schema(name = "email", description = "E-mail do cliente", example = "carlos@gmail.com", type = "int")
	private String email;
}
