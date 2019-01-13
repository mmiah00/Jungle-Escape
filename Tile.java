public class Tile {
  private char tens, ones; //num 1 is 0 or 1 for the tens digit and num 2 is the ones digit
  private int x,y;

  public Tile (char num1, char num2, int xcor, int ycor) {
  	tens = num1;
    ones = num2;
  	x = xcor;
  	y = ycor;
  }
  public int xcor () {
    return x;
  }

  public int ycor () {
    return y;
  }

  public char tens () {
    return tens;
  }

  public char ones () {
    return ones;
  }

  public void setX (int value) {
    x = value;
  }

  public void setY (int value) {
    y = value;
  }

  public String toString () {
	   return ".________.\n|        |\n|   "+ tens + ones + "   |\n|        |\n.________.";
  }
}
