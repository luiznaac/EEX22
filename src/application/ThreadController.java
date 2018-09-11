package application;

import java.util.ArrayList;

public class ThreadController implements Runnable {

  private ArrayList<Thread> moveThreads; // Fila de threads a serem executadas
  
  public ThreadController(ArrayList<Thread> moveThreads) {
    this.moveThreads = moveThreads;
  }
  
  @Override
  public void run() {
    while(true) {
      try {
        // Verifica se existe alguma thread na fila
        if(!moveThreads.isEmpty()) {
          Thread t = moveThreads.get(0);
          // Verifica se a thread é nova
          if(t.getState() == Thread.State.NEW) {
            // Thread nova, inicia a execução
            t.start();
          }
          // Verifica se a thread já terminou de ser executada
          else if(t.getState() == Thread.State.TERMINATED) {
            // Thread terminou, remove da fila
            moveThreads.remove(t);
          }
        }
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
}
