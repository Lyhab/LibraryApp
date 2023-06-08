public class Borrow {
  private int BID;
  private String Author;
  private String title;
  private String language;
  private String Genre;
  private String SID;
  private String Borrow_date;

  public Borrow(int bID, String title, String author, String language, String genre, String sID, String borrow_date) {
    BID = bID;
    Author = author;
    this.title = title;
    this.language = language;
    Genre = genre;
    SID = sID;
    Borrow_date = borrow_date;
  }

  public int getBID() {
    return BID;
  }

  public void setBID(int bID) {
    BID = bID;
  }

  public String getAuthor() {
    return Author;
  }

  public void setAuthor(String author) {
    Author = author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getGenre() {
    return Genre;
  }

  public void setGenre(String genre) {
    Genre = genre;
  }

  public String getSID() {
    return SID;
  }

  public void setSID(String sID) {
    SID = sID;
  }

  public String getBorrow_date() {
    return Borrow_date;
  }

  public void setBorrow_date(String borrow_date) {
    Borrow_date = borrow_date;
  }
}
