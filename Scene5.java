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

public class Scene5 extends Scene {
  public Scene5 (Terminal t) {
    display(t);
  }

  public void display(Terminal terminal) {
    putString(0, 1, terminal, "           x");
    putString(0, 2, terminal, ".-. _______|");
    putString(0, 3, terminal, "|=|/     /  \\");
    putString(0, 4, terminal, "| |_____|_\"\"_|");
    putString(0, 5, terminal, "|_|_[X]_|____|");
    putString(0, 7, terminal, "| Great Job! You are SAFE! |");
    putString(0, 8, terminal, "| Your escape took time t  |");
    putString(0, 9, terminal, "|                          |");
    putString(0, 10, terminal, "|  Press ESC to exit game  |");
  }

  public static int [] playScene5(Terminal terminal, int beginMin, int beginSec) {
    Scene5 A = new Scene5(terminal);
    int [] returns = new int [3];

    boolean running = true;
    long lastTime =  System.currentTimeMillis();
    long currentTime = lastTime;
    long timer = 0;
    boolean firstPass = true;

    while (running) {
      A.display(terminal);
      Key key = terminal.readInput();
      if (key != null){
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          running = false;
          returns[0] = -1;
          return returns;
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
        running = false;
        returns [0] = -1;
        return returns;
      }
    }
    terminal.clearScreen();
    returns [0] = -1;
    return returns;
  }
}
