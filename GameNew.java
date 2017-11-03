

import java.util.Scanner;

/**
 * Mastermind Game July 12, 2017
 * 
 * @author Joon An
 * 
 */

public class GameNew implements MastermindInterface

{

	int[] num = new int[4];

	String[] x = new String[4];

	String[] comp = new String[4];

	String[] eval = new String[4];

	String[] descending = new String[4];

	String[][] input = new String[10][8];

	int givenNum = 0;

	int count = 0;

	/**
	 * Make a new game started generating 4-digit random number
	 * 
	 */
	@Override
	public void newGame() {

		for (int i = 0; i < num.length; i++) {
			num[i] = 1 + (int) (Math.random() * 8);
		}

		ranNumValidation();
	}

	/**
	 * Validate if the 4-digit number unique.
	 */
	public void ranNumValidation() {
		for (int i = 0; i < num.length; i++) {
			for (int j = i + 1; j < num.length; j++) {
				if (num[i] == num[j]) {
					newGame();
				}
			}
			x[i] = String.valueOf(num[i]);

		}

	}

	/**
	 * Draw the cells of the answer at the top of the table Then, generate 10
	 * rows and 4 columns the user is able to fill in
	 * 
	 * @param in 4 digit number the user enters or null value when the initializes
	 * 			  If the value is null value, then cell is empty.
	 * 			  If the data is sent from the user, the cell display those data value
	 * 			  In terms of the input numbers or null value, the contents of the cell will be finalized. 
	 */
	@Override
	public void drawGame(String[][] in) {
		// TODO Auto-generated method stub

		System.out.println(" =======================");

		for (int i = 0; i < x.length; i++) {
			if (givenNum == 0) {
				x[i] = "X";
				System.out.print("|  " + x[i] + "  ");

				x[i] = String.valueOf(num[i]);
			} else {
				System.out.print("|  " + x[i] + "  ");

			}
		}
		System.out.println("|");

		for (int i = input.length - 1; i >= 0; i--) {
			System.out.println(" -----------------------     ------");

			for (int j = 0; j < input[i].length; j++) {
				if (in[i][j] == null) {
					input[i][j] = " ";

				} else {
					input[i][j] = in[i][j];
				}
			}
			System.out.println("|  " + input[i][0] + "  |  " + input[i][1] + "  |  " + input[i][2] + "  |  "
					+ input[i][3] + "  | = | " + input[i][4] + input[i][5] + input[i][6] + input[i][7] + " |");

		}
		System.out.println(" -----------------------     ------");
	}

	/**
	 * Retrieve 4-digit numbers from the user by using the prompt and update
	 * them into cell Also, update the validated the numbers the user entered.
	 * The delimiters the user is able to use are a space and a new line
	 */
	@Override
	public void updateGame() {
		// TODO Auto-generated method stub

		for (int i = 0; i < input.length; i++) {
			if (givenNum == 1) {
				break;
			}

			Scanner userInput = new Scanner(System.in);

			System.out.println("Enter 4 digits in order with space between numbers (1 to 8):");

			for (int j = 0; j < 4; j++) {

				comp[j] = userInput.next();
				input[i][j] = comp[j];

			}

			checkValues();

			for (int j = 4; j < input[i].length; j++) {
				input[i][j] = eval[j - 4];
			}

			count++;
			if (eval[3].equals("2") || count == 10) {
				givenNum = 1;
				drawGame(input);
				gameOver();
			} else {
				drawGame(input);
			}
		}
	}

	/**
	 * Compare the 4-digit numbers the user input to the answer. For each
	 * correct number in the correct spot, a value 2 should be added. For each
	 * correct number in an incorrect spot, a value 1 should be added. All other
	 * numbers get a value 0 added.
	 * 
	 * The user can enter the same numbers for instance "4 4 4 4" The highest
	 * single validating value is assigned
	 */
	@Override
	public void checkValues() {

		for (int i = 0; i < eval.length; i++) {
			eval[i] = null;
		}

		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < comp.length; j++) {
				if (x[i].equals(comp[j])) {
					if (i == j) {
						eval[i] = "2";
					} else {
						if (eval[i] != "2")
							eval[i] = "1";
					}
				} else {
					if (eval[i] != "1" && eval[i] != "2")
						eval[i] = "0";
				}
			}
		}

		String temp;

		for (int i = 0; i < eval.length; i++) {
			for (int j = i + 1; j < eval.length; j++) {
				if (eval[i].equals("0")) {
					if (eval[j].equals("1")) {
						temp = eval[i];
						eval[i] = eval[j];
						eval[j] = temp;
					} else if (eval[j].equals("2")) {
						temp = eval[i];
						eval[i] = eval[j];
						eval[j] = temp;
					}
				}

				if (eval[i].equals("1")) {
					if (eval[j].equals("2")) {
						temp = eval[i];
						eval[i] = eval[j];
						eval[j] = temp;
					}
				}
				if (eval[i].equals("2")) {
					eval[i] = eval[i];
				}

			}
		}
	}

	/**
	 * End or start over the game. The user can start the game again, entering
	 * "Y" The user can end the game enter "N" that pops up "Goodbye" message
	 */
	public void gameOver() {
		Scanner input = new Scanner(System.in);
		System.out.println("Your game is done!!!. Would you like to restart (Y/N)?");

		if (input.next().equalsIgnoreCase("Y")) {
			newGame();
			givenNum = 0;
			count = 0;
			String[][] in = new String[10][8];
			drawGame(in);
			updateGame();
		} else {
			System.out.println("Goodbye!!!");

		}

	}

}
