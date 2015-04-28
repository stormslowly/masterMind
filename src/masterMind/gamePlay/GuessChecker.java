package masterMind.gamePlay;

public interface GuessChecker {
  boolean shouldTry(String guess);

  void addScore(String guess, Score score);
}
