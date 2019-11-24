package com.anand.sudoku.solver.exception;

/**
 * Exception class throws if the sudoku board is not initialized properly before attempting to solve the puzzle.
 * 
 * @author A Anand
 *
 */
public class BoardNotInitializedException extends Exception {

	private static final long serialVersionUID = 1L;

	public BoardNotInitializedException() {
		super();
	}

	public BoardNotInitializedException(String errorMessage) {
		super(errorMessage);
	}
}
