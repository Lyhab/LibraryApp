import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class about_controller {
  private Stage stage;
  private Scene scene;
  private Parent root;

  @FXML
  void switchtoabout(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("about.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  void switchtoadmin(ActionEvent event) {

  }

  @FXML
  void switchtohome(ActionEvent event) {

  }

}
