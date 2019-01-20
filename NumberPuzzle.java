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

	public void reset() {
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

	public String addSpaces(String s) { //adds spaces to account for the 4 spaces that should be in each position
		String spaces = "";
		int length = 3 - s.length();
		for (int i = 0; i < length; i++) {
			spaces = spaces + " ";
		}
		return spaces;
	}

	public void moveLeft() {
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

	public void moveRight() {
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

	public void moveUp() {
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

  public void moveDown() {
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

	public boolean isComplete() { //checks if there are anymore possible moves
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

	public boolean beatGame() {
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 4; c++) {
				if (grid[r][c].equals("128")) {
					return true;
				}
			}
		}
		return false;
	}

	public String toString() { //prints 2-d array grid with a grid around it to mimic game
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
				s = s + "\n|---+---+---+---|\n";
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

	public static int play2048(Terminal terminal) {
		NumberPuzzle A = new NumberPuzzle();
		putString(0, 0, terminal, A.toString());
		putString(0, 11, terminal, "|Use the arrow keys |");
		putString(0, 12, terminal, "|to combine numbers |");
		putString(0, 13, terminal, "|   and reach 256   |");



			//boolean gameNotDone = true;
			while (!(A.beatGame())) {
				if (!(A.isComplete())) {
					putString(0, 0, terminal, A.toString());
					Key key = terminal.readInput();
					if (key != null){
						if (key.getKind() == Key.Kind.Escape) {
							terminal.exitPrivateMode();
							gameNotDone = false;
							return -1;
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
				else {
					A.reset();
				}
		}
		terminal.clearScreen();
		return 3;
	}


		 /*
		while (gameNotDone) {
			gameNotDone = !(A.isComplete());
			putString(0, 0, terminal, A.toString());
			Key key = terminal.readInput();
			if (key != null){
				if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
					gameNotDone = false;
					return -1;
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
		terminal.clearScreen();
		return 3;
		//
		if (A.beatGame()) {
			terminal.clearScreen();
		}
		else {
			A = new NumberPuzzle();
		}//
	}
*/
/*
	public static void main(String[] args) {
		Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();

		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);

		NumberPuzzle A = new NumberPuzzle();
		boolean running = true;
		while (running) {
			running = !(A.isComplete());
			putString(0, 0, terminal, A.toString());
			Key key = terminal.readInput();
			if (key != null){
				if (key.getKind() == Key.Kind.Escape) {
					terminal.exitPrivateMode();
					running = false;
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
		terminal.exitPrivateMode();
	}
	*/

}
