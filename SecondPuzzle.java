//API : http://mabe02.github.io/lanterna/apidocs/2.1/
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;

import java.util.*;

public class SecondPuzzle {

	private String[][]grid;
  private int row; //location of space
  private int col;

	public SecondPuzzle() {
		grid = new String[3][3];
		row = 2; //space in lower right corner
    col = 2;
    fillGrid(); //place all numbers on grid
		scramble(); //mixes board
	}

  public void fillGrid() {
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        grid[r][c] = addSpaces("" + (3*r+c+1)) + (3*r+c+1); //adds in numbers 1-8
      }
    }
    grid[2][2] = "  ";
  }

  public void scramble() {
    Random randgen = new Random();
    for (int i = 0; i < 40; i++) {
      int direction = randgen.nextInt(4); //scrambles randomly
      if (direction == 0) {
        moveLeft();
      }
      else if (direction == 1) {
        moveRight();
      }
      else if (direction == 2) {
        moveUp();
      }
      else if (direction == 3) {
        moveDown();
      }
    }
  }

	public String addSpaces(String s) { //adds spaces to account for the 2 spaces that should be in each position
		String spaces = "";
		int length = 2 - s.length();
		for (int i = 0; i < length; i++) {
			spaces = spaces + " ";
		}
		return spaces;
	}

	public void moveLeft() { //moves number to right of space over
		if (col != 2) {
      String newNum = grid[row][col+1].trim();
      grid[row][col] = addSpaces(newNum) + newNum;
      grid[row][col+1] = "  ";
			col++;
    }
	}

	public void moveRight() { //moves number to left of space over
		if (col != 0) {
      String newNum = grid[row][col-1].trim();
      grid[row][col] = addSpaces(newNum) + newNum;
      grid[row][col-1] = "  ";
			col--;
    }
	}

	public void moveUp() { //moves number below space up
    if (row != 2) {
      String newNum = grid[row+1][col].trim();
      grid[row][col] = addSpaces(newNum) + newNum;
      grid[row+1][col] = "  ";
			row++;
    }
	}

  public void moveDown() { //moves number above space down
    if (row != 0) {
      String newNum = grid[row-1][col].trim();
      grid[row][col] = addSpaces(newNum) + newNum;
      grid[row-1][col] = "  ";
			row--;
    }
	}

	public boolean isComplete() { //checks if numbers are in order
    boolean complete = true;
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        String correct = addSpaces("" + (3*r+c+1)) + (3*r+c+1);
				if (!(r == 2 && c == 2)) {
					if (!(grid[r][c].equals(correct))) {
	          return false;
	        }
				}
      }
    }
    return complete;
  }

	public String toString() { //prints 2-d array grid with a grid around it to mimic game
		String s = "|--------|\n";
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (c == 0) {
					s = s + "|" + grid[r][c] + "|";
				}
				else {
					s = s + grid[r][c] + "|";
				}
			}
			if (r == 2) {
				s = s + "\n|--------|";
			}
			else {
				s = s + "\n|--+--+--|\n";
			}
		}
		return s;
	}

	public static void putString(int r, int c,Terminal t, String s){ //displays String on terminal
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}

	public static int [] playFifteen(Terminal terminal, int beginMin, int beginSec) {
		SecondPuzzle A = new SecondPuzzle();
		putString(0, 1, terminal, A.toString());
		putString(0, 10, terminal, "|  Use the arrow keys  |");
		putString(0, 11, terminal, "| to place the numbers |");
		putString(0, 12, terminal, "| in the correct order |");

		boolean gameNotDone = true;
		int [] returns = new int [3]; //returns 3 mins
		long lastTime =  System.currentTimeMillis();
    long currentTime = lastTime;
    long timer = 0;
    boolean firstPass = true;

		while (gameNotDone) {
			gameNotDone = !(A.isComplete()); //puzzle not solved
			putString(0, 1, terminal, A.toString());
			Key key = terminal.readInput();
			if (key != null){
				if (key.getKind() == Key.Kind.Escape) { //exits game
          terminal.exitPrivateMode();
          gameNotDone = false;
					returns [0] = -1;
	        return returns;
        }
				if (key.getKind() == Key.Kind.ArrowLeft) { //moves numbers appropriately
					A.moveLeft();
				}
				if (key.getKind() == Key.Kind.ArrowRight) {
					A.moveRight();
				}
				if (key.getKind() == Key.Kind.ArrowUp) {
					A.moveUp();
				}
				if (key.getKind() == Key.Kind.ArrowDown) {
					A.moveDown();
				}
			}
			lastTime = currentTime;
      currentTime = System.currentTimeMillis();
      timer += (currentTime -lastTime); //changes time
      int minLeft = beginMin - (int)(timer/60000); //changes min left
      String minPassed = String.format("%02d", minLeft);
			int secLeft;//changes sec left
      if ((int)(timer%60000/1000) > beginSec) {
        firstPass = false;
      }
      if (firstPass) {
        secLeft = beginSec -(int)(timer%60000/1000);
      }
      else {
        secLeft = 60 - (int)(timer%60000/1000);
      }
      String secPassed = String.format("%02d", secLeft); //special case
      if (secLeft == 60) {
        minLeft = beginMin - (int)(timer/60000);
        minPassed = String.format("%02d", minLeft);
        secPassed = "00";
      }
      putString(0,0,terminal, "Time Left: "+ minPassed + ":" + secPassed);
      returns[1] = minLeft; //passes on min left
      returns[2] = secLeft;//passes on sec left

      if (minLeft == 0 && secLeft == 1) { //time runs out
        gameNotDone = false;
        returns [0] = -2; //fail message
        return returns;
      }
    }
    terminal.clearScreen();
    returns [0] = 5; //next mode
    return returns;
  }
}
