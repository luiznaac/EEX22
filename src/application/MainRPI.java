package application;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;

import gpio.Arm;
import gpio.Base;
import gpio.Magnet;
import gpio.Rail;
import model.CoinWatcher;
import model.State;
import remote.Communication;

public class MainRPI {

  public static void main(String[] args) {
    try {
      // Cria o registro servidor
      System.out.println("Criando registro na porta 1099.");
      System.setProperty("java.rmi.server.hostname","169.254.218.142");
      LocateRegistry.createRegistry(1099);
      State state = new State(0, 258, 180, false);
      Remote communication = new Communication(state);
      System.out.println("Registro criado com sucesso.");
      // Insere a instância do ChatServer no registro
      System.out.println("Cadastrando objeto servidor no registro.");
      Naming.rebind("//localhost:1099/communication", communication);
      System.out.println("Servidor cadastrado com sucesso.");
      System.out.println("Online.");
      Rail.initialize();
      Base.initialize();
      Arm.initialize();
      Magnet.initialize();
      Thread.sleep(1000);
      state.defaultState();
      Runnable coinWatcher = new CoinWatcher(state);
      Thread tCoin = new Thread(coinWatcher);
      tCoin.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
