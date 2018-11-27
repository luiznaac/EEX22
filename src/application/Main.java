package application;

import java.rmi.Naming;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import remote.CommunicationInterface;

public class Main extends Application {

  private static CommunicationInterface com;
  
  public static void main(String[] args) {
    com = null;
    while(com == null) {
      try {
        System.out.println("Conectando ao RPI...");
        com = (CommunicationInterface)Naming.lookup("//169.254.218.142:1099/communication");
        System.out.println(com.test());
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
