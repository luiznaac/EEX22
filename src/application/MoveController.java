package application;

import java.util.ArrayList;

public class MoveController implements Runnable {

  private ArrayList<Thread> moveThreads; // Fila de threads a serem executadas
  
  public MoveController(ArrayList<Thread> moveThreads) {
    this.moveThreads = moveThreads;
  }
  
  @Override
  public void run() {
    while(true) {
      try {
        Thread.sleep(5);
        // Verifica se existe alguma thread na fila
        if(moveThreads.size() >= 2) {
          Thread t1 = moveThreads.get(0);
          Thread t2 = moveThreads.get(1);
          // Verifica se a thread é nova
          if(t1.getState() == Thread.State.NEW) {
            // Thread nova, inicia a execução
            t1.start();
          }
          if(t2.getState() == Thread.State.NEW) {
            // Thread nova, inicia a execução
            t2.start();
          }
          // Verifica se a thread já terminou de ser executada
            t1.join();
            t2.join();
          // Thread terminou, remove da fila
          moveThreads.remove(t1);
          moveThreads.remove(t2);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
}
