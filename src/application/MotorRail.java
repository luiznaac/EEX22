package application;

import gpio.Rail;
import model.State;

/**
 * Thread responsável por enviar pulsos para o motor
 * de passo do trilho e alterar o estado da posição 
 * 
 * @author Luiz
 */
public class MotorRail implements Runnable {

  private State state;
  private int toPosY;
  
  public MotorRail(State state, int toPosY) {
    this.state = state;
    this.toPosY = toPosY;
  }
  
  @Override
  public void run() {
    while(state.getPosY() != toPosY) {
      try {
        if(state.getPosY() < toPosY) {
          //GlobalLock.lock();
          Rail.stepCW();
          state.setPosY(state.getPosY()+1);
          //GlobalLock.unlock();
        }
        else if(state.getPosY() > toPosY) {
          //GlobalLock.lock();
          Rail.stepCCW();
          state.setPosY(state.getPosY()-1);
          //GlobalLock.unlock();
        }
        Thread.sleep(0, 125);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
