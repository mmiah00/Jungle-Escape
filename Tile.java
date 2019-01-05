public class Tile {
  char num;
  int x,y;
  public Tile (char number, int xcor, int ycor) {
  	num = number;
  	x = xcor;
  	y = ycor;
  }

  public String toString () {
	   return "._______.\n|   	|\n|   "+ num + "   |\n._______.";
  }


  public void flip (Tile other) {
  	char theirnum = other.num;
  	int theirx = other.x;
  	int theiry = other.y;
  	char mynum = num;
  	int myx = x;
  	int myy = y;
  	num = theirnum;
  	x = theirx;
  	y = teiry;
  	other.num = mynum;
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
