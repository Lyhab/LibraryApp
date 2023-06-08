import java.security.Provider.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class edit_controller {
    DBConnection db = new DBConnection();
    service sv = new service();
    ObservableList<Borrow> list = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Borrow, String> AuthorColumn;

    @FXML
    private TableColumn<Borrow, Integer> BIDColumn;

    @FXML
    private TableColumn<Borrow, String> BorrowColumn;

    @FXML
    private TableColumn<Borrow, String> GenreColumn;

    @FXML
    private TableColumn<Borrow, String> LanguageColumn;

    @FXML
    private TableColumn<Borrow, String> SIDColumn;

    @FXML
    private TableColumn<Borrow, String> TitleColumn;

    @FXML
    private TextField bidField;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Borrow> table;

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
    private Button deleteBtn;

    @FXML
    private TextField searchField;

    @FXML
    void Insert(ActionEvent event) {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String language = txtLanguage.getText();
        String genre = txtGenre.getText();
        String sID = txtSID.getText();
        String borrowDate = txtBorrow.getText();

        if (title == "" || sID == "" || borrowDate == "") {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("invalid insert...");
            alert.setContentText("");
            // Show the alert dialog
            alert.showAndWait();

        } else if (title != "" && sID != "" && borrowDate != "") {
            db.insertSql(title, author, language, genre, sID, borrowDate);
            Remove(event);
            Search("");

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Insert Success");
            alert.setContentText("Insert Success....");
            // Show the alert dialog
            alert.showAndWait();
        }

    }

    @FXML
    void Remove(ActionEvent event) {
        bidField.setText("");
        txtAuthor.setText("");
        txtTitle.setText("");
        txtGenre.setText("");
        txtBorrow.setText("");
        txtLanguage.setText("");
        txtSID.setText("");

    }

    @FXML
    void Update(ActionEvent event) {
        String bid = bidField.getText();
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String language = txtLanguage.getText();
        String genre = txtGenre.getText();
        String sID = txtSID.getText();
        String borrowDate = txtBorrow.getText();

        if (title == "" || sID == "" || borrowDate == "") {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("invalid insert...");
            alert.setContentText("");
            // Show the alert dialog
            alert.showAndWait();

        } else if (title != "" && sID != "" && borrowDate != "") {
            db.updateSql(bid, title, author, language, genre, sID, borrowDate);
            Remove(event);
            Search("");

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Update Success");
            alert.setContentText("Update Success....");
            // Show the alert dialog
            alert.showAndWait();
        }
    }

    @FXML
    void handleDelete(ActionEvent event) {

        int BID = Integer.parseInt(bidField.getText());
        db.deleteSql(BID);
        Search("");

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Success");
        alert.setContentText("Delete Success....");
        // Show the alert dialog
        alert.showAndWait();
    }

    @FXML
    void handleSearch(ActionEvent event) {
        String title = searchField.getText();
        Search(title);
    }

    @FXML
    void switchtoadmin(ActionEvent event) {

    }

    public ObservableList<Borrow> getBooksList() throws SQLException {
        ObservableList<Borrow> borrowList = FXCollections.observableArrayList();
        try {
            Connection conn = databaseconnection.getConnection();
            String sql = "SELECT * FROM borrow";

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Borrow borrows;
            while (resultSet.next()) {
                borrows = new Borrow(resultSet.getInt("BID"), resultSet.getString("Title"),
                        resultSet.getString("Author"), resultSet.getString("Language"), resultSet.getString("Genre"),
                        resultSet.getString("SID"), resultSet.getString("Borrow_date"));
                borrowList.add(borrows);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return borrowList;

    }

    public void showBorrows() throws SQLException {
        ObservableList<Borrow> list = getBooksList();
        BIDColumn.setCellValueFactory(new PropertyValueFactory<Borrow, Integer>("BID"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<Borrow, String>("Title"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<Borrow, String>("Author"));
        LanguageColumn.setCellValueFactory(new PropertyValueFactory<Borrow, String>("Language"));
        GenreColumn.setCellValueFactory(new PropertyValueFactory<Borrow, String>("Genre"));
        SIDColumn.setCellValueFactory(new PropertyValueFactory<Borrow, String>("SID"));
        BorrowColumn.setCellValueFactory(new PropertyValueFactory<Borrow, String>("Borrow_date"));
        table.setItems(list);
    }

    @FXML
    public void initialize() {
        try {
            showBorrows();

        } catch (Exception e) {
            System.out.println(e);
        }
        selectedRow();
    }

    public void Search(String title) {
        list = db.searchSql(title);
        table.setItems(list);
    }

    public void selectedRow() {
        // Set a listener on the table view to get the values of each column when the
        // user clicks on a row
        table.setOnMouseClicked(event -> {

            // Check if the user clicked on a row
            if (event.getClickCount() == 2) {

                // Get the table view's selection model
                TableView.TableViewSelectionModel<Borrow> selectionModel = table.getSelectionModel();

                // Get the selected row
                Borrow selected = selectionModel.getSelectedItem();

                // Get the values of each column
                int BID = selected.getBID();
                String title = selected.getTitle();
                String author = selected.getAuthor();
                String language = selected.getLanguage();
                String genre = selected.getGenre();
                String SID = selected.getSID();
                String borrowDate = selected.getBorrow_date();

                // Set the value to Fields
                bidField.setText(Integer.toString(BID));
                txtTitle.setText(title);
                txtAuthor.setText(author);
                txtLanguage.setText(language);
                txtGenre.setText(genre);
                txtSID.setText(SID);
                txtBorrow.setText(borrowDate);

                // Print the values of each column
                System.out.println("Name: " + title);

            }
        });
    }

    @FXML
    void autoGenerrate(MouseEvent event) throws SQLException {
        int id = 0;
        Connection conn = databaseconnection.getConnection();
        String sql = "SELECT * FROM borrow";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            id = rs.getInt("BID") + 1;
        }
        String convert = Integer.toString(id);
        bidField.setText(convert);
    }

}
