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

}
