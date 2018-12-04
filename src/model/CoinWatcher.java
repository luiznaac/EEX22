package model;

public class CoinWatcher implements Runnable {

  private State state;
  
  public CoinWatcher(State state) {
    this.state = state;
  }
  
  @Override
  public void run() {
    while(true) {
      State[] nextState = CoinProcessor.getNext();
      if(nextState != null) {
        CoinProcessor.toggleProcessing();
        if(state.changeState(nextState[0])) 
          if(state.changeState(nextState[1]))
            CoinProcessor.toggleProcessing();
      }
      try {
        Thread.sleep(200);
      } catch (Exception E) {
        E.printStackTrace();
      }
    }
  }

}
