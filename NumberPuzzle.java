/*
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
*/
import java.util.*;

public class NumberPuzzle {

	private String[][]grid;

	public NumberPuzzle() {
		grid = new String[9][13];
		for (int r = 0; r < 9; r++) {
			int rows = r % 2; //helps determine if row is even or odd
			for (int c = 0; c < 13; c++) {
				int cols = c % 3; //helps determine if column is divisible by 3
				if (c == 0 || (c == 12 && rows == 0)) {//|| (rows == 1 && cols == 0)) { //the first column, last column, and odd rows with columns divisible by 3
					grid[r][c] = "|";
				}
				else if (rows == 1 && cols == 0 || c == 12) {
					grid[r][c] = "  |";
				}
				else if (((r == 0 || r == 8) && (cols != 0)) || (rows == 0 && cols != 0)) { //excluding what's already filled in, the first row, last row, and even rows with columns nondivisible by 3
					grid[r][c] = "--";
				}
				else if ((r == 0 || r == 8) && (cols == 0)) {
					grid[r][c] = "-";
				}
				else if (rows == 0 && cols == 0) { //odd rows and columns divisible by 3
					grid[r][c] = "+";
				}
				else {
					grid[r][c] = " "; //everything else
				}
			}
		}
		addStartNums(); //adds 2 '2' to the grid
	}

	private void addStartNums() {
		Random randgen = new Random();
		int randRow = randgen.nextInt(4) * 2 + 1;
		int randCol = randgen.nextInt(4) * 3 + 2;
		grid[randRow][randCol] = "2"; //first '2' is placed randomly
		randRow = randgen.nextInt(4) * 2 + 1; //another random row and col
		randCol = randgen.nextInt(4) * 3 + 2;
		while (grid[randRow][randCol] != " ") { //if the row and col are the same as the first
			randRow = randgen.nextInt(4) * 2 + 1; //find another random row and col
			randCol = randgen.nextInt(4) * 3 + 2;
		}
		grid[randRow][randCol] = "2"; //place second '2'
	}

	private void inputNewNum() {
		Random randgen = new Random();
		int randRow = randgen.nextInt(4) * 2 + 1;
		int randCol = randgen.nextInt(4) * 3 + 2;
		while (grid[randRow][randCol] != " ") { //if the row and col are the same as the first
			randRow = randgen.nextInt(4) * 2 + 1; //find another random row and col
			randCol = randgen.nextInt(4) * 3 + 2;
		}
		grid[randRow][randCol] = "2"; //place second '2'
	}

	public void moveLeft() {
		for (int r = 1; r < 8; r = r + 2) { //for every row
			for (int c = 4; c < 11; c = c + 3) { //starting from the second col
				int orig = c;
				if (grid[r][c-3] != " " && grid[r][c-3].equals(grid[r][c])) { // if the first and second col are the same (and not empty)
					int newNum = Integer.parseInt(grid[r][c]) * 2; //combine their numbers
					grid[r][c-3] = "" + newNum;
					grid[r][c] = " ";
					c = c -3;
				}
				while (c != 1 && grid[r][c-3].equals(" ")) { //if not the first col and space to left is empty
					grid[r][c-3] = grid[r][c]; //move to left
					grid [r][c] = " ";
					c = c-3;
					if (c != 1 && grid[r][c-3] != " " && grid[r][c-3].equals(grid[r][c])) { //if number to left is the same
						int newNum = Integer.parseInt(grid[r][c]) * 2; //combine their numbers
						grid[r][c-3] = "" + newNum;
						grid[r][c] = " ";
						c = c -3;
					}
				}
				c = orig;
			}
		}
		inputNewNum();
	}

  public void moveRight() {
		for (int r = 1; r < 8; r = r + 2) { //for every row
			for (int c = 7; c > 0; c = c - 3) { //starting from the third col
				int orig = c;
				if (grid[r][c+3] != " " && grid[r][c+3].equals(grid[r][c])) { //if third and last col are the same
					int newNum = Integer.parseInt(grid[r][c]) * 2; //combine their numbers
					grid[r][c+3] = "" + newNum;
					grid[r][c] = " ";
					c = c+3;
				}
				while (c != 10 && grid[r][c+3].equals(" ")) { //if not the last col and space to right is empty
					grid[r][c+3] = grid[r][c]; //move to the right
					grid [r][c] = " ";
					c = c+3;
					if (c != 10 && grid[r][c+3] != " " && grid[r][c+3].equals(grid[r][c])) { //if number to the left is the same
						int newNum = Integer.parseInt(grid[r][c]) * 2; //combine their numbers
						grid[r][c+3] = "" + newNum;
						grid[r][c] = " ";
						c = c+3;
					}
				}
				c = orig;
			}
		}
		inputNewNum();
	}

  public void moveUp() {
		for (int c = 2; c < 12; c = c + 3) { //for every column
			for (int r = 3; r < 8; r = r + 2) { //starting with second row
				int orig = r;
				if (grid[r-2][c] != " " && grid[r-2][c] == grid[r][c]) { //if first and second row are equal
					int newNum = Integer.parseInt(grid[r][c]) * 2; //combine their numbers
					grid[r-2][c] = "" + newNum;
					grid[r][c] = " ";
          r = r-2;
				}
				while (r != 1 && grid[r-2][c] == " ") { //if not the first row and row above is empty
					grid[r-2][c] = grid[r][c]; //move up
					grid [r][c] = " ";
					r = r-2;
					if (r != 1 && grid[r-2][c] != " " && grid[r-2][c] == grid[r][c]) { //if row above is the same number
						int newNum = Integer.parseInt(grid[r][c]) * 2; //combine their numbers
						grid[r-2][c] = "" + newNum;
						grid[r][c] = " ";
						r = r-2;
					}
				}
				r = orig;
			}
		}
		inputNewNum();
	}

  public void moveDown() {
		for (int c = 2; c < 12; c = c + 3) { //for every column
			for (int r = 5; r > 0; r = r - 2) { //starting with third row
				int orig = r;
				if (grid[r+2][c] != " " && grid[r+2][c].equals(grid[r][c])) { //if third and last row are the same
					int newNum = Integer.parseInt(grid[r][c]) * 2; //combine their numbers
					grid[r+2][c] = "" + newNum;
					grid[r][c] = " ";
          r = r+2;
				}
				while (r != 7 && grid[r+2][c].equals(" ")) { //if not the last row and row below is empty
					grid[r+2][c] = grid[r][c]; //move down
					grid [r][c] = " ";
					r = r+2;
					if (r != 7 && grid[r+2][c] != " " && grid[r+2][c].equals(grid[r][c])) { //if row below has the same number
						int newNum = Integer.parseInt(grid[r][c]) * 2; //combine their numbers
						grid[r+2][c] = "" + newNum;
						grid[r][c] = " ";
						r = r+2;
					}
				}
				r = orig;
			}
		}
		inputNewNum();
	}

  public boolean isComplete() { //checks if there are anymore possible moves
    boolean complete = true;
    for (int r = 1; r < 9; r = r+2) {
      for (int c = 2; c < 13; c = c+3) {
        if (grid[r][c] == " " || //if there is empty space
            (c != 2 && grid[r][c-3] == grid[r][c]) || //can combine using moveLeft
            (c != 11 && grid[r][c+3] == grid[r][c]) || //can combine using moveRight
            (r != 1 && grid[r-2][c] == grid[r][c]) || //can combine using moveUp
            (r != 7 && grid[r+2][c] == grid[r][c])) { //can combine using moveDown
          return false; //then not complete
        }
      }
    }
    return complete;
  }

	public String toString() {
		String s = "";
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 13; c++) {
				s = s + grid[r][c];
			}
			s = s + "\n";
		}
		return s;
	}

	public static void main(String[] args) {
		NumberPuzzle A = new NumberPuzzle();
		System.out.println(A.toString());
	}

}
