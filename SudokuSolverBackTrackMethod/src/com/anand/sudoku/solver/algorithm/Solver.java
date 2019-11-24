package com.anand.sudoku.solver.algorithm;

import com.anand.sudoku.solver.exception.BoardNotInitializedException;
import com.anand.sudoku.solver.util.SolverConstants;

/**
 * The Solver class solves the {@link SudokuBoard} using backtracking method.
 * The Algorithm: <br>
 * 1. An Empty Cells is found (cell with 0 as value)<br>
 * 2. Find all possible values for the cell <br>
 * 3. Try Each possible value one by one and for each value repeat from step
 * 1.<br>
 * 4. If not value is possible then set the value of cell as 0 and backtrack.
 * 
 * @author A Anand
 *
 */
public class Solver {

	private SudokuBoard sudokuBoard = null;
	private boolean solutionFound = false;
	private int size;

	/**
	 * This method initializes the Solver class. Using the sudokuData a
	 * {@link SudokuBoard} to be solved.
	 * 
	 * @param sudokuData  the sudoku puzzle board data. Each cell should be
	 *                    separated by a single space
	 * @param row         the size of the sudoku board
	 * @param innerRow    the row size of a single inner box
	 * @param innerColumn the column size of a single inner box
	 * @return an Initialized instance of Solver class
	 */
	public Solver initialize(String sudokuData, int row, int innerRow, int innerColumn) {
		sudokuBoard = new SudokuBoard(sudokuData, row, innerRow, innerColumn);
		size = row;
		return this;
	}

	/**
	 * This method solves and displays the solution for the sudoku board. The type
	 * of solution could be <br>
	 * 1. {@link SolverConstants#SINGLE_SOLUTION} <br>
	 * 2. {@link SolverConstants#ALL_SOLUTIONS}<br>
	 * 
	 * This method also displays the time elapsed to solve the puzzle in
	 * milliseconds.
	 * 
	 * @param solutionType the type of the solution required
	 * @return whether the board is solved or not.
	 * @throws BoardNotInitializedException if the {@link SudokuBoard} is not
	 *                                      initialzed before attempting to solve
	 *                                      the puzzle
	 */
	public boolean solve(int solutionType) throws BoardNotInitializedException {
		if (sudokuBoard == null)
			throw new BoardNotInitializedException("The Sudoku Board Is Not Initialized Properly.");

		if (solutionType == SolverConstants.SINGLE_SOLUTION) {

			long startTime = System.currentTimeMillis();
			if (!doSolveOneSolution(sudokuBoard)) {
				System.out.println("No Solution Found");
				return false;
			} else {
				System.out.println("Time Elapsed To Find Solution: " + (System.currentTimeMillis() - startTime) + "ms");
				return true;
			}

		} else if (solutionType == SolverConstants.ALL_SOLUTIONS) {

			long startTime = System.currentTimeMillis();
			if (!doSolveAllPossibleSolutions(sudokuBoard)) {
				System.out.println("No Solution Found");
				return false;
			} else {
				System.out.println("Time Elapsed To Find All Possible Solutions: "
						+ (System.currentTimeMillis() - startTime) + "ms");
				return true;
			}
		}
		return false;
	}

	/**
	 * This method reccursively applies backtracking method to solve the
	 * {@link SudokuBoard}. Once a solution is obtained the method terminated
	 * immediately displaying the obtained solution.
	 * 
	 * @param sudokuBoard the {@link SudokuBoard} to be solved
	 * @return true if the {@link SudokuBoard} is solved, otherwise returns false
	 */
	private boolean doSolveOneSolution(SudokuBoard sudokuBoard) {
		int i = 0;
		int j = 0;

		if (sudokuBoard.isFull()) {
			sudokuBoard.printBoard();
			return true;
		} else {
			outerLoop: for (i = 0; i < size; i++) {
				for (j = 0; j < size; j++) {
					if (sudokuBoard.isNotFilled(i, j)) {
						break outerLoop;
					}
				}
			}

			int[] possibilities = sudokuBoard.possibleEntries(i, j);
			for (int index = 0; index < possibilities.length; index++) {
				if (possibilities[index] != 0) {
					sudokuBoard.set(i, j, possibilities[index]);
					if (doSolveOneSolution(sudokuBoard))
						return true;
				}
			}
			sudokuBoard.set(i, j, 0);
		}
		return false;
	}

	/**
	 * This method reccursively applies backtracking method to solve the
	 * {@link SudokuBoard}. Once a solutions is obtained the solution will be
	 * displayed and the method continues to find other solutions if they exits.
	 * 
	 * @param sudokuBoard the {@link SudokuBoard} to be solved
	 * @return true if the {@link SudokuBoard} is solved, otherwise returns false
	 */
	private boolean doSolveAllPossibleSolutions(SudokuBoard sudokuBoard) {
		int i = 0;
		int j = 0;

		if (sudokuBoard.isFull()) {
			System.out.println("Solved");
			sudokuBoard.printBoard();
			solutionFound = true;
			return true;
		} else {
			outerLoop: for (i = 0; i < size; i++) {
				for (j = 0; j < size; j++) {
					if (sudokuBoard.isNotFilled(i, j)) {
						break outerLoop;
					}
				}
			}

			int[] possibilities = sudokuBoard.possibleEntries(i, j);
			for (int index = 0; index < possibilities.length; index++) {
				if (possibilities[index] != 0) {
					sudokuBoard.set(i, j, possibilities[index]);
					doSolveAllPossibleSolutions(sudokuBoard);
				}
			}
			sudokuBoard.set(i, j, 0);
		}
		return solutionFound;
	}

}
