package application;

import java.util.concurrent.locks.Lock;

import view.Arm;

public class MoveArm implements Runnable {

  private int toAngle;
  private Arm arm;
  private Lock lock;
  
  public MoveArm(Arm arm, int toAngle, Lock lock) {
    this.toAngle = toAngle;
    this.arm = arm;
    this.lock = lock;
  }
  
  @Override
  public void run() {
    // Enquanto o �ngulo atual do bra�o n�o for a desejado
    while(arm.getAngle() != toAngle) {
      lock.lock();
      // Se o �ngulo atual for menor que o desejado
      if(arm.getAngle() < toAngle)
        arm.setAngle(arm.getAngle() + 1);
      // Se o �ngulo atual for maior que o desejado
      else
        arm.setAngle(arm.getAngle() - 1);
      lock.unlock();
      try {
        Thread.sleep(20);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
