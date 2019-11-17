package com.anand.sudoku.solver.exception;

public class InvalidSolutionTypeException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidSolutionTypeException() {
		super();
	}

	public InvalidSolutionTypeException(String errorMessage) {
		super(errorMessage);
	}
}
