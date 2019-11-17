package com.anand.sudoku.solver.exception;

public class InvalidBoardStyleException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidBoardStyleException() {
		super();
	}

	public InvalidBoardStyleException(String errorMessage) {
		super(errorMessage);
	}
}
