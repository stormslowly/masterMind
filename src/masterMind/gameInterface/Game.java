package masterMind.gameInterface;

import masterMind.gamePlay.Console;
import masterMind.gamePlay.GameEngine;
import masterMind.gamePlay.Score;
import masterMind.strategy.RememberingGuessChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
  private static BufferedReader br;

  public static void main(String[] args) {
    br = new BufferedReader(new InputStreamReader(System.in));
    GameEngine.guessChecker = new RememberingGuessChecker();
    GameEngine.console = new GameConsole();
    new GameEngine().play();
  }

  private static class GameConsole implements Console {
    public Score scoreGuess(String guess) {
      System.out.println("Enter score for " + guess);
      String guessString = readLine();
      return new Score(count(guessString, '+'), count(guessString, '-'));
    }

    private String readLine() {
      try {
        return br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }

    private int count(String s, char c) {
      int count = 0;
      for (int i = 0; i < s.length(); i++)
        if (s.charAt(i) == c)
          count++;
      return count;
    }

    public void announceGameOver() {
      System.out.println("Game Over.");
    }

    public void announceWinningCode(String code) {
      System.out.println("Winning code = " + code);
    }

    public void announceTries(int tries) {
      System.out.println("tries = " + tries);
    }

    public void announceBadScoring() {
      System.out.println("Sorry, but you're scoring was less than perfectly accurate.");
    }
  }
}
