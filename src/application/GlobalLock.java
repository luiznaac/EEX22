package application;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
