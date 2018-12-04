package model;

import java.util.ArrayList;

public class CoinProcessor {

  private static ArrayList<Coin> coins;
  private static boolean processing;
  
  static {
    coins = new ArrayList<>();
    processing = false;
  }
  
  public static State[] getNext() {
    if(!coins.isEmpty()) {
      Coin coin = coins.remove(0);
      State[] state = {
          new State(coin.getOriginTrail(), coin.getOriginBase()),
          new State(coin.getDestTrail(), coin.getDestBase())
      };
      return state;
    }
    else
      return null;
  }
  
  public static void newCoin(Coin coin) {
    if(!processing) {
      coins.add(coin);
      System.out.println("Nova moeda.");
    }
  }
  
  public static void toggleProcessing() {
    processing = !processing;
  }
  
}
