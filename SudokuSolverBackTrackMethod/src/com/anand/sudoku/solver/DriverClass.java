package com.anand.sudoku.solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.anand.sudoku.solver.algorithm.Solver;
import com.anand.sudoku.solver.exception.InvalidBoardStyleException;
import com.anand.sudoku.solver.exception.InvalidSolutionTypeException;
import com.anand.sudoku.solver.exception.InvalidSudokuPuzzleException;
import com.anand.sudoku.solver.util.SolverConstants;

public class DriverClass {

	private int puzzleSize;
	private int innerRowSize;
	private int innerColumnSize;
	private String sudokuData;
	private int solutionType;

	public static void main(String[] args) {

		try {
			DriverClass driverClass = new DriverClass();
			driverClass.displayIntro();

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			int readerType = Integer.parseInt(in.readLine());

			switch (readerType) {
			case SolverConstants.FILE_READER:
				driverClass.readFromFile(in);
				break;
			case SolverConstants.STD_READER:
				driverClass.readFromStandardInput(in);
				break;
			default:
				System.out.println("Invalid Choice. Proceeding With File Reader");
				driverClass.readFromFile(in);
			}
			in.close();

			Solver solver = new Solver().initialize(driverClass.sudokuData, driverClass.puzzleSize,
					driverClass.innerRowSize, driverClass.innerColumnSize);
			solver.solve(driverClass.solutionType);

		} catch (Exception e) {
			System.err.println("Error Occured In Sudoku Solver. \nError: " + e);
		}
	}

	private void readFromFile(BufferedReader in)
			throws IOException, InvalidBoardStyleException, InvalidSudokuPuzzleException, InvalidSolutionTypeException {
		in = new BufferedReader(new FileReader("sudokuPuzzleInput.txt"));

		puzzleSize = Integer.parseInt(in.readLine());
		innerRowSize = Integer.parseInt(in.readLine());
		innerColumnSize = Integer.parseInt(in.readLine());

		int boardStyle = Integer.parseInt(in.readLine());
		validateBoardStyle(boardStyle);

		switch (boardStyle) {
		case SolverConstants.ONE_ROW_PER_LINE:
			readOneRowPerLine(in);
			break;
		case SolverConstants.ENTIRE_BOARD_ONE_LINE:
			sudokuData = in.readLine();
			break;
		}

		validatePuzzleInput();

		solutionType = Integer.parseInt(in.readLine());

		validateSolutionType();
	}

	private void readFromStandardInput(BufferedReader in) throws NumberFormatException, IOException,
			InvalidSudokuPuzzleException, InvalidSolutionTypeException, InvalidBoardStyleException {

		System.out.print("Enter Size Of Single Row / Column:");
		puzzleSize = Integer.parseInt(in.readLine());

		System.out.print("No Of Rows in Inner Box:");
		innerRowSize = Integer.parseInt(in.readLine());

		System.out.print("No Of Columns in Inner Box:");
		innerColumnSize = Integer.parseInt(in.readLine());

		System.out.println("Enter Board Input Style");
		System.out.println("1. One Row Per Line");
		System.out.println("2. Entire Board In One Line");
		int boardStyle = Integer.parseInt(in.readLine());

		validateBoardStyle(boardStyle);

		switch (boardStyle) {
		case SolverConstants.ONE_ROW_PER_LINE:
			System.out.println("Enter Sudoku Board Rows");
			readOneRowPerLine(in);
			break;
		case SolverConstants.ENTIRE_BOARD_ONE_LINE:
			System.out.println("Enter Sudoku Board As Space Seaprated Digits");
			System.out.println("Example: 0 0 3 0 0 5 0 ........... 0 7 8");

			sudokuData = in.readLine();
			break;
		}

		validatePuzzleInput();

		System.out.println("Enter Solution Type Choice");
		System.out.println("1. Single Solution");
		System.out.println("2. All Possible Solutions");

		solutionType = Integer.parseInt(in.readLine());

		validateSolutionType();
		
		System.out.println();
	}

	private void readOneRowPerLine(BufferedReader in) throws IOException {
		sudokuData = in.readLine();
		int readCount = 1;
		while (readCount < puzzleSize) {
			sudokuData += SolverConstants.SPACE;
			sudokuData += in.readLine();
			readCount++;
		}
	}

	private void validateBoardStyle(int boardStyle) throws InvalidBoardStyleException {
		if (boardStyle > SolverConstants.NO_OF_BOARD_INPUT_STYLES || boardStyle <= 0)
			throw new InvalidBoardStyleException("Invalid Board Input Style Provided. Please recheck the input");
	}

	private void validateSolutionType() throws InvalidSolutionTypeException {
		if (solutionType > SolverConstants.NO_OF_SOLUTION_TYPES || solutionType <= 0)
			throw new InvalidSolutionTypeException("Invalid Solution Type Provided. Please recheck the input");
	}

	private void validatePuzzleInput() throws InvalidSudokuPuzzleException {

		if (sudokuData.length() <= 0)
			throw new InvalidSudokuPuzzleException("Sudoku Board Input Is Empty. Please recheck the input");

		int noOfWiteSpaces = sudokuData.split(SolverConstants.MULTIPLE_SPACE_REGX).length;

		if (puzzleSize * puzzleSize != noOfWiteSpaces) {
			throw new InvalidSudokuPuzzleException("Sudoku Board Input Validation Failed. Please recheck the input");
		}

	}

	private void displayIntro() {
		System.out.println("********************************************");
		System.out.println("***************SUDOKU SOLVER****************");
		System.out.println("********************************************");
		System.out.println();

		System.out.println("Enter Reader Mode:");
		System.out.println("1. File");
		System.out.println("2. Standard Input");

	}
}
