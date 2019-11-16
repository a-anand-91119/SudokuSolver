package com.anand.sudoku.solver.algorithm;

public class SudokuBoard {
	private static int generation = 1;
	private int[][] sudokuBoard;
	private int innerColumn;
	private int innerRow;
	private int puzzleSize;

	public static void main(String[] args) {
		SudokuBoard sudokuBoard = new SudokuBoard("" + "9 0 0 1 0 0 0 0 5 " + "0 0 5 0 9 0 2 0 1 "
				+ "8 0 0 0 4 0 0 0 0 " + "0 0 0 0 8 0 0 0 0 " + "0 0 0 7 0 0 0 0 0 " + "0 0 0 0 2 6 0 0 9 "
				+ "2 0 0 3 0 0 0 0 6 " + "0 0 0 2 0 0 9 0 0 " + "0 0 1 9 0 4 5 7 0", 9 ,3, 3);
		sudokuBoard.possibleEntries(0, 1);
	}

	public SudokuBoard(String sudokuData, int row, int innerRow, int innerColumn) {
		this.innerRow = innerRow;
		this.puzzleSize = row;
		this.innerColumn = innerColumn;
		String[] datas = sudokuData.split("\\s");
		int dataCount = 0;
		sudokuBoard = new int[row][row];

		for (int i = 0; i < puzzleSize; i++)
			for (int j = 0; j < puzzleSize; j++)
				sudokuBoard[i][j] = Integer.parseInt(datas[dataCount++]);
	}

	public boolean isFull() {
		for (int i = 0; i < puzzleSize; i++)
			for (int j = 0; j < puzzleSize; j++)
				if (sudokuBoard[i][j] == 0)
					return false;
		return true;
	}

	public boolean isNotFilled(int i, int j) {
		if (sudokuBoard[i][j] == 0)
			return true;
		return false;
	}

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
		for(int i=1;i<possibleEntries.length;i++) {
			if(possibleEntries[i] == 0)
				possibleEntries[i] = i;
			else
				possibleEntries[i] = 0;
		}
		
		return possibleEntries;
	}

	public void set(int row, int column, int value) {
		sudokuBoard[row][column] = value;
	}

	public void printBoard() {
		System.out.println("*********************************");
		System.out.println("Solution: " + generation++);
		System.out.println("*********************************");
		StringBuilder stringBuilder = new StringBuilder("+-----------------------+\n");
		for (int i = 0; i < sudokuBoard.length; i++) {
			for (int j = 0; j < sudokuBoard.length; j++) {
				if (j % innerColumn == 0) {
					stringBuilder.append("|").append(" ");
				}
				stringBuilder.append(sudokuBoard[i][j]).append(" ");
			}
			stringBuilder.append("|\n");
			if (i != sudokuBoard.length - 1 && (i + 1) % innerRow == 0)
				stringBuilder.append("+-----------------------+\n");
		}
		stringBuilder.append("+-----------------------+");
		/*for(int i=0;i<9;i++) {
			System.out.println(Arrays.toString(sudokuBoard[i]));
		}*/
		System.out.println(stringBuilder);
	}
}