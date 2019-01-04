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

public class Tile {
  char num;
  int x,y;
  public Tile (char number, int xcor, int ycor) {
    num = number;
    x = xcor;
    y = ycor;
  }

  public String toString () {
    return "._______.\n|       |\n|   "+ num + "   |\n._______.";
  }

  public static void switch (Tile other) {
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
}
