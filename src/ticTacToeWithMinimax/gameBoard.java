package ticTacToeWithMinimax;

import java.util.Scanner;

public class gameBoard {
	
	public char[][] board;
	
	/*This is a constructor for initializing the board
	 * Initializing it with spaces because if not initialized it will point to null.
	 * And that will become hard to test.
	 * */
//	public gameBoard() {
//		board = new char[3][3];
//		for(int row=1; row<4; row++) {
//			Arrays.fill(board[row], ' ');
//		}
	//}// End of the constructor.
	
	
	/* This is method to display the board.
	 * */
	public void displayBoard(char[][] board) {
		for(int row = 0; row < board.length; row++) {
			for(int col = 0; col < board[0].length; col++) {
				System.out.print(board[row][col]);
				if( col==0 || col==1 )
					System.out.print("\t|");
			}
			if(row!=2)
				System.out.println("\n------------------------");
		}
		System.out.println("\n");
	}// End of displayBoard method.
	
	
	/*Checking if my game is still active or not. If there is a winner it will
	 *  return false. 
	 * */
	public boolean isActive(char[][] board) {
		//checking for rows.
		for(int row=0; row<board.length; row++) {
			if(board[row][0]==board[row][1] && board[row][1]==board[row][2]&& board[row][0]!= ' ') {
				System.out.println("Winner is: "+ board[row][0]);
				return false;
			}
		}
		//checking for cols.
		for(int col=0; col<board[0].length; col++) {
			if(board[0][col]==board[1][col] && board[1][col]==board[2][col] && board[0][col]!= ' '){
				System.out.println("Winner is: "+ board[0][col]);
				return false;
			}
		}
		// checking diagonally
		if(board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0]!=' '){
			System.out.println("Winner is: "+ board[0][0]);
			return false;
		}
		if(board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[0][2]!=' '){
			System.out.println("Winner is: "+ board[0][2]);
			return false;
		}
		return true;
	}
	
	
	/*Checking if the selected position is valid or not.
	 * This function will return true only if that position is valid.
	 * */
	public boolean notValid(int row, int col, char[][] board) {
		if( row>2 || row<0 || col>2 || col<0) {
			System.out.println("Plaese enter a valid position");
			return true;
		}
		if(board[row][col]!=' ') {
			System.out.println("Already taken!! Please enter a valid move.");
			return true;
		}
		return false;
	}// End of notValid move.
	
	/*Asking a player to select row and col for their next move and checking if 
	 * that move is valid or not. If not keep going on. If valid then will 
	 * update our board.
	 * */
	public void askPlayer(char player, char[][] board) {
		Scanner sc = new Scanner(System.in);
		int row,col;
		do {
			System.out.printf("Player %s please enter a row number between 0-2",player);
			row = sc.nextInt();
			System.out.printf("Player %s please enter a col number between 0-2",player);
			col = sc.nextInt();
		}while(notValid(row, col, board));
	
		board[row][col]=player;
		displayBoard(board);
		
	}// End of askPlayer method.
	
	/*
	 *Check if there are still positions left on
	 *the board. this method will return boolean value.
	 * */
	public boolean availablePositions(char[][] board) {
		
		for(int row=0; row<3; row++) {
			for(int col=0; col<3; col++) {
				if(board[row][col]==' ') {
					return true;
				}
			}
		}
		return false;
	}// End of availablePositions method.
	
	
	/* Evaluate board will return the status of the game
	 * when no positions are left.
	 * */
	public int evaluateBoard(char[][] board) {
		//checking for player 'X'
		//checking for rows.
		for(int row=0; row<board.length; row++) {
			if(board[row][0]==board[row][1] && board[row][1]==board[row][2]&& board[row][0]== 'X') 
				return -10;
		}
		//checking for cols.
		for(int col=0; col<board[0].length; col++) {
			if(board[0][col]==board[1][col] && board[1][col]==board[2][col] && board[0][col]== 'X')
				return -10;
		}
		// checking diagonally
		if(board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0]=='X')
			return -10;
		if(board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[0][2]=='X')
			return -10;
		
		//checking for player 'O'
		//checking for rows.
		for(int row=0; row<board.length; row++) {
			if(board[row][0]==board[row][1] && board[row][1]==board[row][2]&& board[row][0]== 'O') 
				return 10;
		}
		//checking for cols.
		for(int col=0; col<board[0].length; col++) {
			if(board[0][col]==board[1][col] && board[1][col]==board[2][col] && board[0][col]== 'O')
				return 10;
		}
		// checking diagonally
		if(board[0][0]==board[1][1] && board[1][1]==board[2][2] && board[0][0]=='O')
			return 10;
		if(board[0][2]==board[1][1] && board[1][1]==board[2][0] && board[0][2]=='O')
			return 10;
		return 0;
	}
	/*MINIMAX
	 * 
	 * */
	public int minimax(char[][] board, boolean isMax) {
		int eval = evaluateBoard(board);
		if(!availablePositions(board)) {
			return eval;
		}
		if(eval==10||eval==-10)
			return eval;
		if(isMax) {
			int maxValue=-1000;
			for(int row=0; row<3; row++) {
				for(int col=0; col<3; col++) {
					if(board[row][col]==' ') {
						board[row][col]='O';
						maxValue = Math.max(maxValue, minimax(board,!isMax));
						if(maxValue==10) {
							board[row][col]=' ';
							return maxValue;
						}
						board[row][col]=' ';
					}
				}
			}
			return maxValue;
		}
		else {
			int minValue=1000;
			for(int row=0; row<3; row++) {
				for(int col=0; col<3; col++) {
					if(board[row][col]==' ') {
						board[row][col]='X';
						minValue = Math.min(minValue, minimax(board,!isMax));
						if(minValue==-10) {
							board[row][col]=' ';
							return minValue;
							
						}
						board[row][col]=' ';
					}
				}
			}
			return minValue;
		}
	}// end of minimax algo.
	
	
	/*Find Optimal move will find the best move for computer.
	 * 
	 * */
	
	public void findOptimalMove(char[][] board) {
		int maxValue = -1000;
		int currentValue;
		int bestRow = -1;
		int bestCol = -1;
		Loop: 
		for(int row=0; row<3;row++) {
			for(int col=0; col<3;col++) {
				if(board[row][col]==' ') {
					board[row][col]='O';
					int eval = evaluateBoard(board);
					if(eval==10) {
						//maxValue= 10;
						bestRow = row;
						bestCol = col;
						break Loop;
					}
					currentValue = minimax(board, false);
					board[row][col]=' ';
					if(currentValue>= maxValue) {
						maxValue= currentValue;
						bestRow = row;
						bestCol = col;
					}
				}
			}
		}
		board[bestRow][bestCol] ='O';
		displayBoard(board);
	}
}
