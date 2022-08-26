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
		simulate move at current position
		if it is running
			make move at current position
			move()
		else, return
	
  legal moves takes in a position and returns an arraylist of legal moves from that position
		 for i 0 --> 7 inc.
			if current x + move is within bounds and current y + move is within bounds	
				if the position is 0
					add move to arraylist
					
		end for
		return arraylist
  
  count moves takes in a position and move and returns the number of possible moves from a position
	legal moves
	return arraylist size
	
  simulate move takes in a position and returns the best possible move from that position
	    arraylist of legal moves at position
		if arraylist is 0
			isrunning is false
			return -1;
		int best move
		int best move count max int
		for each move
			count number of possible moves
			if number is less than the current minimum
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
	 private int[][] chessboard; //Stores the chessboard as a 2D int array
	 private int x; // The current x position of the knight
	 private int y; // The current y position of the knight
	 private int count; //The move number that the knight is currently on
	 private static final int[] xMove = { -2, -2, -1, -1,  1, 1,  2, 2}; // all possible moves in the x direction
	 private static final int[] yMove = { -1,  1, -2,  2, -2, 2, -1, 1}; // all possible moves in the y direction, array is in the same order as xMove
	 /**
	  * The constructor for the knight class, places the initial position of the knight and declares this position as square 1.
	  * @param startX The initial x position of the knight
	  * @param startY The initial y position of the knight
	  * @param cb The chessboard expressed as a 2D int array
	  */
	 public KnightsTour(int startX, int startY, int[][] cb){
		 x = startX;
		 y = startY;
		 chessboard = cb;
		 chessboard[y][x] = 1;
		 count = 2;

	 }

	 /**
	  * Will use the best move as determined by simulateMove and then will update the position of the knight and the current square nubmer accordingly.
	  * <p></p> If the simulateMove cannot determine a correct position, the method is returned and the tour ceases.
	  */
	 public void move(){
		 int move = simulateMove();
		 //If a move, as determined by simulateMove, exists, then make the move and call move again, else, end the program.
		 if(move != -1) {
			 x += xMove[move];
			 y += yMove[move];
			 chessboard[y][x] = count;
			 count++;
			 move();
		 }
	 }

	 /**
	  * Determines all legal moves the knight can make from the position defined in the parameters
	  * A move is determined as legal if:
	  * <p> -The destination square after the move is on the chessboard</p>
	  * <p> -The destination square after the move is currently empty (not yet been touched by the knight)</p>
	  * @param x The x position of the square to check from
	  * @param y The y position of the square to check from
	  * @return An ArrayList containing all possible moves that can be made from the square
	  */
	 public ArrayList<Integer> legalMoves(int x, int y){
	 	ArrayList<Integer> moves = new ArrayList<Integer>();
		/*
			Iterates through every possible move in both xMove and yMove
			Note: since xMove and yMove have the same length, either one will prevent an out of bounds error for both
		 */
	 	for(int i = 0; i < xMove.length; i++){
	 		// xDestination and yDestination have been added for clarity, as they are called multiple times in the loop
		 	int xDestination = x + xMove[i];
		 	int yDestination = y + yMove[i];
		 	// Checks if xDestination and yDestination lie within the bounds of the chessboard. Also checks if the position after the move has not been already filled.
		 	if((xDestination < chessboard[0].length) && (xDestination >= 0) && (yDestination < chessboard.length) && (yDestination >= 0 ) && (chessboard[yDestination][xDestination] == 0)){
				moves.add(i);
			}
		 }
	 	return moves;
	 }

	 /**
	  * Simulates all possible, legal, moves and determines the best move to make
	  * @return The best move to make, expressed as an index on the xMove and yMove arrays.
	  */
	 public int simulateMove(){
		 ArrayList<Integer> moves = legalMoves(x,y);
		 int bestMove = -1; //The best move that is to be made
		 int bestMoveCount = Integer.MAX_VALUE; // The number of possible moves that result from making the best move
		 //Checks if any possible moves can be made from the current position, if not, it returns -1 which terminates the tour
		 if(moves.size() == 0){
		 	return -1;
		 }
		 for(Integer m : moves){
		 	int currentMoveCount = legalMoves(x+xMove[m],y+yMove[m]).size();
		 	if(currentMoveCount < bestMoveCount){
		 		bestMove = m;
		 		bestMoveCount = currentMoveCount;
			}
		 }
		 return bestMove;
	 }

	 /**
	  * Prints the chessboard formatted so that it is an even square. <p><b>Any numbers below 10 have a 0 added to the beginning</b><</p>
	  */
	 public void printBoard() {
		 for (int[] row : chessboard) {
			 for (int pos : row) {
			 	if(pos < 10){
			 		System.out.print("0");
				}
				 System.out.print( pos + " ");
			 }

			 System.out.println();
		 }
	 }

	 public static void main(String[] args){
	 	Scanner s = new Scanner(System.in);
	 	int initX = -1;
	 	int initY = -1;
	 	int[][] chssbrd = new int[8][8];
	 	// While the initial X position is invalid, keep checking for a new x position
	 	while(initX < 0 || initX > chssbrd[0].length-1){
	 		System.out.print("Please input the initial x position of the knight(1-"+chssbrd[0].length+"): ");
	 		//If the next line in the scanner is an int, accept it, else, don't
	 		if(s.hasNextInt()){
	 			initX = s.nextInt()-1;
				s.nextLine();
			}else {
	 			s.nextLine();
			}
			//If the input is not within range, send a message telling the user that, and ask them to try again
	 		if(initX < 0 || initX > chssbrd[0].length){
	 			System.out.println("Sorry that is not a valid x position, please try again. (Valid inputs are from 1-"+(chssbrd[0].length)+")");
			}
		}
		 // While the initial Y position is invalid, keep checking for a new Y position
		 while(initY < 0 || initY > chssbrd.length-1){
			 System.out.print("Please input the initial y position of the knight(1-"+(chssbrd.length)+"): ");
			 //If the next line in the scanner is an int, accept it, else, don't
			 if(s.hasNextInt()){
				 initY = s.nextInt()-1;
				 s.nextLine();
			 }else {
				 s.nextLine();
			 }
			 //If the input is not within range, send a message telling the user that, and ask them to try again
			 if(initY < 0 || initY > chssbrd.length-1){
				 System.out.println("Sorry that is not a valid y position, please try again. (Valid inputs are from 1-"+(chssbrd.length)+")");
			 }
		 }
		 KnightsTour kt = new KnightsTour(initX, initY, chssbrd);
		 kt.move();
		 kt.printBoard();
	 }
 }
