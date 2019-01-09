public class Tile {
  private static char tens, ones; //num 1 is 0 or 1 for the tens digit and num 2 is the ones digit
  private static int x,y;

  public Tile (char num1, char num2, int xcor, int ycor) {
  	tens = num1;
    ones = num2;
  	x = xcor;
  	y = ycor;
  }

  public static int xcor () {
    return x;
  }

  public static int ycor () {
    return y;
  }

  public static char tens () {
    return tens;
  }

  public static char ones () {
    return ones;
  }

  public String toString () {
	   return ".________.\n|        |\n|   "+ tens + ones + "   |\n|        |\n.________.";
  }

  /*
  public void flip (Tile other) {
  	char theirtens = other.tens;
    char theirones = other.ones;
  	int theirx = other.x;
  	int theiry = other.y;
  	char mytens = tens;
    char myones = ones;
  	int myx = x;
  	int myy = y;
  	tens = theirtens;
    ones = theirones;
  	x = theirx;
  	y = theiry;
  	other.tens = mytens;
    other.ones = myones;
  	other.x = myx;
  	other.y = myy;
  }
  */

  public static void main (String[] args) {
    Tile test = new Tile ('1','2', 10,10);
    System.out.println (test.toString ());
  }
}
