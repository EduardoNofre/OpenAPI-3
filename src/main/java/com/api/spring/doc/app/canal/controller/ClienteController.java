package com.api.spring.doc.app.canal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.spring.doc.app.canal.dto.ClienteDTO;
import com.api.spring.doc.app.canal.entity.Cliente;
import com.api.spring.doc.app.canal.hanlde.ServiceException;
import com.api.spring.doc.app.canal.hanlde.ServiceNoContentExcetion;
import com.api.spring.doc.app.canal.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cliente/")
@Tag(name = "Cliente Controller", description = "Cliente crud API")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@Operation(summary = "Busca cliente por id", description = "Busca cliente por id no banco de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso"),
			@ApiResponse(responseCode = "400", description = "Processar a requisição"),
			@ApiResponse(responseCode = "401", description = "Não autorizado"),
			@ApiResponse(responseCode = "404", description = "Não encontrado"),
			@ApiResponse(responseCode = "500", description = "Interno sem causa mapeada."),
			@ApiResponse(responseCode = "504", description = "Gateway Time-Out") })
	@GetMapping(value = "id/{idCliente}", produces = { "application/json", "application/xml" })
	public ResponseEntity<ClienteDTO> buscaId(
			@Parameter(name = "idCliente", description = "id cliente", example = "123") @RequestParam(name = "idCliente", required = true) long idCliente) throws ServiceException {

		return ResponseEntity.status(HttpStatus.OK).body(clienteService.busca(idCliente));
	}

	@Operation(summary = "Lista todos os cliente.", description = "Busca todos os cliente no banco de dados")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class)) }),
			@ApiResponse(responseCode = "201", description = "Criado", content = @Content),
			@ApiResponse(responseCode = "204", description = "Sem conteudo", content = @Content),
			@ApiResponse(responseCode = "400", description = "Processar a requisição", content = @Content),
			@ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
			@ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content),
			@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Interno sem causa mapeada.", content = @Content),
			@ApiResponse(responseCode = "504", description = "Gateway Time-Out", content = @Content) })
	@GetMapping(value = "lista", produces = { "application/json", "application/xml" })
	public ResponseEntity<List<ClienteDTO>> buscaTodos(
			@Parameter(name = "pagina", description = "Numero da pagina", example = "1") @RequestParam(name = "pagina", required = true) Integer pagina
			) throws ServiceNoContentExcetion {

		return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscaTodos(pagina));
	}

	@Operation(summary = "Cadastro de cliente", description = "Cadastro de cliente no banco de dados")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content),
			@ApiResponse(responseCode = "201", description = "Criado", content = @Content),
			@ApiResponse(responseCode = "204", description = "Sem conteudo", content = @Content),
			@ApiResponse(responseCode = "400", description = "Processar a requisição", content = @Content),
			@ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
			@ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content),
			@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Interno sem causa mapeada.", content = @Content),
			@ApiResponse(responseCode = "504", description = "Gateway Time-Out", content = @Content) })
	@PostMapping(path = "cadastro/{nome}/{endereco}/{cep}/{idade}/{email}/{telefone}", produces = { "application/json",
			"application/xml" })
	public ResponseEntity<ClienteDTO> registraCliente(
			@Parameter(name = "nome", description = "Nome do cliente.", required = true) @RequestParam(name = "nome", required = true) String nome,
			@Parameter(name = "endereco", description = "Endereco do cliente") @RequestParam(name = "endereco", required = true) String endereco,
			@Parameter(name = "cep", description = "Cep do cliente") @RequestParam(name = "cep", required = true) String cep,
			@Parameter(name = "idade", description = "Idade do cliente") @RequestParam(name = "idade", required = true) int idade,
			@Parameter(name = "email", description = "E-mail do cliente") @RequestParam(name = "email", required = true) String email,
			@Parameter(name = "telefone", description = "Telefone do cliente") @RequestParam(name = "telefone", required = true) String telefone) throws ServiceException {

		return ResponseEntity.status(HttpStatus.OK).body(clienteService.gravaCliente(nome, endereco, cep, idade,email,telefone));

	}

	@Operation(summary = "Remove o registro do cliente", description = "Deleta o registro do cliente no banco de dados")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content),
			@ApiResponse(responseCode = "201", description = "Criado", content = @Content),
			@ApiResponse(responseCode = "204", description = "Sem conteudo", content = @Content),
			@ApiResponse(responseCode = "400", description = "Processar a requisição", content = @Content),
			@ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
			@ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content),
			@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Interno sem causa mapeada.", content = @Content),
			@ApiResponse(responseCode = "504", description = "Gateway Time-Out", content = @Content) })
	@DeleteMapping(path = "Remove/id{idCliente}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Void> deleteCliente(
			@Parameter(name = "idCliente", description = "id cliente", example = "123") @RequestParam(name = "idCliente", required = true) Long idCliente) throws ServiceException {

		clienteService.deleteCliente(idCliente);

		return ResponseEntity.ok().build();
	}

	@Operation(summary = "Atualiza o cliente", description = "Atualiza os dados do cliente no banco de dados")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Sucesso", content = @Content),
			@ApiResponse(responseCode = "201", description = "Criado", content = @Content),
			@ApiResponse(responseCode = "204", description = "Sem conteudo", content = @Content),
			@ApiResponse(responseCode = "400", description = "Processar a requisição", content = @Content),
			@ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
			@ApiResponse(responseCode = "403", description = "Acesso negado", content = @Content),
			@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
			@ApiResponse(responseCode = "500", description = "Interno sem causa mapeada.", content = @Content),
			@ApiResponse(responseCode = "504", description = "Gateway Time-Out", content = @Content) })
	@PutMapping(value = "atualiza/{IdCliente}", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<ClienteDTO> atualizaCliente(@RequestBody @Valid Cliente cliente) throws ServiceException {

		return ResponseEntity.status(HttpStatus.OK).body(clienteService.atualizaCliente(cliente));
	}

}
