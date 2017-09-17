/*ConnectFour
 * the classic Connect Four game, but in text on a terminal!
 * it uses 2D arrays and Scanner to track input and the gameBoard, and some
 * nested for loops to check win conditions.
 * Created for Hello World Hackathon
 *
 * made by Grant Bowman
 * last worked on 2017-09-17 11:17am
*/

import java.util.Scanner;

public class ConnectFour {                

  private static char gameBoard[][] = new char[7][7];
  private static boolean gameWon = false;

  public static void main(String[] args) {

    for (int row = 0; row < gameBoard.length; row++) {
      for (int slot = 0; slot < gameBoard[row].length; slot ++) {

        gameBoard[row][slot] = '.';

      }

    } //end of gameBoard setup


    Scanner scans = new Scanner(System.in);
    int choice = -1;
    String input = "";

    // the main gameplay loop. Does the same thing twice but each for X and O. after each
    // input it checks if it fits and then re-requests or adds it. after each turn it checks the
    // win conditions and will exit the loop if someone wins.
    while(!gameWon) {
    
      printGame();
      do { // X turn (haha Xtern)
        System.out.print(" X's turn: ");
        input = scans.nextLine();
        if (input == null || input == "\n") {
          choice = 9001;
        }
        else {
          choice = Integer.parseInt(input)-1;
        }
        if (!isSlotFull(choice)) {
          addPiece('X', choice);
          break;
        }
        System.out.println("INVALID OPTION");
      } while (isSlotFull(choice)); 

      // win check after X turn
      if (gameWon) break;
      if (testForWin()) break;

      printGame();
      do { // O's turn
        System.out.print(" O's turn: ");
        input = scans.nextLine();
        if (input == null || input == "\n") {
          choice = 9001;
        }
        else {
          choice = Integer.parseInt(input)-1;
        }
        if (!isSlotFull(choice)) {
          addPiece('O', choice);
          break;
        }
        System.out.println("INVALID OPTION");
      } while (isSlotFull(choice));
      
      // win check after O turn
      if (gameWon) break;
      if (testForWin()) break;
      
    }


    System.out.println("GAME END");
    printGame();

  } //end main

  //adds a piece to the bottom of the slot by using a for loop until there is a piece below
  //or it hits the end of the places to be. Inputs a character to place (X or O from main) and the slot
  //to drop it into (i.e. 0 being the far left).
  private static void addPiece(char piece, int slot) {
    if (isSlotFull(slot)) return; //just in case
    for (int row = 0; row < gameBoard.length; row++) {

      if (row == 6 || gameBoard[row+1][slot] != '.') {
        gameBoard[row][slot] = piece;
        break;
      }

    }

  }


  //runs through the given slot and checks if there is slot for any more pieces
  //returns true if slot is out of range or slot is full
  private static boolean isSlotFull(int slot) {
    if (slot < 0 || slot > gameBoard.length-1) return true;
    return gameBoard[0][slot] != '.';
  } //end isSlotFull

  //goes through and prints the 2d array gameBoard
  private static void printGame() {
    System.out.println("_______________");
    for (int slot = 1; slot <= gameBoard[0].length; slot++) System.out.print(" " + slot);
    System.out.println();

    for (int row = 0; row < gameBoard.length; row++) {
      for (int slot = 0; slot < gameBoard[row].length; slot++) {
        System.out.print(" " + gameBoard[row][slot]);
      }
      System.out.println();
    }
    System.out.println("_______________");
  } //end printGame

  private static boolean testForWin() {
  
    char temp = '.';

    //sweep means each iteration path
    for (int m = 0; m <= gameBoard.length-4; m++) {
      for (int n = 0; n < gameBoard[m].length; n++) {
        //System.out.println("[DEV 113]: M:" + m + " N:" + n);
        //checks 4 horizontally, sweep down then right
  //-      System.out.println("-: "+n+m+gameBoard[n][m]+" "+gameBoard[n][m+1]+" "+gameBoard[n][m+2]+" "+gameBoard[n][m+3]);
        if (gameBoard[n][m] == gameBoard[n][m+1] && gameBoard[n][m+2] == gameBoard[n][m+3] && gameBoard[n][m] == gameBoard[n][m+2] && gameBoard[n][m] != '.') {
          System.out.println("" + gameBoard[n][m] + " Wins!");
          gameWon = true;
          return true;
        }
        //vertical sweep right then down
  //|      System.out.println("|: "+m+n+gameBoard[m][n]+" "+gameBoard[m+1][n]+" "+gameBoard[m+2][n]+" "+gameBoard[m+3][n]);
        if (gameBoard[m][n] == gameBoard[m+1][n] && gameBoard[m+2][n] == gameBoard[m+3][n] && gameBoard[m][n] == gameBoard[m+2][n] && gameBoard[m][n] != '.') {
          System.out.println("" + gameBoard[m][n] + " Wins!");
          gameWon = true;
          return true;
        }
      } //end inner-n loop
      for (int i = 0; i <= gameBoard[m].length-4; i++) {

        //diagonal \ sweep down then right
  //\      System.out.println("\\: "+m+i+gameBoard[m][i]+" "+gameBoard[m+1][i+1]+" "+gameBoard[m+2][i+2]+" "+gameBoard[m+3][i+3]);
        if (gameBoard[m][i] == gameBoard[m+1][i+1] && gameBoard[m+2][i+2] == gameBoard[m+3][i+3] && gameBoard[m][i] == gameBoard[m+2][i+2] && gameBoard[m][i] != '.') {
          System.out.println("" + gameBoard[m][i] + " Wins!");
          gameWon = true;
          return true;
        }
        //diagonal / sweep from bottomleft to upperright; sweep downwards then right
  ///      System.out.println("/: "+m+i+gameBoard[m+3][i]+" "+gameBoard[m+2][i+1]+" "+gameBoard[m+1][i+2]+" "+gameBoard[m][i+3]);
        if (gameBoard[m+3][i] == gameBoard[m+2][i+1] && gameBoard[m+1][i+2] == gameBoard[m][i+3] && gameBoard[m+3][i] == gameBoard[m+1][i+2] && gameBoard[m+3][i] != '.') {
          System.out.println("" + gameBoard[m+3][m] + " Wins!");
          gameWon = true;
          return true;
        }
      } //end inner-i loop           
    } //end outter loop
    gameWon = false;
    return false;

  } //end testForWin

} //end class





