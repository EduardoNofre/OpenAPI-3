package com.api.spring.doc.app.canal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.spring.doc.app.canal.entity.Cliente;
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

	@Operation(summary = "Busca cliente por id", description = "Busca cliente por id no banco de dados", tags = { "Busca por id" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso"),
							@ApiResponse(responseCode = "400", description = "Processar a requisição"),
							@ApiResponse(responseCode = "401", description = "Não autorizado"),
							@ApiResponse(responseCode = "404", description = "Não encontrado"),
							@ApiResponse(responseCode = "500", description = "Interno sem causa mapeada."),
							@ApiResponse(responseCode = "504", description = "Gateway Time-Out") })
	@GetMapping(value  = "id/{idCliente}", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> buscaId(@Parameter(description="idCliente não pode esta vazio.", required=true) @PathVariable long idCliente) {
		
		Cliente cliente = clienteService.busca(idCliente);			
		return new ResponseEntity<> (cliente,HttpStatus.OK);
	}
	
	@Operation(summary = "Lista todos os cliente.", description = "Busca todos os cliente no banco de dados", tags = { "Lista todos os clientes" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso"),
							@ApiResponse(responseCode = "400", description = "Processar a requisição"),
							@ApiResponse(responseCode = "401", description = "Não autorizado"),
							@ApiResponse(responseCode = "404", description = "Não encontrado"),
							@ApiResponse(responseCode = "500", description = "Interno sem causa mapeada."),
							@ApiResponse(responseCode = "504", description = "Gateway Time-Out") })
	@GetMapping(value  = "lista", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> buscaTodos() {
		
		List<Cliente> clientes = clienteService.buscaTodos();			
		return new ResponseEntity<> (clientes,HttpStatus.OK);
	}
	
	@Operation(summary = "Cadastro de cliente", description = "Cadastro de cliente no banco de dados", tags = { "Cadastro de cliente" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso"),
							@ApiResponse(responseCode = "400", description = "Processar a requisição"),
							@ApiResponse(responseCode = "401", description = "Não autorizado"),
							@ApiResponse(responseCode = "404", description = "Não encontrado"),
							@ApiResponse(responseCode = "500", description = "Interno sem causa mapeada."),
							@ApiResponse(responseCode = "504", description = "Gateway Time-Out") })
	@PostMapping(path = "cadastro/{nome}/{endereco}/{cep}/{idade}/{email}/{telefone}", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> registraCliente(@Parameter(description="Nome do cliente.", required=true) @PathVariable String nome,
			 								 @Parameter(description="Endereco do cliente", required=true) @PathVariable String endereco,
			 								 @Parameter(description="Cep do cliente", required=true) @PathVariable String cep,
			 								 @Parameter(description="Idade do cliente", required=true) @PathVariable int idade,
			 								 @Parameter(description="E-mail do cliente", required=true) @PathVariable String email,
			 								@Parameter(description="Telefone do cliente", required=true) @PathVariable String telefone) {
		
		Cliente clienteSalvo = clienteService.gravaCliente(nome, endereco, cep, idade);			
		return new ResponseEntity<> (clienteSalvo,HttpStatus.OK);
	}
		
	@Operation(summary = "Remove o registro do cliente", description = "Deleta o registro do cliente no banco de dados", tags = { "Delete Cliente" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso",content = @Content(schema = @Schema(implementation = Cliente.class))),
							@ApiResponse(responseCode = "400", description = "Processar a requisição",content = @Content(schema = @Schema(implementation = Cliente.class))),
							@ApiResponse(responseCode = "401", description = "Não autorizado",content = @Content(schema = @Schema(implementation = Cliente.class))),
							@ApiResponse(responseCode = "404", description = "Não encontrado",content = @Content(schema = @Schema(implementation = Cliente.class))),
							@ApiResponse(responseCode = "500", description = "Interno sem causa mapeada.",content = @Content(schema = @Schema(implementation = Cliente.class))),
							@ApiResponse(responseCode = "504", description = "Gateway Time-Out",content = @Content(schema = @Schema(implementation = Cliente.class))) })
	@DeleteMapping(path = "Remove/id{idCliente}", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> deleteCliente(@Parameter(description="id cliente.", required=true) @PathVariable Long idCliente) {
		
		Cliente deletadoCliente = clienteService.deleteCliente(idCliente);			
		return new ResponseEntity<> (deletadoCliente,HttpStatus.OK);
	}
	
	@Operation(summary = "Atualiza o cliente", description = "Atualiza os dados do cliente no banco de dados", tags = { "Update Cliente" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Sucesso",content = @Content(schema = @Schema(implementation = Cliente.class))),
							@ApiResponse(responseCode = "400", description = "Processar a requisição",content = @Content(schema = @Schema(implementation = Cliente.class))),
							@ApiResponse(responseCode = "401", description = "Não autorizado",content = @Content(schema = @Schema(implementation = Cliente.class))),
							@ApiResponse(responseCode = "404", description = "Não encontrado",content = @Content(schema = @Schema(implementation = Cliente.class))),
							@ApiResponse(responseCode = "500", description = "Erro interno sem causa mapeada.",content = @Content(schema = @Schema(implementation = Cliente.class))),
							@ApiResponse(responseCode = "504", description = "Gateway Time-Out",content = @Content(schema = @Schema(implementation = Cliente.class)))})
	@PutMapping(value  = "atualiza/{IdCliente}", produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" })
	public ResponseEntity<?> atualizaCliente(@RequestBody @Valid Cliente cliente) {
		
		Cliente atualizaCliente = clienteService.atualizaCliente(cliente);			
		return new ResponseEntity<> (atualizaCliente,HttpStatus.OK);
	}
	
}
