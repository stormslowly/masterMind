package masterMind;

import masterMind.gamePlay.GameEngine;
import masterMind.gamePlay.GuessChecker;
import masterMind.gamePlay.Score;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;

public class GuesserTest {

  private GuessChecker failingChecker;

  @Before
  public void setUp() throws Exception {

    failingChecker = new GuessChecker() {
      public boolean shouldTry(String guess) {
        return false;
      }

      public void addScore(String guess, Score score) {
      }
    };
  }

  @Test
  public void whenNoGuessesAreValid_nextGuessIsNil() throws Exception {
    GameEngine.Guesser guesser = new GameEngine.Guesser(failingChecker);
    assertNull(guesser.getNextGuess());
  }

  @Test
  public void WhenOnlyOneGuessIsValid_nextGuessIsThatOne() throws Exception {
    GameEngine.Guesser guesser = new GameEngine.Guesser(new SingleChecker("BEEF"));
    assertEquals("BEEF", guesser.getNextGuess());
    assertNull(guesser.getNextGuess());
  }

  @Test
  public void makeGuess() throws Exception {
    assertEquals("AAAA", GameEngine.Guesser.makeGuess(0));
    assertEquals("AAAB", GameEngine.Guesser.makeGuess(1));
    assertEquals("FFFF", GameEngine.Guesser.makeGuess(GameEngine.MAX_CODES - 1));
    assertNull(GameEngine.Guesser.makeGuess(GameEngine.MAX_CODES));
    assertNull(GameEngine.Guesser.makeGuess(-1));
  }


}

class SingleChecker implements GuessChecker {
  private String validGuess;

  public SingleChecker(String validGuess) {
    this.validGuess = validGuess;
  }

  public boolean shouldTry(String guess) {
    return guess.equals(validGuess);
  }

  public void addScore(String guess, Score score) {
  }
}
