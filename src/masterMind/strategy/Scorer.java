package masterMind.strategy;


import masterMind.gamePlay.Score;
import masterMind.gamePlay.GameEngine;

public class Scorer {
  private boolean[] codePositionUsed;
  private boolean[] guessPositionUsed;
  private String code;
  private String guess;

  public Scorer(String code, String guess) {
    this.code = code;
    this.guess = guess;
    codePositionUsed = new boolean[GameEngine.POSITIONS];
    guessPositionUsed = new boolean[GameEngine.POSITIONS];
  }

  public static Score scoreGuess(String code, String guess) {
    return new Scorer(code, guess).scoreIt();
  }

  public Score scoreIt() {
    return new Score(countLettersInPosition(), countLettersOutOfPosition());
  }

  private int countLettersInPosition() {
    int inPosition = 0;
    for (int i = 0; i < code.length(); i++)
      if (isInPosition(i))
        inPosition++;
    return inPosition;
  }

  private boolean isInPosition(int i) {
    if (code.charAt(i) == guess.charAt(i))
      return codePositionUsed[i] = guessPositionUsed[i] = true;
    else
      return false;
  }

  private int countLettersOutOfPosition() {
    int outOfPosition = 0;
    for (int ic = 0; ic < code.length(); ic++)
      if (isOutOfPosition(ic))
        outOfPosition++;
    return outOfPosition;
  }

  private boolean isOutOfPosition(int ic) {
    for (int ig = 0; ig < guess.length(); ig++)
      if (!codePositionUsed[ic] && !guessPositionUsed[ig] && ig != ic && guess.charAt(ig) == code.charAt(ic))
        return codePositionUsed[ic] = guessPositionUsed[ig] = true;

    return false;
  }

}
