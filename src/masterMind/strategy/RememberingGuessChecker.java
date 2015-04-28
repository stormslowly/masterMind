package masterMind.strategy;

import masterMind.gamePlay.Score;
import masterMind.gamePlay.GuessChecker;

import java.util.ArrayList;
import java.util.List;

public class RememberingGuessChecker implements GuessChecker {
  private List<ScoreRecord> scoreHistory = new ArrayList<ScoreRecord>();

  public boolean shouldTry(String guess) {
    return isGuessConsistentWithHistory(guess);
  }

  private boolean isGuessConsistentWithHistory(String guess) {
    String assumedCode = guess;
    for (ScoreRecord previous : scoreHistory)
      if (Scorer.scoreGuess(assumedCode, previous.guess).equals(previous.score) == false)
        return false;

    return true;
  }

  public void addScore(String guess, Score score) {
    scoreHistory.add(new ScoreRecord(guess, score));
  }

  private class ScoreRecord {
    private String guess;
    private Score score;

    public ScoreRecord(String guess, Score score) {
      this.guess = guess;
      this.score = score;
    }
  }
}
