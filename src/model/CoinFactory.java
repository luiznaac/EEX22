package model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;

import remote.CommunicationInterface;

public class CoinFactory {

  private static CommunicationInterface com;
  private static ArrayList<Position> coins; 

  static {
    coins = new ArrayList<>();
  }
  
  public static void initialize(CommunicationInterface com) {
    CoinFactory.com = com; 
  }
  
  public static void receive(int x, int y) {
    int[] motors = (new Converte(x, y)).calculaConversao();
    System.out.println(x + ", " + y + " " + motors[0] + ", " + motors[1]);
    int[][] destination = {{776, 400},
                           {570, 390},
                           {350, 400},
                           {120, 410},
                           {0, 480}};
    try {
      Random rand = new Random();
      int next = rand.nextInt(5);
      com.sendCoin(new Coin(motors[0], motors[1], destination[next][0], destination[next][1]));
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
  
}
