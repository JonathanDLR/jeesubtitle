package jdlr.subtitle.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import jdlr.subtitle.beans.BDDTitle;

public class TitleSubDAOImpl implements TitleSubDAO {
	private DAOFactory daoFactory;
	
	TitleSubDAOImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void addTitle(BDDTitle bddtitle) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) 
					connection.clientPrepareStatement("INSERT INTO file_sub(file_name) VALUES(?);");
			preparedStatement.setString(1, bddtitle.getFileName());
			
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DAOException("Impossible de communiquer avec le base de données.");
		}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new DAOException("Impossible de communiquer avec le base de données.");
			}
		}
	}
	
	@Override
	public List<BDDTitle> getAllBDDTitle() throws DAOException {
		List<BDDTitle> BDDTitles = new ArrayList<BDDTitle>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		
		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery("SELECT file_name FROM file_sub;");
			
			while (result.next()) {
				String fileName = result.getString("file_name");
				
				BDDTitle bddTitle = new BDDTitle();
				bddTitle.setFileName(fileName);
				
				BDDTitles.add(bddTitle);
			}
		} catch (SQLException e) {
			throw new DAOException("Impossible de communiquer avec la base de données.");
		} 
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				throw new DAOException("Impossible de communiquer avec la base de données.");
			}
		}
		return BDDTitles;
	}
}
