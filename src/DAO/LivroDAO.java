package DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import Models.Livro;

public class LivroDAO {
	
	private Connection myConn;
	
	public LivroDAO() throws SQLException {
		
		String user = "root";
		String password = "yami";
		String dburl = "jdbc:mysql://localhost:3306/locadora";
		
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.print("Conectado!");
	}
	
	public List<Livro> getAllLivros() throws SQLException {
		
		List<Livro> list = new ArrayList<>();
		
		Statement myStat = null;
		ResultSet myResult = null;
		
		myStat = myConn.createStatement();
		myResult = myStat.executeQuery("SELECT * FROM Livros");
		
		while (myResult.next()) {
			Livro tempLivro = new Livro(
					myResult.getString("title"),
					myResult.getString("author"),
					myResult.getString("gender"),
					myResult.getInt("year"),
					myResult.getBoolean("isRead")
					);
			
			list.add(tempLivro);
		}
		
		myStat.close();
		return list;
		
	}
	
	public void updateLivroById(int id, Livro livro) throws SQLException {
		Statement myStat = null;
		
		myStat = myConn.createStatement();
		String query = String.format("UPDATE Livros SET "
				+ " title = '%s' , author = '%s' , gender = '%s', year = %s, isRead = %b WHERE id = %s", 
				livro.getTitle(), livro.getAuthor(), livro.getGender(), livro.getYear(), livro.isRead(), id);
		System.out.println(query);
		myStat.executeUpdate(query);

	}

}
