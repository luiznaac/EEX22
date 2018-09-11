package application;

import view.Arm;
import view.Rail;

public class MoveArm implements Runnable {

  private int toAngle;
  private Arm arm;
  
  public MoveArm(Arm arm, int toAngle) {
    this.toAngle = toAngle;
    this.arm = arm;
  }
  
  @Override
  public void run() {
    // Enquanto o �ngulo atual do bra�o n�o for a desejado
    while(arm.getAngle() != toAngle) {
      // Se o �ngulo atual for menor que o desejado
      if(arm.getAngle() < toAngle)
        arm.setAngle(arm.getAngle() + 1);
      // Se o �ngulo atual for maior que o desejado
      else
        arm.setAngle(arm.getAngle() - 1);
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
