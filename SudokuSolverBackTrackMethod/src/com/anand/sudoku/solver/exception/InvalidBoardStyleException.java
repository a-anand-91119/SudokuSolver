package com.anand.sudoku.solver.exception;

/**
 * Exception class throws if the sudoku board style specified is invalid or not supported.
 * 
 * @author A Anand
 *
 */
public class InvalidBoardStyleException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidBoardStyleException() {
		super();
	}

	public InvalidBoardStyleException(String errorMessage) {
		super(errorMessage);
	}
}
