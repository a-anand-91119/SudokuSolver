package com.anand.sudoku.solver.exception;

/**
 * Exception class throws if the sudoku board data entered is not valid for the dimensions of the puzzle provided.
 * 
 * @author A Anand
 *
 */
public class InvalidSudokuPuzzleException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidSudokuPuzzleException() {
		super();
	}

	public InvalidSudokuPuzzleException(String errorMessage) {
		super(errorMessage);
	}
}
