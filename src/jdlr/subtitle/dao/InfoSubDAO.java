package jdlr.subtitle.dao;

import java.util.List;

import jdlr.subtitle.beans.BDDInfo;

public interface InfoSubDAO {
	/**
	 * Add info in the BDD
	 * @param bddinfo
	 * @throws DAOException
	 */
	void addInfo(BDDInfo bddinfo) throws DAOException;
	
	/**
	 * Get info from the BDD
	 * @return Array of infos
	 * @throws DAOException
	 */
	List<BDDInfo> getAllBDDInfo() throws DAOException;
}
