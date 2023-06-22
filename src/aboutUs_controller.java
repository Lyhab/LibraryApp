import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class aboutUs_controller {
  private Stage stage;
  private Scene scene;

  @FXML
  void aboutbtn(ActionEvent event) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("aboutUs.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  void adminbtn(ActionEvent event) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  void homebtn(ActionEvent event) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

}
