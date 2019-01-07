public class Tile {
  char tens, ones; //num 1 is 0 or 1 for the tens digit and num 2 is the ones digit
  int x,y;

  public Tile (char num1, char num2, int xcor, int ycor) {
  	tens = num1;
    ones = num2;
  	x = xcor;
  	y = ycor;
  }

  public String toString () {
	   return "._______.\n|   	|\n|   "+ tens + ones + "   |\n._______.";
  }


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

  public void moveRight () {
    x += 1;
  }

  public void moveLeft () {
    x -= 1;
  }

  public void moveUp () {
    y -= 1;
  }

  public void moveDown () {
    y += 1;
  }
}
