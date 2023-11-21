package com.api.spring.doc.app.canal.hanlde;

import java.io.IOException;
import java.io.Serializable;
import java.util.Optional;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "errorCode", "error", "errorDescription" })
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 3064633702216878129L;

    private int errorCode;

    private String error;

    private String errorDescription;

    /**
     * Construtor que deve ser utilizado para informar a mensagem do erro e o
     * {@code HTTP} status code.
     * 
     * @param errorDescription A descrição do erro
     * @param status           O {@code HTTP} status code.
     */
    public ErrorResponse(final String errorDescription, final HttpStatus status) {
    	this.errorDescription = errorDescription;
    	this.error = status.getReasonPhrase();
    	this.errorCode = status.value();
    }

    /**
     * Converte o valor do tipo {@code String} para uma instância do tipo
     * {@code ErrorResponse}.
     * 
     * @param body Valor que será convertido para objeto
     * 
     * @return {@code Optional} de {@code ErrorResponse} ou {@code Optional#empty()}
     *         caso não seja possível de converter o valor para objeto.
     */
    public static Optional<ErrorResponse> converterStringToObject(final String body) {
	try {
	    return Optional.ofNullable((ErrorResponse) new ObjectMapper().readerFor(ErrorResponse.class).readValue(body));
	} catch (IOException e) {
	    log.error("NÃO FOI POSSÍVEL CONVERTER A STRING '{}' PARA OBJETO ERROR_RESPONSE", body);
	}
	return Optional.empty();
    }


    
    public int getErrorCode() {
		return errorCode;
	}

	public String getError() {
		return error;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	/**
     * Sobrescrito o método {@code toString()} de {@code Object} para o estado da
     * instância ser mostrada no formado de {@code JSON}.
     */
	@Override
    public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
