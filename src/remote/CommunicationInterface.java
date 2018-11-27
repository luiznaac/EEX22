package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.Coin;
import model.State;

public interface CommunicationInterface extends Remote {

  public void move(State state) throws RemoteException;
  
  public State getState() throws RemoteException;
  
  public String test() throws RemoteException;
  
  public void changeState(State state) throws RemoteException;
  
  public void sendPwm(int pwm) throws RemoteException;
  
  public void sendCoin(Coin coin) throws RemoteException;
  
  public void toggleMagnet() throws RemoteException;
  
}
