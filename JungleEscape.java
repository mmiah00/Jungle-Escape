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

public class JungleEscape {

  public static void putString(int r, int c,Terminal t, String s){
    t.moveCursor(r,c);
    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
  }

  public static int timeRanOut(Terminal terminal) { //Message if time runs out
    putString(0, 0, terminal, "|              FAIL               |");
    putString(0, 1, terminal, "| You didn't reach the safehouse  |");
    putString(0, 2, terminal, "|in time and the zombies ate you. |");
    putString(0, 3, terminal, "|   Please press ESC to exit.     |");
    putString(0, 4, terminal, "|Run program again, to play again.|");

    boolean running = true;
    while (running) {
      Key key = terminal.readInput();
      if (key != null){
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          running = false;
          return -1;
        }
      }
    }
    terminal.clearScreen();
    return -1;
  }

  public static void main(String[] args) {

    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();

    TerminalSize size = terminal.getTerminalSize();
    terminal.setCursorVisible(false);

    boolean running = true;
    int mode = 0; //different scenes or puzzles are called depending on mode
    int [] results;
    int nextMin = 15; //passes on how many minutes are left
    int nextSec = 60; //passes on how many seconds are left

    while (running) {
      Key key = terminal.readInput();
      if (key != null){
        if (key.getKind() == Key.Kind.Escape) { //Exits game from main gmae
          terminal.exitPrivateMode();
          running = false;
        }
      }
      if (mode == -1) { //Exits game from scenes or puzzles
        terminal.exitPrivateMode();
        running = false;
      }
      if (mode == -2) { //fail message
        mode = timeRanOut(terminal);
      }
      if (mode == 0) { //forest scene or intro
        Scene1 A = new Scene1(terminal);
        results = A.playScene1(terminal);
        mode = results[0]; //change mode
        nextMin = results[1]; //update time
        nextSec = results[2];
      }
      if (mode == 1) { //river scene
        Scene2 A = new Scene2(terminal);
        results = A.playScene2(terminal, nextMin, nextSec);
        mode = results[0];
        nextMin = results[1];
        nextSec = results[2];
      }
      if (mode == 2) { //2048
        NumberPuzzle A = new NumberPuzzle();
        results = A.play2048(terminal, nextMin, nextSec);
        mode = results[0];
        nextMin = results[1];
        nextSec = results[2];
      }
      if (mode == 3) { //unlocked box
        Scene3 A = new Scene3(terminal);
        results = A.playScene3(terminal, nextMin, nextSec);
        mode = results[0];
        nextMin = results[1];
        nextSec = results[2];
      }
      if (mode == 4) { //15Puzzle
        SecondPuzzle A = new SecondPuzzle();
        results = A.playFifteen(terminal, nextMin, nextSec);
        mode = results[0];
        nextMin = results[1];
        nextSec = results[2];
      }
      if (mode == 5) { //forest scene approaching highway
        Scene4 A = new Scene4(terminal);
        results = A.playScene4(terminal, nextMin, nextSec);
        mode = results[0];
        nextMin = results[1];
        nextSec = results[2];
      }
      if (mode == 6) { //highway or frogger like game
        Frogger A = new Frogger();
        results = A.playFrogger(terminal, nextMin, nextSec);
        mode = results[0];
        nextMin = results[1];
        nextSec = results[2];
      }
      if (mode == 7) { //safehouse
        Scene5 A = new Scene5(terminal);
        results = A.playScene5(terminal, nextMin, nextSec);
        mode = results[0];
      }
    }
    terminal.exitPrivateMode();
  }
}
