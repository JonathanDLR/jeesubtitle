package jdlr.subtitle.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DAOFactory {
	private String url;
	private String username;
	private String password;
	
	public DAOFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	// Factory creation
	public static DAOFactory getInstance() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
		}
		
		DAOFactory instance = new DAOFactory("jdbc:mysql://localhost:3306/j2e_subtitle?useUnicode=yes&characterEncoding=UTF-8",
				"root", "marie89");
		
		return instance;
	}
	
	// Connection creation
	public Connection getConnection() throws SQLException {
		Connection connection = (Connection) DriverManager.getConnection(url, username, password);
		connection.setAutoCommit(false);
		
		return connection;
	}
	
	// Using DAO
	public InfoSubDAO getInfoSubDAO() {
		return new InfoSubDAOImpl(this);
	}
	
	public TitleSubDAO getTitleSubDAO() {
		return new TitleSubDAOImpl(this);
	}
}
