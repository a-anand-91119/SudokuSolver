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

/**
 * Driver Class in the main class accepting the sudoku board dimensions and
 * sudoku data. The class is capable of reading input from both file and STDIN.
 * 
 * Once the data is read and validated, a sudoku {@link Solver} is initialized
 * and begins to solve the puzzle.
 * 
 * @author A Anand
 *
 */
public class DriverClass {

	private int puzzleSize;
	private int innerRowSize;
	private int innerColumnSize;
	private String sudokuData;
	private int solutionType;

	/**
	 * The main method which reads input, initialized a {@link Solver} and solves
	 * the puzzle
	 * 
	 * @param args the command line arguments.
	 */
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

	/**
	 * Method reads a sudoku puzzle from the sudokuPuzzleInput.txt file, validates
	 * the input read an creates a concatenated string to sudoku rows.
	 * 
	 * The format of sudokuPuzzleInput.txt is <br>
	 * <ul>
	 * <li>Size of the sudoku board (no of rows and no of columns)</li>
	 * <li>Row size of the inner box</li>
	 * <li>Column size of the inner box</li>
	 * <li>BoardStyle: Style in which board data will be provided. Each entry of the
	 * sudoku board needs to be separated by a single space<br>
	 * Possible Values:<br>
	 * 1. Each Row of the puzzle is entered in different line <br>
	 * 2. Entire puzzle input is provided in single line.</li>
	 * <li>Sudoku board data in one line or n lines where n is the number of rows in
	 * the sudoku board</li>
	 * <li>SolutionType: Type of the solution Required. <br>
	 * Possible Values:<br>
	 * 1. Single solution: Find a single solution for the puzzle and stop the
	 * program <br>
	 * 2. All Solutions: Find all possible solutions to the puzzle.</li>
	 * </ul>
	 * 
	 * @param in The buffered reader
	 * @throws IOException                  if error occurs while reading input data
	 * @throws InvalidBoardStyleException   if the board style specified is not
	 *                                      valid
	 * @throws InvalidSudokuPuzzleException if the sudoku board data entered is
	 *                                      invalid
	 * @throws InvalidSolutionTypeException if the solution type required in invalid
	 */
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

	/**
	 * Method reads a sudoku puzzle from the STDIN, validates the input read an
	 * creates a concatenated string to sudoku rows.
	 * 
	 * The various data needed for the application are
	 * <ul>
	 * <li>Size of the sudoku board (no of rows and no of columns)</li>
	 * <li>Row size of the inner box</li>
	 * <li>Column size of the inner box</li>
	 * <li>BoardStyle: Style in which board data will be provided. Each entry of the
	 * sudoku board needs to be separated by a single space<br>
	 * Possible Values:<br>
	 * 1. Each Row of the puzzle is entered in different line <br>
	 * 2. Entire puzzle input is provided in single line.</li>
	 * <li>Sudoku board data in one line or n lines where n is the number of rows in
	 * the sudoku board</li>
	 * <li>SolutionType: Type of the solution Required. <br>
	 * Possible Values:<br>
	 * 1. Single solution: Find a single solution for the puzzle and stop the
	 * program <br>
	 * 2. All Solutions: Find all possible solutions to the puzzle.</li>
	 * </ul>
	 * 
	 * @param in The buffered reader
	 * @throws IOException                  if error occurs while reading input data
	 * @throws NumberFormatException        if any size entered is not a valid
	 *                                      number
	 * @throws InvalidBoardStyleException   if the board style specified is not
	 *                                      valid
	 * @throws InvalidSudokuPuzzleException if the sudoku board data entered is
	 *                                      invalid
	 * @throws InvalidSolutionTypeException if the solution type required in invalid
	 */
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

	/**
	 * This method reads the sudoku board data line by line. The method will be
	 * invoked only if the BoardStyle is {@link SolverConstants#ONE_ROW_PER_LINE}
	 * 
	 * @param in The buffered reader from which data needs to be read
	 * @throws IOException if error occurs while reading input data
	 */
	private void readOneRowPerLine(BufferedReader in) throws IOException {
		sudokuData = in.readLine();
		int readCount = 1;
		while (readCount < puzzleSize) {
			sudokuData += SolverConstants.SPACE;
			sudokuData += in.readLine();
			readCount++;
		}
	}

	/**
	 * This method validates the entered BoardStyle.The possible values are <br>
	 * 1. {@link SolverConstants#ONE_ROW_PER_LINE}<br>
	 * 2. {@link SolverConstants#ENTIRE_BOARD_ONE_LINE}
	 * 
	 * @param boardStyle the board style received from input which needs to be
	 *                   validated
	 * @throws InvalidBoardStyleException if board style is less than 0 or greater
	 *                                    than the total number of board styles
	 */
	private void validateBoardStyle(int boardStyle) throws InvalidBoardStyleException {
		if (boardStyle > SolverConstants.NO_OF_BOARD_INPUT_STYLES || boardStyle <= 0)
			throw new InvalidBoardStyleException("Invalid Board Input Style Provided. Please recheck the input");
	}

	/**
	 * This method validates the entered SolutionType. The possible values are <br>
	 * 1. {@link SolverConstants#SINGLE_SOLUTION}<br>
	 * 2. {@link SolverConstants#ALL_SOLUTIONS}
	 * 
	 * @throws InvalidSolutionTypeException if solution type entered is less than 0 or greater
	 *                                    than the total number of solution types
	 */
	private void validateSolutionType() throws InvalidSolutionTypeException {
		if (solutionType > SolverConstants.NO_OF_SOLUTION_TYPES || solutionType <= 0)
			throw new InvalidSolutionTypeException("Invalid Solution Type Provided. Please recheck the input");
	}

	/**
	 * This method validates the sudoku board data read from the input.
	 * 
	 * @throws InvalidSudokuPuzzleException if the entered data is not a valid
	 *                                      sudoku data as per the dimensions
	 *                                      provided
	 */
	private void validatePuzzleInput() throws InvalidSudokuPuzzleException {

		if (sudokuData.length() <= 0)
			throw new InvalidSudokuPuzzleException("Sudoku Board Input Is Empty. Please recheck the input");

		int noOfWiteSpaces = sudokuData.split(SolverConstants.MULTIPLE_SPACE_REGX).length;

		if (puzzleSize * puzzleSize != noOfWiteSpaces) {
			throw new InvalidSudokuPuzzleException("Sudoku Board Input Validation Failed. Please recheck the input");
		}

	}

	/**
	 * Method Displays Intro Text for the application
	 */
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
