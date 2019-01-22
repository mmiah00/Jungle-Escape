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

public class NumberPuzzle {

	private String[][]grid;

	public NumberPuzzle() {
		grid = new String[4][4];
		reset();
	}

	private void reset() { //clears board and places two random twos on the board
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				grid[r][c] = "   "; //4 spaces for 4 possible digits
			}
		}
		addStartNums(); //adds two "2" to the grid
	}

	private void addStartNums() {
		Random randgen = new Random();
		int randRow = randgen.nextInt(4);
		int randCol = randgen.nextInt(4);
		grid[randRow][randCol] = "  2"; //first "2" is placed randomly
		randRow = randgen.nextInt(4); //another random row and col
		randCol = randgen.nextInt(4);
		while (!(grid[randRow][randCol].equals("   "))) { //if the row and col are the same as the first
			randRow = randgen.nextInt(4); //find another random row and col
			randCol = randgen.nextInt(4);
		}
		grid[randRow][randCol] = "  2"; //place second "2"
	}

	private void inputNewNum() {
		Random randgen = new Random();
		int randRow = randgen.nextInt(4);
		int randCol = randgen.nextInt(4);
		while (!(grid[randRow][randCol].equals("   "))) { //if the row and col are the same as the first
			randRow = randgen.nextInt(4); //find another random row and col
			randCol = randgen.nextInt(4);
		}
		grid[randRow][randCol] = "  2"; //place second "2"
	}

	private String addSpaces(String s) { //adds spaces to account for the 4 spaces that should be in each position
		String spaces = "";
		int length = 3 - s.length();
		for (int i = 0; i < length; i++) {
			spaces = spaces + " ";
		}
		return spaces;
	}

	private void moveLeft() {
		for (int r = 0; r < 4; r++) { //for every row
			for (int c = 1; c < 4; c++) { //starting from the second col
				int orig = c;
				if (!(grid[r][c-1].equals("   ")) && grid[r][c-1].equals(grid[r][c])) { // if the first and second col are the same (and not empty)
					String newNum = "" + (Integer.parseInt(grid[r][c].trim()) * 2); //combine their numbers
					grid[r][c-1] = addSpaces(newNum) + newNum;
					grid[r][c] = "   ";
					c = c -1;
				}
				while (c != 0 && grid[r][c-1].equals("   ")) { //if not the first col and space to left is empty
					grid[r][c-1] = grid[r][c]; //move to left
					grid [r][c] = "   ";
					c = c-1;
					if (c != 0 && !(grid[r][c-1].equals("   ")) && grid[r][c-1].equals(grid[r][c])) { //if number to left is the same
						String newNum = "" + (Integer.parseInt(grid[r][c].trim()) * 2); //combine their numbers
						grid[r][c-1] = addSpaces(newNum) + newNum;
						grid[r][c] = "   ";
						c = c -1;
					}
				}
				c = orig;
			}
		}
		inputNewNum();
	}

	private void moveRight() {
		for (int r = 0; r < 4; r++) { //for every row
			for (int c = 2; c > -1; c--) { //starting from the third col
				int orig = c;
				if (!(grid[r][c+1].equals("   ")) && grid[r][c+1].equals(grid[r][c])) { //if third and last col are the same
					String newNum = "" + (Integer.parseInt(grid[r][c].trim()) * 2); //combine their numbers
					grid[r][c+1] = addSpaces(newNum) + newNum;
					grid[r][c] = "   ";
					c = c+1;
				}
				while (c != 3 && grid[r][c+1].equals("   ")) { //if not the last col and space to right is empty
					grid[r][c+1] = grid[r][c]; //move to the right
					grid [r][c] = "   ";
					c = c+1;
					if (c != 3 && !(grid[r][c+1].equals("   ")) && grid[r][c+1].equals(grid[r][c])) { //if number to the left is the same
						String newNum = "" + (Integer.parseInt(grid[r][c].trim()) * 2); //combine their numbers
						grid[r][c+1] = addSpaces(newNum) + newNum;
						grid[r][c] = "   ";
						c = c+1;
					}
				}
				c = orig;
			}
		}
		inputNewNum();
	}

	private void moveUp() {
		for (int c = 0; c < 4; c++) { //for every column
			for (int r = 1; r < 4; r++) { //starting with second row
				int orig = r;
				if (!(grid[r-1][c].equals("   ")) && grid[r-1][c].equals(grid[r][c])) { //if first and second row are equal
					String newNum = "" + (Integer.parseInt(grid[r][c].trim()) * 2); //combine their numbers
					grid[r-1][c] = addSpaces(newNum) + newNum;
					grid[r][c] = "   ";
          r = r-1;
				}
				while (r != 0 && grid[r-1][c].equals("   ")) { //if not the first row and row above is empty
					grid[r-1][c] = grid[r][c]; //move up
					grid [r][c] = "   ";
					r = r-1;
					if (r != 0 && !(grid[r-1][c].equals("   ")) && grid[r-1][c].equals(grid[r][c])) { //if row above is the same number
						String newNum = "" + (Integer.parseInt(grid[r][c].trim()) * 2); //combine their numbers
						grid[r-1][c] = addSpaces(newNum) + newNum;
						grid[r][c] = "   ";
						r = r-1;
					}
				}
				r = orig;
			}
		}
		inputNewNum();
	}

  private void moveDown() {
		for (int c = 0; c < 4; c++) { //for every column
			for (int r = 2; r > -1; r--) { //starting with third row
				int orig = r;
				if (!(grid[r+1][c].equals("   ")) && grid[r+1][c].equals(grid[r][c])) { //if third and last row are the same
					String newNum = "" + (Integer.parseInt(grid[r][c].trim()) * 2); //combine their numbers
					grid[r+1][c] = addSpaces(newNum) + newNum;
					grid[r][c] = "   ";
          r = r+1;
				}
				while (r != 3 && grid[r+1][c].equals("   ")) { //if not the last row and row below is empty
					grid[r+1][c] = grid[r][c]; //move down
					grid [r][c] = "   ";
					r = r+1;
					if (r != 3 && !(grid[r+1][c].equals("   ")) && grid[r+1][c].equals(grid[r][c])) { //if row below has the same number
						String newNum = "" + (Integer.parseInt(grid[r][c].trim()) * 2); //combine their numbers
						grid[r+1][c] = addSpaces(newNum) + newNum;
						grid[r][c] = "   ";
						r = r+1;
					}
				}
				r = orig;
			}
		}
		inputNewNum();
	}

	private boolean isComplete() { //checks if there are anymore possible moves
    boolean complete = true;
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 4; c++) {
        if (grid[r][c].equals("   ") || //if there is empty space
            (c != 0 && grid[r][c-1].equals(grid[r][c])) || //can combine using moveLeft
            (c != 3 && grid[r][c+1].equals(grid[r][c])) || //can combine using moveRight
            (r != 0 && grid[r-1][c].equals(grid[r][c])) || //can combine using moveUp
            (r != 3 && grid[r+1][c].equals(grid[r][c]))) { //can combine using moveDown
          return false; //then not complete
        }
      }
    }
    return complete;
  }

	private boolean beatGame() { //if player reachs 256 they can move on
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 4; c++) {
				if (grid[r][c].equals("512")) {
					return true;
				}
			}
		}
		return false;
	}

	private String toString() { //prints 2-d array grid with a grid around it to mimic game
		String s = "|---------------|\n";
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (c == 0) {
					s = s + "|" + grid[r][c] + "|";
				}
				else {
					s = s + grid[r][c] + "|";
				}
			}
			if (r == 3) {
				s = s + "\n|---------------|";
			}
			else {
				s = s + "\n|---------------|\n";
			}
		}
		return s;
	}

	private static void putString(int r, int c,Terminal t, String s){ //displays String on terminal
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}

	private static int[] play2048(Terminal terminal, int beginMin, int beginSec) {
		NumberPuzzle A = new NumberPuzzle();
		putString(0, 1, terminal, A.toString());
		putString(0, 12, terminal, "| Use the arrow keys |");
		putString(0, 13, terminal, "| to combine numbers |");
		putString(0, 14, terminal, "|   and reach 512.   |");

		boolean gameNotDone = true;
		int [] returns = new int [3];
		long lastTime =  System.currentTimeMillis();
    long currentTime = lastTime;
    long timer = 0;
    boolean firstPass = true;

		while (gameNotDone) {
			gameNotDone = (!(A.beatGame())); //player has not beaten game
			if (A.isComplete() && !gameNotDone) { //if board is filled but 512 not on board, reset
				A.reset();
			}
			else { //moves are still possible
				putString(0, 1, terminal, A.toString());
				Key key = terminal.readInput();
				if (key != null){
					if (key.getKind() == Key.Kind.Escape) { //exits game
						terminal.exitPrivateMode();
						gameNotDone = false;
						returns [0] = -1;
		        return returns;
					}
					if (key.getKind() == Key.Kind.ArrowLeft) {
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
			}

			lastTime = currentTime;
      currentTime = System.currentTimeMillis();
      timer += (currentTime -lastTime); //changes time
      int minLeft = beginMin - (int)(timer/60000); //changes min left
      String minPassed = String.format("%02d", minLeft);
			int secLeft; //changes sec left
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
      returns[1] = minLeft; //passes on min left at end
      returns[2] = secLeft; //passes on sec left at end

      if (minLeft == 0 && secLeft == 1) { //if time runs out
        gameNotDone = false;
        returns [0] = -2; //fail message
        return returns;
      }
    }
    terminal.clearScreen();
    returns [0] = 3; //next mode
    return returns;
  }
}
