package com.api.spring.doc.app.canal.hanlde;

import java.io.Serializable;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.spring.doc.app.canal.util.Util;

@ControllerAdvice
public class ServiceHandler extends ResponseEntityExceptionHandler implements Serializable {

	private static final long serialVersionUID = 4790038517549729374L;

	@ExceptionHandler(ServiceException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public ResponseEntity<String> handle(ServiceException e) {
		return montaMsg(e.getMessage(), HttpStatus.CONFLICT);
	}

//	@ExceptionHandler(FeignException.class)
//	public ResponseEntity<String> handleFeignStatusException(FeignException e, HttpServletResponse response) {
//		String msg = null;
//		int status = 0;
//
//		if (e.status() == 401) {
//			status = e.status();
//			msg = Util.feingDetailMessage(e.getMessage(), "error");
//		}
//
//		if (e.status() == 403) {
//			status = e.status();
//			msg = Util.feingDetailMessage(e.getMessage(), "error");
//		}
//
//		if (e.status() == 404) {
//			status = e.status();
//			msg = Util.feingDetailMessage(e.getMessage(), "error");
//		}
//
//		if (e.status() == 405) {
//			status = e.status();
//			msg = Util.feingDetailMessage(e.getMessage(), "detail");
//		}
//
//		if (e.status() == 409) {
//			status = e.status();
//			msg = Util.feingDetailMessage(e.getMessage(), "errorDescription");
//		}
//
//		if (e.status() == -1) {
//			status = 500;
//			msg = e.getCause() + e.request().url();
//		}
//
//		return montaMsg(msg, HttpStatus.valueOf(status));
//	}

	@ExceptionHandler(RestClientResponseException.class)
	public ResponseEntity<String> handleRestClientResponseException(RestClientResponseException e) {

		String msg = null;
		int status = 0;

		if (e.getRawStatusCode() == 409) {

			msg = Util.feingDetailMessage(e.getMessage(), "errorDescription");
			status = e.getRawStatusCode();
		}

		return montaMsg(msg, HttpStatus.valueOf(status));
	}

	/*
	 * Usado pelo Bean Validator
	 */
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errorList = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList());

		String msg = ex.getMessage();
		HttpStatus statusRetorno = status;
		if (!errorList.isEmpty()) {
			msg = errorList.toString().replace("[", "").replace("]", "");
			statusRetorno = HttpStatus.CONFLICT;
		}
		return handleExceptionInternal(ex, new ErrorResponse(msg, statusRetorno), headers, statusRetorno, request);
	}

	@ExceptionHandler(ResourceAccessException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleResourceAccessException(ResourceAccessException e) {
		return montaMsg(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
		return montaMsg(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public ResponseEntity<String> handle(AccessDeniedException e) {
		return montaMsg(e.getMessage(), HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(ServiceNoContentExcetion.class)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<String> handleNoContent(ServiceNoContentExcetion e) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	private ResponseEntity<String> montaMsg(String msg, HttpStatus status) {
		return new ResponseEntity<>(new ErrorResponse(msg, status).toString(), ServiceHandler.getHeaderJson(), status);
	}

	public static HttpHeaders getHeaderJson() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}
