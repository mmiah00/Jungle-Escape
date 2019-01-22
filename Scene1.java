//API : http://mabe02.github.io/lanterna/apidocs/2.1/
import com.googlecode.lanterna.terminal.Terminal.SGR;
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

public class Scene1 extends Scene {

  public Scene1 (Terminal t) {
    path = new String[35]; //place for player to move
    t.applyForegroundColor (Terminal.Color.GREEN);
    putString(0, 1, t, "            ,@@@@@@@,              "); //background
    putString(0, 2, t, "    ,,,,   ,@@@@@@/@@,  .oo8888o.  ");
    putString(0, 3, t, " ,&%%&%&&%,@@@@@/@@@@@@,8888\\88/8o ");
    putString(0, 4, t, ",%&\\%&&%&&%,@@@\\@@@/@@@88\\88888/88’");
    putString(0, 5, t, "%&&%&%&/%&&%@@\\@@/  /@@@88888\\88888’");
    putString(0, 6, t, "%&&%/ %&%%&&@@\\ V /@@’ ’88\\8 ’/88’ ");
    putString(0, 7, t, "‘&%\\ ‘ /%&’    |.|        \\ ‘|8’   ");
    putString(0, 8, t, "    |o|        | |         | |     ");
    putString(0, 9, t, "    |.|        | |         | |     ");
    putString(0, 10, t, "_\\\\/ ._\\//_/__/  ,\\_//__\\\\/.  \\_//_");
    t.applyForegroundColor (Terminal.Color.DEFAULT);
    path[0] = "o";
    for (int i = 1; i < 35; i++) { //makes blank path
      path[i] = " ";
    }
    putString(0, 11, t, toString());
    //Gives prompt and directions
    putString(0, 13, t, "|    Mission: Get out of the jungle    |");
    putString(0, 14, t, "| before the zombies catch up with you.|");
    putString(0, 15, t, "| Use your arrow keys to run forward   |");
    putString(0, 16, t, "|       and reach the safehouse.       |");
  }

  public static int [] playScene1(Terminal terminal) {
    Scene1 A = new Scene1(terminal);
    int [] returns = new int [3]; //array with 3 numbers that will be returned

    boolean pathNotDone = true;
    long lastTime =  System.currentTimeMillis();
    long currentTime = lastTime;
    long timer = 0;

    while (pathNotDone) {
      pathNotDone = !(A.isLastSpot()); //not at end of path

      putString(0, 11, terminal, A.toString());
      Key key = terminal.readInput();
      if (key != null){
        if (key.getKind() == Key.Kind.Escape) { //ESC escapes game
          terminal.exitPrivateMode();
          pathNotDone = false;
          returns[0] = -1;
          return returns;
        }
        if (key.getKind() == Key.Kind.ArrowLeft) { //moves player left and right
          A.moveLeft();
        }
        if (key.getKind() == Key.Kind.ArrowRight) {
          A.moveRight();
        }
      }

      lastTime = currentTime;
      currentTime = System.currentTimeMillis();
      timer += (currentTime -lastTime); //count down timer
      A.setMinLeft(14 - (int)(timer/60000)); //number of minutes left
      String minPassed = String.format("%02d", A.getMinLeft());
      A.setSecLeft(60 - (int)(timer%60000/1000)); //number of seconds left
      String secPassed = String.format("%02d", A.getSecLeft());
      if (A.getSecLeft() == 60) { //special case
        A.setMinLeft(15 - (int)(timer/60000));
        minPassed = String.format("%02d", A.getMinLeft());
        secPassed = "00";
      }
      putString(0,0,terminal, "Time Left: "+ minPassed + ":" + secPassed);
      returns[1] = A.getMinLeft(); //to be passed onto next scene
      returns[2] = A.getSecLeft();

      if (A.getMinLeft() == 0 && A.getSecLeft() == 1) { //if time runs out then fail
        pathNotDone = false;
        returns [0] = -2;
        return returns;
      }
    }
    terminal.clearScreen();
    returns [0] = 1; //return next mode
    return returns;
  }

}
