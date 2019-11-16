package com.anand.sudoku.solver.algorithm;

import com.anand.sudoku.solver.exception.BoardNotInitializedException;
import com.anand.sudoku.solver.util.SolverConstants;

public class Solver {

	private SudokuBoard sudokuBoard = null;
	private boolean solutionFound = false;

	public Solver initialize(String sudokuData, int row, int innerRow, int innerColumn) {
		sudokuBoard = new SudokuBoard(sudokuData, row, innerRow, innerColumn);
		return this;
	}

	public void solve(int solutionType) throws BoardNotInitializedException {
		if (sudokuBoard == null)
			throw new BoardNotInitializedException("The Sudoku Board Is Not Initialized Properly.");
		
		if(solutionType == SolverConstants.SINGLE_SOLUTION) {
			long startTime = System.currentTimeMillis();
			if(!doSolveOneSolution(sudokuBoard))
				System.out.println("No Solution Found");
			System.out.println("Time Elapsed To Find Solution: " + (System.currentTimeMillis() - startTime) + "ms");
		}else if(solutionType == SolverConstants.ALL_SOLUTIONS) {
			long startTime = System.currentTimeMillis();
			if(!doSolveAllPossibleSolutions(sudokuBoard))
				System.out.println("No Solution Found");
			System.out.println("Time Elapsed To Find All Possible Solutions: " + (System.currentTimeMillis() - startTime) + "ms");
		}
	}

	private boolean doSolveOneSolution(SudokuBoard sudokuBoard2) {
		int i = 0;
		int j = 0;
		
		if (sudokuBoard.isFull()) {
			sudokuBoard.printBoard();
			return true;
		} else {
			outerLoop:
			for (i = 0; i < 9; i++) {
				for (j = 0; j < 9; j++) {
					if (sudokuBoard.isNotFilled(i, j)) {
						break outerLoop;
					}
				}
			}

			int[] possibilities = sudokuBoard.possibleEntries(i, j);
			for (int index = 0; index < possibilities.length; index++) {
				if(possibilities[index] != 0) {
					sudokuBoard.set(i,j,possibilities[index]);
					if(doSolveOneSolution(sudokuBoard))
						return true;
				}
			}
			sudokuBoard.set(i,j,0);
		}
		return false;
	}

	private boolean doSolveAllPossibleSolutions(SudokuBoard sudokuBoard) {
		int i = 0;
		int j = 0;
		
		if (sudokuBoard.isFull()) {
			System.out.println("Solved");
			sudokuBoard.printBoard();
			solutionFound = true;
			return true;
		} else {
			outerLoop:
			for (i = 0; i < 9; i++) {
				for (j = 0; j < 9; j++) {
					if (sudokuBoard.isNotFilled(i, j)) {
						break outerLoop;
					}
				}
			}

			int[] possibilities = sudokuBoard.possibleEntries(i, j);
			for (int index = 0; index < possibilities.length; index++) {
				if(possibilities[index] != 0) {
					sudokuBoard.set(i,j,possibilities[index]);
					doSolveAllPossibleSolutions(sudokuBoard);
				}
			}
			sudokuBoard.set(i,j,0);
		}
		return solutionFound;
	}

}
