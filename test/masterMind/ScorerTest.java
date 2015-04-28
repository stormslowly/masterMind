package masterMind;

import masterMind.gamePlay.Score;
import masterMind.strategy.Scorer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ScorerTest {
  private Score score(int inPosition, int inCode) {
    return new Score(inPosition, inCode);
  }

  @Test
  public void withNoMatches_scoreIsNull() throws Exception {
    assertInPosition(0, "AAAA", "BBBB");
  }

  @Test
  public void inPosition() throws Exception {
    assertInPosition(0, "ABCD", "DCBA");
    assertInPosition(1, "ABBB", "ACCC");
    assertInPosition(2, "AABB", "AACC");
    assertInPosition(2, "ABAB", "ACAC");
    assertInPosition(3, "DEEE", "AEEE");
    assertInPosition(3, "BABB", "BCBB");
    assertInPosition(4, "AAAA", "AAAA");
    assertInPosition(1, "ABBB", "AAAA");
  }

  @Test
  public void notInPosition() throws Exception {
    assertNotInPosition(0, "AAAA", "BBBB");
    assertNotInPosition(0, "ABBB", "ACCC");
    assertNotInPosition(1, "ABBB", "CACC");
    assertNotInPosition(4, "ABCD", "DCBA");
    assertNotInPosition(2, "ABBA", "CAAC");
    assertNotInPosition(3, "ABCD", "CADF");
    assertNotInPosition(0, "ABBB", "AAAA");
    assertNotInPosition(1, "ABBB", "CAAA");
    assertNotInPosition(1, "AABB", "CCAC");

  }

  private void assertInPosition(int inPosition, String code, String guess) {
    assertEquals(inPosition, Scorer.scoreGuess(code, guess).inPosition());
  }

  private void assertNotInPosition(int inPosition, String code, String guess) {
    assertEquals(inPosition, Scorer.scoreGuess(code, guess).inCode());
  }

}
