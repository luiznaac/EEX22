package view;

import application.GlobalLock;
import remote.CommunicationInterface;

/**
 * Thread respons�vel por verificar a anfula��o da garra
 * e refletir o estado na interface gr�fica da aplica��o 
 * 
 * @author Luiz
 */
public class VMoveBase implements Runnable {

  private VBase base;
  private CommunicationInterface com;
  
  public VMoveBase(CommunicationInterface com, VBase base) {
    this.com = com;
    this.base = base;
  }
  
  @Override
  public void run() {
    while(true) {
      try {
        base.setAngle(com.getState().getAngle());
        Thread.sleep(4);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
