package application;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Classe utilizada globalmente pelas threads para
 * facilitar a sincronização na escrita/leitura dos dados
 * 
 * @author Luiz
 */
public class GlobalLock {

  private static Lock lock;
  
  static {
    lock = new ReentrantLock();
    System.out.println("teste");
  }
  
  public static void lock() {
    lock.lock();
  }
  
  public static void unlock() {
    lock.unlock();
  }
  
}
