import java.util.*;

public class NumberPuzzle {

	private String[][]grid;

	public NumberPuzzle() {
		grid = new String[4][4];
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				grid[r][c] = "    ";
			}
		}
	addStartNums();
	}

	private void addStartNums() {
		Random randgen = new Random();
		int randRow = randgen.nextInt(4);
		int randCol = randgen.nextInt(4);
		grid[randRow][randCol] = "2   "; //first '2' is placed randomly
		randRow = randgen.nextInt(4); //another random row and col
		randCol = randgen.nextInt(4);
		while (!(grid[randRow][randCol].equals("    "))) { //if the row and col are the same as the first
			randRow = randgen.nextInt(4); //find another random row and col
			randCol = randgen.nextInt(4);
		}
		grid[randRow][randCol] = "2   "; //place second '2'
	}

	private void inputNewNum() {
		Random randgen = new Random();
		int randRow = randgen.nextInt(4);
		int randCol = randgen.nextInt(4);
		while (!(grid[randRow][randCol].equals("    "))) { //if the row and col are the same as the first
			randRow = randgen.nextInt(4); //find another random row and col
			randCol = randgen.nextInt(4);
		}
		grid[randRow][randCol] = "2   "; //place second '2'
	}

	public String addSpaces(String s) {
		String spaces = "";
		int length = 4 - s.length();
		for (int i = 0; i < length; i++) {
			spaces = spaces + " ";
		}
		return spaces;
	}

	public void moveLeft() {
		for (int r = 0; r < 4; r++) { //for every row
			for (int c = 1; c < 4; c++) { //starting from the second col
				int orig = c;
				if (!(grid[r][c-1].equals("    ")) && grid[r][c-1].equals(grid[r][c])) { // if the first and second col are the same (and not empty)
					String newNum = "" + (Integer.parseInt(grid[r][c].trim()) * 2); //combine their numbers
					grid[r][c-1] = newNum + addSpaces(newNum);
					grid[r][c] = "    ";
					c = c -1;
				}
				while (c != 0 && grid[r][c-1].equals("    ")) { //if not the first col and space to left is empty
					grid[r][c-1] = grid[r][c]; //move to left
					grid [r][c] = "    ";
					c = c-1;
					if (c != 0 && !(grid[r][c-1].equals("    ")) && grid[r][c-1].equals(grid[r][c])) { //if number to left is the same
						String newNum = "" + (Integer.parseInt(grid[r][c].trim()) * 2); //combine their numbers
						grid[r][c-1] = newNum + addSpaces(newNum);
						grid[r][c] = "    ";
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
				if (!(grid[r][c+1].equals("    ")) && grid[r][c+1].equals(grid[r][c])) { //if third and last col are the same
					String newNum = "" + (Integer.parseInt(grid[r][c].trim()) * 2); //combine their numbers
					grid[r][c+1] = newNum + addSpaces(newNum);
					grid[r][c] = "    ";
					c = c+1;
				}
				while (c != 3 && grid[r][c+1].equals("    ")) { //if not the last col and space to right is empty
					grid[r][c+1] = grid[r][c]; //move to the right
					grid [r][c] = "    ";
					c = c+1;
					if (c != 3 && !(grid[r][c+1].equals("    ")) && grid[r][c+1].equals(grid[r][c])) { //if number to the left is the same
						String newNum = "" + (Integer.parseInt(grid[r][c].trim()) * 2); //combine their numbers
						grid[r][c+1] = newNum + addSpaces(newNum);
						grid[r][c] = "    ";
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
				if (!(grid[r-1][c].equals("    ")) && grid[r-1][c].equals(grid[r][c])) { //if first and second row are equal
					String newNum = "" + (Integer.parseInt(grid[r][c].trim()) * 2); //combine their numbers
					grid[r-1][c] = newNum + addSpaces(newNum);
					grid[r][c] = "    ";
          r = r-1;
				}
				while (r != 0 && grid[r-1][c].equals("    ")) { //if not the first row and row above is empty
					grid[r-1][c] = grid[r][c]; //move up
					grid [r][c] = "    ";
					r = r-1;
					if (r != 0 && !(grid[r-1][c].equals("    ")) && grid[r-1][c].equals(grid[r][c])) { //if row above is the same number
						String newNum = "" + (Integer.parseInt(grid[r][c].trim()) * 2); //combine their numbers
						grid[r-1][c] = newNum + addSpaces(newNum);
						grid[r][c] = "    ";
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
				if (!(grid[r+1][c].equals("    ")) && grid[r+1][c].equals(grid[r][c])) { //if third and last row are the same
					String newNum = "" + (Integer.parseInt(grid[r][c].trim()) * 2); //combine their numbers
					grid[r+1][c] = newNum + addSpaces(newNum);
					grid[r][c] = "    ";
          r = r+1;
				}
				while (r != 3 && grid[r+1][c].equals("    ")) { //if not the last row and row below is empty
					grid[r+1][c] = grid[r][c]; //move down
					grid [r][c] = "    ";
					r = r+1;
					if (r != 3 && !(grid[r+1][c].equals("    ")) && grid[r+1][c].equals(grid[r][c])) { //if row below has the same number
						String newNum = "" + (Integer.parseInt(grid[r][c].trim()) * 2); //combine their numbers
						grid[r+1][c] = newNum + addSpaces(newNum);
						grid[r][c] = "    ";
						r = r+1;
					}
				}
				r = orig;
			}
		}
		inputNewNum();
	}

	public String toString() {
		String s = "";
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				if (c == 0) {
					s = s + "|" + grid[r][c] + "|";
				}
				else {
					s = s + grid[r][c] + "|";
				}
			}
			s = s + "\n";
		}
		return s;
	}

	public static void main(String[] args) {
		NumberPuzzle A = new NumberPuzzle();
		System.out.println(A.toString());
		for (int i = 0; i < 10; i++) {
			A.moveDown();
			System.out.println(A.toString());
		}
	}
}
