package application;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.CoinFactory;
import model.State;
import remote.CameraCom;
import remote.CommunicationInterface;

public class Main extends Application {

  private static CommunicationInterface com;
  
  public static void main(String[] args) {
    try {
      // Cria o registro servidor
      System.out.println("Criando registro na porta 1099.");
      System.setProperty("java.rmi.server.hostname","localhost");
      LocateRegistry.createRegistry(1099);
      State state = new State(0, 0, 180, false);
      Remote communication = new CameraCom();
      System.out.println("Registro criado com sucesso.");
      // Insere a instância do ChatServer no registro
      System.out.println("Cadastrando objeto servidor no registro.");
      Naming.rebind("//localhost:1099/communication", communication);
      System.out.println("Servidor cadastrado com sucesso.");
      System.out.println("Online.");
      Thread.sleep(1000);
    } catch (Exception e) {
      e.printStackTrace();
    }
    com = null;
    while(com == null) {
      try {
        System.out.println("Conectando ao RPI...");
        com = (CommunicationInterface)Naming.lookup("//169.254.218.142:1099/communication");
        System.out.println(com.test());
        CoinFactory.initialize(com);
        launch(args);
      } catch (Exception E) {
        System.out.println("Conexão falhou, tentando novamente...");
      }
    }
  }
  
  @Override
  public void start(Stage primaryStage) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader();
      BorderPane root = (BorderPane)fxmlLoader.load(getClass().getResource("/view/View.fxml").openStream());
      Controller controller = fxmlLoader.getController();
      Scene scene = new Scene(root);
      controller.link(com);
      primaryStage.setResizable(false);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  
}
