package remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.Coin;
import model.CoinProcessor;
import model.State;

public class Communication extends UnicastRemoteObject implements CommunicationInterface {
  
  private State state;
  
  public Communication(State state) throws RemoteException {
    this.state = state;
  }

  public void move(State state) throws RemoteException {
    state.changeState(state);
  }
  
  public State getState() throws RemoteException {
    return state;
  }
  
  public String test() throws RemoteException {
    System.out.println("Conectado.");
    return "Conectado.";
  }
  
  public void changeState(State state) throws RemoteException {
    this.state.changeState(state);
  }

  public void sendCoin(Coin coin) throws RemoteException {
    CoinProcessor.newCoin(coin);
  }

  public void sendPwm(int pwm) throws RemoteException {
    state.changeArm(pwm);
  }
  
  public void toggleMagnet() throws RemoteException {
    state.changeMagnet();
  }
  
}
