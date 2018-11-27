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
        System.out.println("Processando moeda.");
        System.out.println("Pegando moeda.");
        long init = System.currentTimeMillis();
        if(state.changeState(nextState[0])) 
          System.out.println("Transportando moeda. " + (System.currentTimeMillis() - init));
          state.changeState(nextState[1]);
      }
      try {
        Thread.sleep(1000);
      } catch (Exception E) {
        E.printStackTrace();
      }
    }
  }

}
