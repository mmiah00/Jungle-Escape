public class Frogger {
  private String[][] world;
  public int lives;
  public Frogger () {
    world = new String [5][5];
    lives = 3;
  }

  public static String carString () {
    //String car = "         __\n         ____|__|___\n       _|  -     -  |_\n         |_______________|\n        (/)        (/)        ";
    String car = "_________\n(/)   (/)\n";
    return car;
  }

  private void moveCars () {
    while (lives > 0) {
      for (int x = 0; x < world.length; x ++) {
        for (int y =0; y < world[x].length; x ++) {
          if (world[x][y] != null) {
            world [x][y + 1] = carString ();
            world [x][y] = null;
          }
          System.out.println (toString ());
        }
      }
    }
  }

  public String toString ()  {
    String ans = "";
    for (int x = 0 ; x < world.length; x ++) {
      for (int y = 0; y < world[x].length; y ++) {
        if (world [x][y] == carString()) {
          ans += world [x][y];
          //ans += "car";
        }

        else {
          ans += "         \n         \n";
          //ans += "-";
        }
        if (y == world.length - 1 ) {
          ans += "\n";
        }
      }
    }
    return ans;
  }

  public static void main (String[] args) {
    Frogger frog = new Frogger ();
    //System.out.println (frog.toString ());
    //frog.world[0][0] = carString();
    frog.world[3][2] = carString();
    System.out.println (frog.toString ());
    //frog.moveCars();
  }

}
