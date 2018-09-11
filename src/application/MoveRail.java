package application;

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
    // Enquanto a posição do trilho não for a desejada
    while(rail.getPosY() != toY) {
      // Se a posição atual for menor que desejada
      if(rail.getPosY() < toY)
        rail.setPosY(rail.getPosY() + 1);
      // Se a posição atual for maior que desejada
      else
        rail.setPosY(rail.getPosY() - 1);
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
