package com.anand.sudoku.solver.exception;

/**
 * Exception class throws if the solution type specified is invalid or not supported.
 * 
 * @author A Anand
 *
 */
public class InvalidSolutionTypeException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidSolutionTypeException() {
		super();
	}

	public InvalidSolutionTypeException(String errorMessage) {
		super(errorMessage);
	}
}
