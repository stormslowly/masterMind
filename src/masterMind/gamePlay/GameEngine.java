package masterMind.gamePlay;

public class GameEngine {
  public static final String LETTERS = "ABCDEF";
  public static final int MAX_LETTERS = LETTERS.length();
  public static final int MAX_CODES = MAX_LETTERS * MAX_LETTERS * MAX_LETTERS * MAX_LETTERS;
  public static final int POSITIONS = 4;

  public static GuessChecker guessChecker;
  public static Console console;

  private Guesser guesser;
  private boolean gameOver;
  private int tries;

  public GameEngine() {
    this.guesser = new Guesser(guessChecker);
  }

  public void play() {
    for (tries = 1; !gameOver; tries++)
      tryNextGuess(guesser.getNextGuess());
  }

  private void tryNextGuess(String guess) {
    if (guess == null)
      codeNotFound();
    else
      scoreOneGuess(guess);
  }

  private void scoreOneGuess(String guess) {
    Score score = console.scoreGuess(guess);
    if (isPerfectMatch(score)) {
      win(guess);
    } else {
      guesser.rememberScore(guess, score);
    }
  }

  private void win(String guess) {
    console.announceGameOver();
    console.announceWinningCode(guess);
    console.announceTries(tries);
    gameOver = true;
  }

  private boolean isPerfectMatch(Score score) {
    return score.inPosition() == 4;
  }

  private void codeNotFound() {
    console.announceGameOver();
    console.announceTries(tries - 1);
    console.announceBadScoring();
    gameOver = true;
  }

  public static class Guesser {
    private GuessChecker guessChecker;
    private int guessIndex = 0;

    public Guesser(GuessChecker guessChecker) {
      this.guessChecker = guessChecker;
    }

    public static String makeGuess(int guessIndex) {
      if (guessIndex < 0 || guessIndex >= (MAX_CODES))
        return null;
      else
        return buildGuess(guessIndex);
    }

    private static String buildGuess(int guessIndex) {
      final int n = MAX_LETTERS;
      int d1 = guessIndex % n;
      int d2 = (guessIndex / n) % n;
      int d3 = (guessIndex / (n * n)) % n;
      int d4 = (guessIndex / (n * n * n) % n);

      return String.format("%c%c%c%c", LETTERS.charAt(d4), LETTERS.charAt(d3), LETTERS.charAt(d2), LETTERS.charAt(d1));
    }

    public String getNextGuess() {
      while (true) {
        String guess = makeGuess(guessIndex++);
        if (guess == null)
          return null;
        else if (guessChecker.shouldTry(guess))
          return guess;
      }
    }

    public void rememberScore(String guess, Score score) {
      guessChecker.addScore(guess, score);
    }
  }
}
