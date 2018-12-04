package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CameraComInterface extends Remote {

  public void registerCoin(int x, int y) throws RemoteException;
  
}
