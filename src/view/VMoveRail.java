package view;

import application.GlobalLock;
import model.State;
import remote.CommunicationInterface;

/**
 * Thread responsável por verificar a posição do trilho
 * e refletir o estado na interface gráfica da aplicação 
 * 
 * @author Luiz
 */
public class VMoveRail implements Runnable {

  private VRail rail;
  private CommunicationInterface com;
  
  public VMoveRail(CommunicationInterface com, VRail rail) {
    this.com = com;
    this.rail = rail;
  }
  
  @Override
  public void run() {
    while(true) {
      try {
        rail.setPosY(com.getState().getPosY());
        Thread.sleep(4);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
