
import java.util.Scanner;

/* this class game defines the two matches first one is player vs player and second one is player vs computer,
it allows the user to choose the match*/

 class Game{
    
    private int moves;  //count of the total no of moves played
    
    //constructor
    public Game(){
        moves = 0;
    }
    
    public void startGame(){

	    System.out.println("Enter 1 for Player vs Player. Enter 2 for Player vs Computer: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
	
	    Player currentPlayer = new Player();    //Human Type
	    Computer Challenger = new Computer();     //Computer(AI) Type

        if(choice == 1)
            System.out.println("Player 1 is X and Player 2 is O");
        else
            System.out.println("Player 1 is X and Computer is O");

        GameBoard board = new GameBoard();
        System.out.println("New Game, Board Numbers are as folllows:");
	    board.printIndexBoard();

	    while(board.Win() == 0 && moves < 9){
	        int index=0;
	        if(choice == 1 || currentPlayer.getType() == 1)
	            index = currentPlayer.makeMove();
	        else
		    index = Challenger.makeMove(board);
	    
            if(board.takeMove(currentPlayer.getType(), index) == 1){    //move taken by the the Game Board
			moves++;
			currentPlayer.changePlayer();
		    }
	        else
		        System.out.println("Enter a valid index.");     //invalid index move not registered by gameboard
		    board.printBoard();
	    
	    }   //end the while loop
	    
	    if(moves < 9){      //not a Draw
	        if(board.Win() == 1)
	            System.out.println("Player1 Wins!");
	        else{           //player2
	            if(choice == 1)
		            System.out.println("Player2 Wins!");
	            else
		            System.out.println("Computer Wins!");
	        }
	    }   
	
	    if(moves == 9 && board.Win() == 0)      //Draw
	        System.out.println("It's a Draw!");
    }
    public static void main(String[] args) {
        
    }
    
}

 class GameBoard{
    
      private char[][] field; //2D Array
      
      //constructor
      public GameBoard(){
          field = new char[3][3];
          
          //initialize the board
          for(int i=0; i<3; i++){
              for(int j=0; j<3; j++){
                  field[i][j] = ' ';
              }
          }
          
      }
          
      //for printing the board
      public void printBoard(){
          
          System.out.println("-----------");
          
          for(int i=0; i<3; i++){
              for(int j=0; j<3; j++){
                  if(j!=2){
                      System.out.print(field[i][j] + " | ");
                  }
                  else{
                      System.out.println(field[i][j]);
                  }
              }
          }
          
          System.out.println("-----------");
          
      }
  
            //for print the board which contains index[1-9]
      public void printIndexBoard(){
            System.out.println("-----------");
          System.out.println("1 | 2 | 3 ");
          System.out.println("-----------");
          System.out.println("4 | 5 | 6 ");
          System.out.println("-----------");
            System.out.println("7 | 8 | 9 ");
          System.out.println("-----------");
      }
      
       // To register the move
      public int takeMove(int player, int index){
        if(index <1 || index >9)
              return 0;
          
          int i =0, j = 0;
          
            switch(index){  //converting index to row and column format
                
                case 1:
                    i = 0;
                    j = 0;
                    break;
                case 2:
                    i = 0;
                    j = 1;
                    break;
                case 3:
                    i = 0;
                    j = 2;
                    break;
                case 4:
                    i = 1;
                    j = 0;
                    break;
                case 5:
                    i = 1;
                    j = 1;
                    break;
                case 6:
                    i = 1;
                    j = 2;
                    break;
                case 7:
                    i = 2;
                    j = 0;
                    break;
                case 8:
                    i = 2;
                    j = 1;
                    break;
                case 9:
                    i = 2;
                    j = 2;
                    break;
                    
            }
            if(field[i][j] != ' '){
              System.out.println("Index is filled already.");
              return 0;
            }
            if(player == 1){
                field[i][j] = 'X';
            }
              
            else{
                field[i][j] = 'O';
            }
        return 1;
      }
  
      public int Win(){
        if((field[0][0] == 'X' && field[0][1] == 'X' && field[0][2] == 'X')||   //this is for rows
           (field[1][0] == 'X' && field[1][1] == 'X' && field[1][2] == 'X')||
           (field[2][0] == 'X' && field[2][1] == 'X' && field[2][2] == 'X')||
           (field[0][0] == 'X' && field[1][0] == 'X' && field[2][0] == 'X')||   //this is for columns
           (field[0][1] == 'X' && field[1][1] == 'X' && field[2][1] == 'X')||
           (field[0][2] == 'X' && field[1][2] == 'X' && field[2][2] == 'X')||
           (field[0][0] == 'X' && field[1][1] == 'X' && field[2][2] == 'X')||   //this is for diagonals
           (field[0][2] == 'X' && field[1][1] == 'X' && field[2][0] == 'X')){
            //System.out.println("Player1 wins!");
            return 1;
        }
        if((field[0][0] == 'O' && field[0][1] == 'O' && field[0][2] == 'O')||
           (field[1][0] == 'O' && field[1][1] == 'O' && field[1][2] == 'O')||
           (field[2][0] == 'O' && field[2][1] == 'O' && field[2][2] == 'O')||
           (field[0][0] == 'O' && field[1][0] == 'O' && field[2][0] == 'O')||
           (field[0][1] == 'O' && field[1][1] == 'O' && field[2][1] == 'O')||
           (field[0][2] == 'O' && field[1][2] == 'O' && field[2][2] == 'O')||
           (field[0][0] == 'O' && field[1][1] == 'O' && field[2][2] == 'O')||
           (field[0][2] == 'O' && field[1][1] == 'O' && field[2][0] == 'O')){
            //System.out.println("Player2 wins!");
            return 2;
        }
        return 0;
      }
  
      public boolean haveSpace(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++)
              if(field[i][j] == ' ')
                  return true;
        }
        return false;
      }
  
      public char getElement(int row, int column){
        return field[row][column];
      }
  
      public void putElement(int row, int column, char character){
        field[row][column]=character;
      }
  public static void main(String[] args) {
      
  }
  }

   class Computer{
    
      //constructor
      public Computer(){}
      
      public int makeMove(GameBoard board){
          System.out.print("Computer picked: ");
          int place = toFindOptimalMove(board);  //index selected by the computer
          System.out.println(place);
          return place;
      }
      
      //Implementing the minimax algorithm
      public static int minimax(GameBoard board, int depth, boolean isMax){
          
          if(board.Win() == 2){
              return 10;
          }
          if(board.Win() == 1){
              return -10;
          }
          if(!board.haveSpace())
              return 0;
          
          if(isMax){
              int best = -1000;
              for(int i=0; i<3;i++){
                  for(int j=0; j<3; j++){
                      if(board.getElement(i,j) == ' '){
                          board.putElement(i,j, 'O');
                          
                          best = Math.max(best, minimax(board, depth+1, !isMax));
                          
                          board.putElement(i, j, ' ');
                      }
                  }
              }
              return best;
          }
          
          else{
              int best = 1000;
              for(int i=0; i<3;i++){
                  for(int j=0; j<3; j++){
                      if(board.getElement(i,j) == ' '){
                          board.putElement(i,j, 'X');
                          
                          best = Math.min(best, minimax(board, depth+1, !isMax));
                          
                          board.putElement(i, j, ' ');
                      }
                  }
              }
              return best;
          }
              
      }
      
            // to find the optimal move by the computer
      public static int toFindOptimalMove(GameBoard board){
          int bestMoveValue = -1000;
          int bestMoveRow = -1;
          int bestMoveColumn = -1;
          
          for(int i=0; i<3; i++){
              for(int j=0; j<3; j++){
                  if(board.getElement(i,j) == ' '){
                      board.putElement(i,j, 'O');
                      int currentMoveValue = minimax(board, 0, false);
                      board.putElement(i, j, ' ');
                      
                      if(currentMoveValue > bestMoveValue){
                          bestMoveRow = i;
                          bestMoveColumn = j;
                          bestMoveValue = currentMoveValue;
                      }
                      
                  }
              }
          }
          //converting row and column to the index 
            int i = bestMoveRow;
            int j = bestMoveColumn;
          
          if(i==0 && j==0)
              return 1;
          if(i==0 && j==1)
              return 2;
          if(i==0 && j==2)
              return 3;
          if(i==1 && j==0)
              return 4;
          if(i==1 && j==1)
              return 5;
          if(i==1 && j==2)
              return 6;
          if(i==2 && j==0)
              return 7;
          if(i==2 && j==1)
              return 8;
          if(i==2 && j==2)
              return 9;
          return 0;
      }
      public static void main(String[] args) {
          
      }
      
  
  }


 class Player{
    private int playerKind; //1 or 2
    
    //constructor
    public Player(){
        playerKind = 1;
    }
    
    public int makeMove(){
        System.out.print("Enter the desired index [1-9]: ");
	    Scanner input = new Scanner(System.in);
	    int position = input.nextInt();
	    return position;
    }
    
    public void changePlayer(){
        if(playerKind == 1)
	        playerKind = 2;		
	    else
	        playerKind = 1;
    }
    
    public int getType(){
        return playerKind;
    }
    public static void main(String[] args) {
        
    }
    
}

public class TicTacToe{

      public static void main(String []args){
          System.out.println("Welcome to the game of Tic-Tac-Toe!");
            Game newGame = new Game();
            newGame.startGame();
      }
  }




