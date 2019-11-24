package com.anand.sudoku.solver.algorithm;

import com.anand.sudoku.solver.util.SolverConstants;

/**
 * The Class represents a Sudoku Puzzle Board. 
 * 
 * @author A Anand
 *
 */
public class SudokuBoard {
	private static int solutionNumber = 1;
	private int[][] sudokuBoard;
	private int innerColumn;
	private int innerRow;
	private int puzzleSize;

	/**
	 * Constructor to initailize the SudokuBoard.
	 * 
	 * @param sudokuData  the concatenated string of data corresponding to the
	 *                    puzzle. The value for each cell should be separated by a
	 *                    single space
	 * @param row         the size / total no of rows or columns in the puzzle
	 * @param innerRow    the row size of a single inner box
	 * @param innerColumn the column size of a single inner box
	 */
	public SudokuBoard(String sudokuData, int row, int innerRow, int innerColumn) {
		this.innerRow = innerRow;
		this.puzzleSize = row;
		this.innerColumn = innerColumn;
		String[] datas = sudokuData.split(SolverConstants.SINGLE_SPACE_REGX);
		int dataCount = 0;
		sudokuBoard = new int[row][row];

		for (int i = 0; i < puzzleSize; i++)
			for (int j = 0; j < puzzleSize; j++)
				sudokuBoard[i][j] = Integer.parseInt(datas[dataCount++]);
	}

	/**
	 * This method checks whether the sudoku board is completely solved or not. The
	 * board is solved values in all cells are not 0. If there exists a cell with 0
	 * value in it, means the board is not solved completely.
	 * 
	 * @return true if the board is completely solved, otherwise returns false
	 */
	public boolean isFull() {
		for (int i = 0; i < puzzleSize; i++)
			for (int j = 0; j < puzzleSize; j++)
				if (sudokuBoard[i][j] == 0)
					return false;
		return true;
	}

	/**
	 * This method checks whether a cell is filled or not. A cell is filled if the
	 * value in the cell is not 0.
	 * 
	 * @param row    the row corresponding to the target cell
	 * @param column the column corresponding to the target cell
	 * @return the method returns true if the cell is not filled, otherwise returns
	 *         false
	 */
	public boolean isNotFilled(int row, int column) {
		if (sudokuBoard[row][column] == 0)
			return true;
		return false;
	}

	/**
	 * The method returns an array of all the possible numbers that can be placed in
	 * a particular cell pointed by the row and column. First the row of the cell,
	 * then the column of the cell and finally the inner box containing the cell is
	 * considered to determine the possible values that can be placed in the cell.
	 * 
	 * @param row    the row corresponding to the target cell
	 * @param column the column corresponding to the target cell
	 * @return an array of values that can be placed in the target cell
	 */
	public int[] possibleEntries(int row, int column) {
		int[] possibleEntries = new int[puzzleSize + 1];

		for (int i = 0; i < puzzleSize; i++) {
			if (sudokuBoard[row][i] != 0)
				possibleEntries[sudokuBoard[row][i]] = 1;
		}

		for (int i = 0; i < puzzleSize; i++) {
			if (sudokuBoard[i][column] != 0)
				possibleEntries[sudokuBoard[i][column]] = 1;
		}

		int startColumn = innerColumn * (column / innerColumn);
		int startRow = innerRow * (row / innerRow);
		for (int i = startRow; i < startRow + innerRow; i++) {
			for (int j = startColumn; j < startColumn + innerColumn; j++) {
				if (sudokuBoard[i][j] != 0)
					possibleEntries[sudokuBoard[i][j]] = 1;
			}
		}

		for (int i = 1; i < possibleEntries.length; i++) {
			if (possibleEntries[i] != 0)
				possibleEntries[i] = i;
		}
		for (int i = 1; i < possibleEntries.length; i++) {
			if (possibleEntries[i] == 0)
				possibleEntries[i] = i;
			else
				possibleEntries[i] = 0;
		}

		return possibleEntries;
	}

	/**
	 * This method sets the data of a particular cell to the specified value.
	 * 
	 * @param row    the row corresponding to the target cell
	 * @param column the column corresponding to the target cell
	 * @param value  the value to be set in the cell pointed by the row and column
	 *               values
	 */
	public void set(int row, int column, int value) {
		sudokuBoard[row][column] = value;
	}

	/**
	 * This method formats and displays the sudoku board
	 */
	public void printBoard() {
		System.out.println("*********************************");
		System.out.println("Solution: " + solutionNumber++);
		System.out.println("*********************************");
		String rowSeparator = new String(new char[4 * puzzleSize + 5]).replace(SolverConstants.EMPTY_CHARACTER,
				SolverConstants.DASH);

		StringBuilder stringBuilder = new StringBuilder(SolverConstants.PLUS).append(rowSeparator)
				.append(SolverConstants.PLUS).append(SolverConstants.NEW_LINE);

		for (int i = 0; i < sudokuBoard.length; i++) {
			for (int j = 0; j < sudokuBoard.length; j++) {

				if (j % innerColumn == 0) {
					stringBuilder.append(SolverConstants.PIPE).append(" ");
				}

				if (sudokuBoard[i][j] < 10)
					stringBuilder.append(SolverConstants.SPACE).append(SolverConstants.SPACE).append(sudokuBoard[i][j])
							.append(SolverConstants.SPACE);
				else
					stringBuilder.append(SolverConstants.SPACE).append(sudokuBoard[i][j]).append(SolverConstants.SPACE);
			}

			stringBuilder.append(SolverConstants.PIPE).append(SolverConstants.NEW_LINE);

			if (i != sudokuBoard.length - 1 && (i + 1) % innerRow == 0)
				stringBuilder.append(SolverConstants.PIPE).append(rowSeparator).append(SolverConstants.PIPE)
						.append(SolverConstants.NEW_LINE);
		}

		stringBuilder.append(SolverConstants.PLUS).append(rowSeparator).append(SolverConstants.PLUS);

		System.out.println(stringBuilder);
	}
}