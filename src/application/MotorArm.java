package application;

import gpio.Arm;
import model.State;

/**
 * Thread responsável por enviar pwm para o servo
 * e mudar a altura da garra
 * 
 * @author Luiz
 */
public class MotorArm implements Runnable {

  private State state;
  private int toAngle;
  
  public MotorArm(State state, int toAngle) {
    this.state = state;
    this.toAngle = toAngle;
  }
  
  @Override
  public void run() {
    
    while(state.getArm() != toAngle) {
      if(state.getArm() < toAngle) {
        //GlobalLock.lock();
        Arm.pwmUp();
        state.setArm(state.getArm()+5);
        //GlobalLock.unlock();
      }
      else if(state.getArm() > toAngle) {
        //GlobalLock.lock();
        Arm.pwmDown();
        state.setArm(state.getArm()-5);
        //GlobalLock.unlock();
      }
      try {
        Thread.sleep(120);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    
    /*Arm.setPwm(toAngle);
    state.setArm(toAngle);*/
  }

}
