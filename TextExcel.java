package TextExcel;

import java.util.Scanner;

public class TextExcel {
	
	public static final int ROWS = 11;
	public static final int COLUMNS = 7;
	public static final int WIDTH = 12;
	
	public static void main(String[] args) throws Exception {
		
		CellMatrix cellmatrix = CellMatrix.getInstance();
		System.out.print("Welcome to TextExcel!\n\nEnter a command: ");
		Scanner console = new Scanner(System.in);
		String rawInput = console.nextLine();
		while (!(rawInput.equalsIgnoreCase("exit") || rawInput.equalsIgnoreCase("quit"))) {
			if (rawInput.contains("clear")) {
				cellmatrix.clear(rawInput);
			} else if (rawInput.equalsIgnoreCase("print")) {
				cellmatrix.print();
			} else if (rawInput.contains("=")) {
				int[] cell = findCoords(rawInput.substring(0, 2));
				String inputValue = rawInput.substring(rawInput.indexOf("=") + 2);
				cellmatrix.setValue(cell[0], cell[1], inputValue, rawInput);
			} else {
				Cell c = cellmatrix.getCell(rawInput);
				String inputValue = cellmatrix.getValue(c);
				if (inputValue == null) {
					System.out.println(rawInput + " = <empty>");
				} else {
					System.out.println(c.getOriginalValue());
				}
			}
			System.out.print("\nEnter a command: ");
			rawInput = console.nextLine();
		}
		console.close();
		System.out.print("\nFarewell!");
		
	}
	
	public static int[] findCoords(String s) {

		char colLetter = s.charAt(0);
		if (colLetter >= 'A' && colLetter <= 'G') {
			int col = colLetter - 'A';
			String rowNumber = s.substring(1);
			int row = Integer.parseInt(rowNumber) - 1;
			if (row < 0 || row > ROWS) {
				System.out.println("Error: Row out of bounds or invalid input.");
				return null;
			}
			int result[] = {row + 1, col + 1};
			return result;
		} else {
			System.out.println("Error: Column out of bounds or invalid input.");
			return null;
		}
		
	}
	
}