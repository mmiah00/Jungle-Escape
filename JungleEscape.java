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

  public static void main(String[] args) {

    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();

    TerminalSize size = terminal.getTerminalSize();
    terminal.setCursorVisible(false);

    boolean running = true;
    long tStart = System.currentTimeMillis();
    long minLeft = 15;
    long lastMin = 0;
    long secLeft = 60;
    long lastSec = 0;
    int mode = 0;

    while (running) {
      if (minLeft == 0 && secLeft ==0) {
        running = false;
      }
      //else {
        long tEnd = System.currentTimeMillis();
  			long milli = tEnd - tStart;
        if (milli/60000 > lastMin ) {
          lastMin = milli/60000;
          minLeft = 15 - lastMin;
        }
        if (milli % 60000 / 1000 > lastSec) {
          lastSec = milli % 60000 / 1000;
          secLeft = 60 - lastSec;
        }
  			putString(0, 0, terminal, "Time Left: " + minLeft + ":" + secLeft);

        Key key = terminal.readInput();
        if (key != null){
          if (key.getKind() == Key.Kind.Escape) {
            terminal.exitPrivateMode();
            running = false;
          }
        }
        if (mode == -1) {
          terminal.exitPrivateMode();
          running = false;
        }
        if (mode == 0) {
          Scene1 A = new Scene1(terminal);
          mode = A.playScene1(terminal);
        }
        if (mode == 1) {
          Scene2 A = new Scene2(terminal);
          mode = A.playScene2(terminal);
        }
        if (mode == 2) {
          NumberPuzzle A = new NumberPuzzle();
          mode = A.play2048(terminal);
        }
        if (mode == 3) {
          Scene3 A = new Scene3(terminal);
          mode = A.playScene3(terminal);
        }
        if (mode == 4) {
          SecondPuzzle A = new SecondPuzzle();
          mode = A.playFifteen(terminal);
        }
        if (mode == 5) {
          Scene4 A = new Scene4(terminal);
          mode = A.playScene4(terminal);
        }
        if (mode == 6) {
          Frogger A = new Frogger();
          mode = A.playFrogger(terminal);
        }
        if (mode == 7) {
          Scene5 A = new Scene5(terminal);
          mode = A.playScene5(terminal);
        }
      //}
    }
    terminal.exitPrivateMode();
  }
}
