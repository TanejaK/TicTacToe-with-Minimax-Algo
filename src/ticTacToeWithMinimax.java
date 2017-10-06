package ticTacToeWithMinimax;

import java.util.Arrays;

public class ticTacToeWithMinimax {

	public static void main(String[] args) {
		gameBoard newGame = new gameBoard();
		char[][] board = new char[3][3];
		for(int row=0; row<3; row++) {
			Arrays.fill(board[row], ' ');
		}
		int counter =0;
		while(newGame.isActive(board)) {
			if(counter%2==0) {
				newGame.askPlayer('X',board);
				counter++;
			}
			else {
				newGame.findOptimalMove(board);
				counter++;
			}
			if(counter==9) {
				System.out.println("Its a tie");
				break;
			}
		}

	}

}
