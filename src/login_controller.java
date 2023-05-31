import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.io.IOException;

public class login_controller {
    @FXML
    TextField userid;
    @FXML
    TextField passid;
    private Stage stage;
    private Scene scene;

    public void switchtohome(ActionEvent event) throws IOException {
        String id = userid.getText();
        String pass = passid.getText();
        if (id.equals("admin") && pass.equals("123")) {

            Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 700, 700));
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Incorrect username or password");
        }
    }
}