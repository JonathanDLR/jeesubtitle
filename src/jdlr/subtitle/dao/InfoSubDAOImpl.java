package jdlr.subtitle.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import jdlr.subtitle.beans.BDDInfo;

public class InfoSubDAOImpl implements InfoSubDAO {
	private DAOFactory daoFactory;
	
	InfoSubDAOImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void addInfo(BDDInfo bddinfo) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = (PreparedStatement) 
					connection.clientPrepareStatement("INSERT INTO file_spec(line_number, line_sub_number, line_min,"
							+ " line_text, file_id) SELECT ?, ?, ?, ?, file_sub.id FROM file_sub WHERE"
							+ " file_sub.file_name = ?;");
			preparedStatement.setLong(1, bddinfo.getLine_number());
			preparedStatement.setLong(2, bddinfo.getLine_sub_number());
			preparedStatement.setString(3, bddinfo.getLine_min());
			preparedStatement.setString(4, bddinfo.getLine_text());
			preparedStatement.setString(5, bddinfo.getFileName());
			
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
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
	public List<BDDInfo> getAllBDDInfo() throws DAOException {
		List<BDDInfo> BDDInfos = new ArrayList<BDDInfo>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		
		try {
			connection = daoFactory.getConnection();
			statement = (Statement) connection.createStatement();
			result = statement.executeQuery("SELECT line_number, line_sub_number, line_min, line_text,"
					+ " file_sub.file_name as file_name FROM file_spec JOIN file_sub ON file_spec.file_id = "
					+ "file_sub.id;");
			
			while (result.next()) {
				int line_number = result.getInt("line_number");
				int line_sub_number = result.getInt("line_sub_number");
				String line_min = result.getString("line_min");
				String line_text = result.getString("line_text");
				String fileName = result.getString("file_name");
				
				BDDInfo bddInfo = new BDDInfo();
				bddInfo.setLine_number(line_number);
				bddInfo.setLine_sub_number(line_sub_number);
				bddInfo.setLine_min(line_min);
				bddInfo.setLine_text(line_text);
				bddInfo.setFileName(fileName);
				
				BDDInfos.add(bddInfo);
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
		return BDDInfos;
	}
}
