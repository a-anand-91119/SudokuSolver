package com.anand.sudoku.solver.exception;

public class InvalidSudokuPuzzleException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidSudokuPuzzleException() {
		super();
	}

	public InvalidSudokuPuzzleException(String errorMessage) {
		super(errorMessage);
	}
}
