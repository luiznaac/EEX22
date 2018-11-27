package view;

import application.GlobalLock;
import model.State;
import remote.CommunicationInterface;

/**
 * Thread respons�vel por verificar a posi��o do trilho
 * e refletir o estado na interface gr�fica da aplica��o 
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
