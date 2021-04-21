package com.api.spring.doc.app.canal.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Cliente implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(description = "Id cliente.", example = "123", required = true)
	@Column(name="id", length = 9)
	private Long id;

	@Schema(description = "Nome do cliente.", example = "06720-450", required = true)
	@Size(max = 50)
	@Column(name="nome", length = 100)
	private String nome;

	@Schema(description = "Endereço do cliente.", example = "abc", required = true)
	@Size(max = 100)
	@Column(name="endereco", length = 100)
	private String endereco;

	@Schema(description = "Cep do cliente.", example = "abc", required = true)
	@Size(max = 50)
	@Column(name="cep", length = 50)
	private String cep;
	
	@Schema(description = "Idade do cliente.", example = "123", required = true)
	@Pattern(regexp ="^\\+?[0-9. ()-]{7,25}$", message = "numero de telefone")
	@Size(max = 20)
	@Column(name="telefone", length = 20)
	private String telefone;

	@Schema(description = "Idade do cliente.", example = "123", required = true)
	@Size(max = 2)
	@Column(name="idade", length = 2)
	private int idade;
	
	@Email(message = "Endereço de email")
	@Schema(description = "Endereço de email do cliente.", example = "xxxx@xxx.com.br ou xxxx@xxx.com.", required = true)
	@Size(max = 100)
	private String email;

}
