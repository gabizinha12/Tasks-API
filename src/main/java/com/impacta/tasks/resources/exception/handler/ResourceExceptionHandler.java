package com.impacta.tasks.resources.exception.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.impacta.tasks.resources.exception.StandardError;
import com.impacta.tasks.services.exceptions.DatabaseException;
import com.impacta.tasks.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException re, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Entity not found");
		err.setMessage(re.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);

	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> databaseError(DatabaseException de, HttpServletRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setMessage(de.getMessage());
		err.setError("Database integrity violation");
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
