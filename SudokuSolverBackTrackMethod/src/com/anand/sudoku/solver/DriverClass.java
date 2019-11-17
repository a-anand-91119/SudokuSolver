package com.anand.sudoku.solver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.anand.sudoku.solver.algorithm.Solver;
import com.anand.sudoku.solver.exception.BoardNotInitializedException;
import com.anand.sudoku.solver.util.SolverConstants;

public class DriverClass {

	public static void main(String[] args) throws IOException, BoardNotInitializedException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.print("Enter Size Of Single Row / Column:");
		int rows = Integer.parseInt(in.readLine());
		
		System.out.print("No Of Rows in Inner Box:");
		int innerRow = Integer.parseInt(in.readLine());
		
		System.out.print("No Of Columns in Inner Box:");
		int innerColumn = Integer.parseInt(in.readLine());
		
		System.out.println("Enter Sudoku Board As Space Seaprated Digits");
		System.out.println("Example: 0 0 3 0 0 5 0 ........... 0 7 8");
		System.out.println();
		String sudokuData = "" + "9 0 0 1 0 0 0 0 5 " + "0 0 5 0 9 0 2 0 1 "
				+ "8 0 0 0 4 0 0 0 0 " + "0 0 0 0 8 0 0 0 0 " + "0 0 0 7 0 0 0 0 0 " + "0 0 0 0 2 6 0 0 9 "
				+ "2 0 0 3 0 0 0 0 6 " + "0 0 0 2 0 0 9 0 0 " + "0 0 1 9 0 4 5 7 0";
		
		sudokuData = "0 0 0 0 0 0 0 9 0 0 12 0 " + 
				"9 0 1 5 3 0 0 0 0 4 0 0 " + 
				"0 0 11 2 0 0 0 0 6 0 0 9 " + 
				"4 11 0 0 0 5 12 0 0 3 1 0 " + 
				"0 0 5 0 0 0 1 0 12 0 11 0 " + 
				"0 6 0 12 0 0 7 11 8 0 0 2 " + 
				"3 0 0 1 10 9 0 0 11 0 6 0 " + 
				"0 9 0 6 0 4 0 0 0 8 0 0 " + 
				"0 5 7 0 0 6 8 0 0 0 4 12 " + 
				"2 0 0 9 0 0 0 0 10 7 0 0 " + 
				"0 0 4 0 0 0 0 10 3 1 0 5 " + 
				"0 7 0 0 8 0 0 0 0 0 0 0";
		
		/*sudokuData = "" + "8 0 0 0 0 0 0 0 0 " + "0 0 3 6 0 0 0 0 0 "
				+ "0 7 0 0 9 0 2 0 0 " + "0 5 0 0 0 7 0 0 0 " + "0 0 0 0 4 5 7 0 0 " + "0 0 0 1 0 0 0 3 0 "
				+ "0 0 1 0 0 0 0 6 8 " + "0 0 8 5 0 0 0 1 0 " + "0 9 0 0 0 0 4 0 0";*/

		if (sudokuData != null && sudokuData.length() > 0) {
			Solver solver = new Solver().initialize(sudokuData, rows, innerRow, innerColumn);
			solver.solve(SolverConstants.SINGLE_SOLUTION);
		} else {
			System.out.println("Invalid Data");
		}
	}
}
