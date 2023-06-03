import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class edit_controller {

    @FXML
    private TableView<Book> table;

    @FXML
    private TableColumn<Book, String> BIDColumn;

    @FXML
    private TableColumn<Book, String> TitleColumn;

    @FXML
    private TableColumn<Book, String> AuthorColumn;

    @FXML
    private TableColumn<Book, String> LanguageColumn;

    @FXML
    private TableColumn<Book, String> GenreColumn;

    @FXML
    private TableColumn<Book, String> SIDColumn;

    @FXML
    private TableColumn<Book, String> BorrowColumn;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBorrow;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtLanguage;

    @FXML
    private TextField txtSID;

    @FXML
    private TextField txtTitle;

    @FXML
    void Insert(ActionEvent event) {
        String title, author, language, genre, sid, borrow;
        title = txtTitle.getText();
        author = txtAuthor.getText();
        language = txtLanguage.getText();
        genre = txtGenre.getText();
        sid = txtSID.getText();
        borrow = txtBorrow.getText();
        try{
            pst = con.prepareStatement("insert into library(Title, Author, Language, Genre, SID, Borrow_date) values(?,?,?)");
            pst.setString(1, title);
            pst.setString(2, author);
            pst.setString(3, language);
            pst.setString(4, genre);
            pst.setString(5, sid);
            pst.setString(6, borrow );
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Insertion");

            alert.setHeaderText("Book Insertion");
            alert.setContentText("Inserted !");

            alert.showAndWait();

            table();

            txtTitle.setText("");
            txtAuthor.setText("");
            txtLanguage.setText("");
            txtGenre.setText("");
            txtSID.setText("");
            txtBorrow.setText("");
        } catch (SQLException ex){
            Logger.getLogger(edit_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void table(){
        Connect();
        ObservableList<Book> books = FXCollections.observableArrayList();
        try{
            pst = con.prepareStatement("select bid, title, author, language, genre, sid, brorow_date from library");
            ResultSet rs = pst.executeQuery();

            {
                while (rs.next()) {
                    Book st = new Book();
                    st.setBID(rs.getString("bid"));
                    st.setTitle(rs.getString("title"));
                    st.setAuthor(rs.getString("author"));
                    st.setLanguage(rs.getString("language"));
                    st.setGenre(rs.getString("genre"));
                    st.setSID(rs.getString("sid"));
                    st.setBorrow_date(rs.getString("borrow_date"));
                    books.add(st);
                }
            }
            table.setItems(books);
            BIDColumn.setCellValueFactory(f -> f.getValue().bidProperty());
            TitleColumn.setCellValueFactory(f -> f.getValue().titleProperty());
            AuthorColumn.setCellValueFactory(f -> f.getValue().authorProperty());
            LanguageColumn.setCellValueFactory(f -> f.getValue().languageProperty());
            GenreColumn.setCellValueFactory(f -> f.getValue().genreProperty());
            SIDColumn.setCellValueFactory(f -> f.getValue().sidProperty());
            BorrowColumn.setCellValueFactory(f -> f.getValue().borrow_dateProperty());

        } catch (SQLException ex){
            Logger.getLogger(edit_controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        table.setRowFactory( tv -> {
            TableRow<Book> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())){
                    myIndex = table.getSelectionModel().getSelectedIndex();
                    bid = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getBID()));
                    txtTitle.setText(table.getItems().get(myIndex).getTitle());
                    txtAuthor.setText(table.getItems().get(myIndex).getAuthor());
                    txtLanguage.setText(table.getItems().get(myIndex).getLanguage());
                    txtGenre.setText(table.getItems().get(myIndex).getGenre());
                    txtSID.setText(table.getItems().get(myIndex).getSID());
                    txtBorrow.setText(table.getItems().get(myIndex).getBorrow_date());
                }
            });
            return myRow;
        });
    }
    

    @FXML
    void Remove(ActionEvent event) {

    }

    @FXML
    void Update(ActionEvent event) {

    }

    private Stage stage;
    private Scene scene;
    @FXML
    public void switchtoadmin(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("admin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    Connection con;
    PreparedStatement pst;
    int myIndex;
    int bid;
    public void Connect(){
        try{
            Class.forName("com.mysql.cj.jbdc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://sql12623200:3306/library", "sql12623200", "1C5XGkr8rA");
        } catch (ClassNotFoundException ex){
 
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void initialize(URL url, ResourceBundle rb){
        Connect();
        table();
    }

}