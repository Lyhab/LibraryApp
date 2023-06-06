import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final StringProperty bid;
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty language;
    private final StringProperty genre;
    private final StringProperty sid;
    private final StringProperty borrow_date;

    public Book() {
        bid = new SimpleStringProperty(this, "bid");
        title = new SimpleStringProperty(this, "title");
        author = new SimpleStringProperty(this, "author");
        language = new SimpleStringProperty(this, "language");
        genre = new SimpleStringProperty(this, "genre");
        sid = new SimpleStringProperty(this, "sid");
        borrow_date = new SimpleStringProperty(this, "borrow_date");
    }

    public StringProperty bidProperty() {
        return bid;
    }

    public String getBID() {
        return bid.get();
    }

    public void setBID(String newBID) {
        bid.set(newBID);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String newTitle) {
        title.set(newTitle);
    }

    public StringProperty authorProperty() {
        return author;
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String newAuthor) {
        author.set(newAuthor);
    }

    public StringProperty languageProperty() {
        return language;
    }

    public String getLanguage() {
        return language.get();
    }

    public void setLanguage(String newLanguage) {
        bid.set(newLanguage);
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public String getGenre() {
        return genre.get();
    }

    public void setGenre(String newGenre) {
        genre.set(newGenre);
    }

    public StringProperty sidProperty() {
        return sid;
    }

    public String getSID() {
        return sid.get();
    }

    public void setSID(String newSID) {
        sid.set(newSID);
    }

    // public StringProperty borrow_dateProperty() { return borrow_date;}
    public String getBorrow_date() {
        return borrow_date.get();
    }

    public void setBorrow_date(String newBorrow_date) {
        borrow_date.set(newBorrow_date);
    }
}
