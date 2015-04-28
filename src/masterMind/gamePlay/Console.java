package masterMind.gamePlay;


public interface Console {
  Score scoreGuess(String guess);

  void announceGameOver();

  void announceWinningCode(String code);

  void announceTries(int tries);

  void announceBadScoring();
}
