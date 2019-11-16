package com.anand.sudoku.solver.exception;

public class BoardNotInitializedException extends Exception {

	private static final long serialVersionUID = 1L;

	public BoardNotInitializedException() {
		super();
	}

	public BoardNotInitializedException(String errorMessage) {
		super(errorMessage);
	}
}
