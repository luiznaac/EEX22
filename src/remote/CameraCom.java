package remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.CoinFactory;

public class CameraCom extends UnicastRemoteObject implements CameraComInterface {

  public CameraCom() throws RemoteException {
    super();
  }
  
  @Override
  public void registerCoin(int x, int y) throws RemoteException {
    CoinFactory.receive(x, y);
  }
  
}
