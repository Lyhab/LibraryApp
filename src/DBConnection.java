import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DBConnection {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	public DBConnection() {
		try {
			conn = databaseconnection.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("hello");
		DBConnection db = new DBConnection();
		System.out.println("start Insert...");
		db.insertSql("GG", "HH", "GG", "HH", "GG", "HH");
		// System.out.println("start Update...");
		// db.updateSql("e20202", "Bonsoir", 50);
		// db.deleteSql("000");

	}

	public void insertSql(String Title, String Author, String Language, String Genre, String SID,
			String Borrow_date) {
		// System.out.println(title + " " + id + " " + age);
		try {
			String insertSql = "INSERT INTO borrow ( Title , Author, Language, Genre, SID, Borrow_date) VALUE(?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(insertSql);
			// stmt.setInt(1, BID);
			stmt.setString(1, Title);
			stmt.setString(2, Author);
			stmt.setString(3, Language);
			stmt.setString(4, Genre);
			stmt.setString(5, SID);
			stmt.setString(6, Borrow_date);

			int rowInserted = stmt.executeUpdate();
			if (rowInserted > 0)
				System.out.println("Insert successfully...!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Borrow> searchSql(String title) {
		ObservableList<Borrow> list = FXCollections.observableArrayList();

		System.out.println(title + " Searching.....");
		try {
			String sql = "SELECT * FROM borrow";
			if (title != "") {
				sql += " WHERE Title LIKE '%" + title + "%'";
			}
			System.out.println(sql);
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			// String searchSql = "SELECT title, id, age FROM student WHERE title LIKE ?";
			// stmt = conn.prepareStatement(searchSql);
			// stmt.setString(1, "%" + title + "%");
			// rs = stmt.executeQuery();

			while (rs.next()) {

				list.add(new Borrow(rs.getInt("BID"), rs.getString("Title"), rs.getString("Author"),
						rs.getString("Language"), rs.getString("Genre"), rs.getString("SID"),
						rs.getString("Borrow_date")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (list != null) {
			System.out.println("Searching Found");
		}
		return list;
	}

	public void deleteSql(int BID) {
		System.out.println(BID);
		System.out.println("Start deletting....");
		try {
			String deleteSql = "DELETE FROM borrow WHERE BID LIKE ? ";
			stmt = conn.prepareStatement(deleteSql);
			stmt.setInt(1, BID);

			int rowDelete = stmt.executeUpdate();
			if (rowDelete > 0) {
				System.out.println("Delete successfully....");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateSql(String BID, String Title, String Author, String Language, String Genre, String SID,
			String Borrow_date) {
		// System.out.println(oldID + " " + name + " " + age);
		System.out.println("Update starting....");
		try {
			String updateSql = "UPDATE borrow SET Title = ?, Author = ?, Language = ?, Genre = ?, SID = ?, Borrow_date = ? WHERE BID = ?";
			stmt = conn.prepareStatement(updateSql);
			stmt.setString(1, Title);
			stmt.setString(2, Author);
			stmt.setString(3, Language);
			stmt.setString(4, Genre);
			stmt.setString(5, SID);
			stmt.setString(6, Borrow_date);
			stmt.setString(7, BID);

			int rowUpdated = stmt.executeUpdate();
			if (rowUpdated > 0) {
				System.out.println("Update Successfully...!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}