package com.imagnative.exception;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private List<String> errors = Collections.emptyList();

	public EmployeeNotFoundException(String msg) {
		super(msg);
	}

	public EmployeeNotFoundException(String message, List<String> errors) {
		super(message);
		this.errors = errors;
	}

}
