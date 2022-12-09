import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	
	
	public static void main(String[] args) {
		/* 1st Step: Print out the game board */

		char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' } }; // 3 rows and 3 columns for the board --> 2D
																			// Array

		printGameBoard(gameBoard);
		
		while(true) {
			/*
			 * We put this code block inside a while TRUE loop because there'll be a
			 * consistent waiting for the user, so computer will need to continue listening
			 */
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your placement (1-9): ");
			int playerPos = scan.nextInt();
			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPositions)) {
				System.out.println("Position Taken! Enter a correct position");
				playerPos = scan.nextInt();
			}
			
			placePiece(gameBoard, playerPos, "player");
			
			String result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
			
			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				//System.out.println("Position Taken! Enter a correct position");
				cpuPos = rand.nextInt(9) + 1;
			}
			placePiece(gameBoard, cpuPos, "cpu");
			
			printGameBoard(gameBoard);
			
			
			
			
		}
		
	}
	
	public static void printGameBoard(char[][] gameBoard) { /* Method to print the game board */
		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println(); // After each row, we print a line
		}
	}
	
	public static void placePiece(char[][] gameBoard, int pos, String user) {
		
		char symbol = ' ';//Set the default symbol to ' ', so we know it actually goes into "if" code block
		
		if(user.equals("player")) { //Check if the user is a player or a CPU in order to switch turns
			symbol = 'X';
			playerPositions.add(pos);
		}else if(user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		
		switch (pos) {
			case 1:
				gameBoard[0][0] = symbol; //Using "symbol" will switch between X and O based on the player
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;	
			default:
				break;
		}
	
	}
	
	public static String checkWinner() {
		
		List topRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(7, 5, 3);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
		
		for(List l : winning) {
			if(playerPositions.containsAll(l)) {
				return "Congratulations. You Won!";
			}else if(cpuPositions.containsAll(l)) {
				return "CPU won!. Try again";
			}else if(playerPositions.size() + cpuPositions.size() == 9) {
				return "CAT!";
			}
		}
		
		return "";
	}
	

}

