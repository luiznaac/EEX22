package application;

import gpio.Base;
import model.State;

/**
 * Thread responsável por enviar pulsos para o motor
 * de passo da garra e alterar o estado da angulação 
 * 
 * @author Luiz
 */
public class MotorBase implements Runnable {

  private State state;
  private int toAngle;
  
  public MotorBase(State state, int toAngle) {
    this.state = state;
    this.toAngle = toAngle;
  }
  
  @Override
  public void run() {
    while(state.getAngle() != toAngle) {
      if(state.getAngle() < toAngle) {
        //GlobalLock.lock();
        Base.stepCW();
        state.setAngle(state.getAngle()+2);
        //GlobalLock.unlock();
      }
      else if(state.getAngle() > toAngle) {
        //GlobalLock.lock();
        Base.stepCCW();
        state.setAngle(state.getAngle()-2);
        //GlobalLock.unlock();
      }
      try {
        Thread.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
