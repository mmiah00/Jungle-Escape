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

public class Scene4 extends Scene {

  public Scene4 (Terminal t) {
    path = new String[35];
    putString(0, 1, t, "            ,@@@@@@@,              ");
    putString(0, 2, t, "    ,,,,   ,@@@@@@/@@,  .oo8888o.  ");
    putString(0, 3, t, " ,&%%&%&&%,@@@@@/@@@@@@,8888\\88/8o ");
    putString(0, 4, t, ",%&\\%&&%&&%,@@@\\@@@/@@@88\\88888/88’");
    putString(0, 5, t, "%&&%&%&/%&&%@@\\@@/  /@@@88888\\88888’");
    putString(0, 6, t, "%&&%/ %&%%&&@@\\ V /@@’ ’88\\8 ’/88’ ");
    putString(0, 7, t, "‘&%\\ ‘ /%&’    |.|        \\ ‘|8’   ");
    putString(0, 8, t, "    |o|        | |         | |     ");
    putString(0, 9, t, "    |.|        | |         | |     ");
    putString(0, 10, t, "_\\\\/ ._\\//_/__/  ,\\_//__\\\\/.  \\_//_");

    path[0] = "o";
    for (int i = 1; i < 35; i++) {
      path[i] = " ";
    }
    putString(0, 11, t, toString());

    putString(0, 13, t, "| Great! You crossed the river. Keep|");
    putString(0, 14, t, "|   running, you've almost reached  |");
    putString(0, 15, t, "|            the safehouse.         |");
    putString(0, 16, t, "|  What's that up ahead? A highway? |");
    putString(0, 17, t, "|  Quick! Cross the highway without |");
    putString(0, 18, t, "|getting hit by a zombie-driven car!|");

  }

  public static int [] playScene4(Terminal terminal, int beginMin, int beginSec) {
    Scene4 A = new Scene4(terminal);
    int [] returns = new int [3];

    boolean pathNotDone = true;
    long lastTime =  System.currentTimeMillis();
    long currentTime = lastTime;
    long timer = 0;
    boolean firstPass = true;

    while (pathNotDone) {
      pathNotDone = !(A.isLastSpot());
      putString(0, 11, terminal, A.toString());
      Key key = terminal.readInput();
      if (key != null){
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          pathNotDone = false;
          returns[0] = -1;
          return returns;
        }
        if (key.getKind() == Key.Kind.ArrowLeft) {
          A.moveLeft();
        }
        if (key.getKind() == Key.Kind.ArrowRight) {
          A.moveRight();
        }
      }

      lastTime = currentTime;
      currentTime = System.currentTimeMillis();
      timer += (currentTime -lastTime);
      A.setMinLeft(beginMin - (int)(timer/60000));
      String minPassed = String.format("%02d", A.getMinLeft());
      if ((int)(timer%60000/1000) > beginSec) {
        firstPass = false;
      }
      if (firstPass) {
        A.setSecLeft(beginSec -(int)(timer%60000/1000));
      }
      else {
        A.setSecLeft(60 - (int)(timer%60000/1000));
      }
      String secPassed = String.format("%02d", A.getSecLeft());
      if (A.getSecLeft() == 60) {
        A.setMinLeft(beginMin - (int)(timer/60000));
        minPassed = String.format("%02d", A.getMinLeft());
        secPassed = "00";
      }
      putString(0,0,terminal, "Time Left: "+ minPassed + ":" + secPassed);
      returns[1] = A.getMinLeft();
      returns[2] = A.getSecLeft();

      if (A.getMinLeft() == 0 && A.getSecLeft() == 1) {
        pathNotDone = false;
        returns [0] = -2;
        return returns;
      }
    }
    terminal.clearScreen();
    returns [0] = 6;
    return returns;
  }
}
