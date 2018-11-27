package model;

import java.util.ArrayList;

public class CoinProcessor {

  private static ArrayList<Coin> coins;
  
  static {
    coins = new ArrayList<>();
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
    coins.add(coin);
    System.out.println("Nova moeda.");
  }
  
}
