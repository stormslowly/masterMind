package masterMind.gamePlay;

public class Score {
  private int inPosition;
  private int inCode;

  public Score(int inPosition, int inCode) {
    this.inPosition = inPosition;
    this.inCode = inCode;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Score) {
      Score score = (Score) o;
      return score.inPosition == inPosition && score.inCode == inCode;
    }
    return false;
  }

  @Override
  public String toString() {
    return String.format("(%d, %d)", inPosition, inCode);
  }

  public int inPosition() {
    return inPosition;
  }

  public int inCode() {
    return inCode;
  }
}
