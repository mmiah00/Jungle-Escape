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
  private int row;
  private int col;

	public SecondPuzzle() {
		grid = new String[4][4];
		row = 3;
    col = 3;
    fillGrid();
		scramble(); //adds two "2" to the grid
	}

  public void fillGrid() {
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 4; c++) {
        grid[r][c] = addSpaces("" + (4*r+c+1)) + (4*r+c+1);
      }
    }
    grid[3][3] = "  ";
  }

  public void scramble() {
    Random randgen = new Random();
    for (int i = 0; i < 20; i++) {
      int direction = randgen.nextInt(4);
      if (direction == 0) {
        moveLeft();
      }
      if (direction == 1) {
        moveRight();
      }
      if (direction == 2) {
        moveUp();
      }
      if (direction == 3) {
        moveDown();
      }
    }
  }

	public String addSpaces(String s) { //adds spaces to account for the 4 spaces that should be in each position
		String spaces = "";
		int length = 2 - s.length();
		for (int i = 0; i < length; i++) {
			spaces = spaces + " ";
		}
		return spaces;
	}

	public void moveLeft() {
		if (col != 3) {
      String newNum = grid[row][col+1].trim();
      grid[row][col] = addSpaces(newNum) + newNum;
      grid[row][col+1] = "  ";
			col++;
    }
	}

	public void moveRight() {
		if (col != 0) {
      String newNum = grid[row][col-1].trim();
      grid[row][col] = addSpaces(newNum) + newNum;
      grid[row][col-1] = "  ";
			col--;
    }
	}

	public void moveUp() {
    if (row != 3) {
      String newNum = grid[row+1][col].trim();
      grid[row][col] = addSpaces(newNum) + newNum;
      grid[row+1][col] = "  ";
			row++;
    }
	}

  public void moveDown() {
    if (row != 0) {
      String newNum = grid[row-1][col].trim();
      grid[row][col] = addSpaces(newNum) + newNum;
      grid[row-1][col] = "  ";
			row--;
    }
	}

	public boolean isComplete() { //checks if there are anymore possible moves
    boolean complete = true;
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 4; c++) {
        String correct = addSpaces("" + (4*r+c+1)) + (4*r+c+1);
        if (!(grid[r][c].equals(16)) && !(grid[r][c].equals(4*r+c+1))) {
          return false;
        }
      }
    }
    return complete;
  }
/*
	public boolean beatGame() {
    for (int r = 0; r < 4; r++) {
      for (int c = 0; c < 4; c++) {
				if (grid[r][c].equals("2048")) {
					return true;
				}
			}
		}
		return false;
	}
*/
	public String toString() { //prints 2-d array grid with a grid around it to mimic game
		String s = "|-----------|\n";
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
				s = s + "\n|-----------|";
			}
			else {
				s = s + "\n|--+--+--+--|\n";
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

	public static void main(String[] args) {
		Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();

    TerminalSize size = terminal.getTerminalSize();
    terminal.setCursorVisible(false);

		SecondPuzzle A = new SecondPuzzle();
		putString(0, 0, terminal, A.toString());
		putString(0, 11, terminal, "|Use the arrow keys |");
		putString(0, 12, terminal, "|to combine numbers |");
		putString(0, 13, terminal, "|  and reach 2048   |");

		boolean gameNotDone = true;
		while (gameNotDone) {
			gameNotDone = !(A.isComplete());
			putString(0, 0, terminal, A.toString());
			Key key = terminal.readInput();
			if (key != null){
				if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          gameNotDone = false;
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
		/*
		if (A.beatGame()) {
			terminal.clearScreen();
		}
		else {
			A = new NumberPuzzle();
		}*/
	}

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
