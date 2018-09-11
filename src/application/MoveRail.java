package application;

import java.util.concurrent.locks.Lock;

import view.Rail;

public class MoveRail implements Runnable {

  private int toY;
  private Rail rail;
  
  public MoveRail(Rail rail, int toY) {
    this.toY = toY;
    this.rail = rail;
  }
  
  @Override
  public void run() {
    // Enquanto a posi��o do trilho n�o for a desejada
    while(rail.getPosY() != toY) {
      GlobalLock.lock();
      // Se a posi��o atual for menor que desejada
      if(rail.getPosY() < toY)
        rail.setPosY(rail.getPosY() + 1);
      // Se a posi��o atual for maior que desejada
      else
        rail.setPosY(rail.getPosY() - 1);
      GlobalLock.unlock();
      try {
        Thread.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
