/* import scanner
8x8 chessboard 2D array
  current x position
  current y position
  boolean is running
  constructor takes an original position and stores it in instance variables
  
  Note: Moves will be stored as two int arrays
			x { -2 -2 -1 -1  1  1 2  2}
			y { -1  1 -2  2 -2  2 1 -1}
							      	
  move method takes in nothing as a parameter and will update current x and y position and add the current count to the array at the proper positon
		Prerequisite: move is legal
		simulate move at current positon
		if it is running
			make move at current positoion
			move()
		else, return
	
  legal moves takes in a positon and returns an arraylist of legal moves from that position
		 for i 0 --> 7 inc.
			if current x + move is within bounds and current y + move is within bounds	
				if the positon is not 0
					add move to arraylist
					
		end for
		return arraylist
  
  count moves takes in a position and move and returns the number of possible moves from a position
	legal moves
	return arraylist size
	
  simulate move takes in a postion and returns the best possible move from that position
	    arraylist of legal moves at position
		if arraylist is 0
			isrunning is false
			return -1;
		int best move
		int best move count max int
		for each move
			count number of possible moves
			if nummber is less than the current minimum
				update move and move count accordingly
				
		return best possible move
			
    printBoard()
		print out chessboard seperated
    main
		new scanner 
		x and y variable from input
		construct new knightstour
		move()
		printBoard()
 */
 import java.util.*;
 public class KnightsTour{
	 private int[][] chessboard = new int[8][8];
	 private int x;
	 private int y;
	 private int count;
	 private static int[] xMove = { -2, -2, -1, -1,  1, 1,  2, 2};
	 private static int[] yMove = { -1,  1, -2,  2, -2, 2, -1, 1};
	 
	 public KnightsTour(int startX, int startY){
		 x = startX;
		 y = startY;
		 chessboard[y][x] = 1;
		 count = 2;
	 }
	 
	 public void move(){
		 int move = simulateMove();
		 if(move != -1){
			 x += xMove[move];
			 y += yMove[move];
			 chessboard[y][x] = count;
			 count++;
			 move();
		else{
			return;
		}
	 }
	 
	 public ArrayList<Integer> legalMoves(int x, int y){
		 
	 }
	 
	 public int countMoves(int x, int y){
		 
	 }
	 
	 public int simulateMove(){
		 
	 }
	 
	 public void printBoard(){
		for(int[] row : chessboard){
			for(int pos : row){
				System.out.print(pos + " ");
	 }
 }