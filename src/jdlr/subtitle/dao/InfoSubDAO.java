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
	 * Update info in the bdd
	 * @param bddinfo
	 * @throws DAOException
	 */
	void updInfo(BDDInfo bddinfo) throws DAOException;
	
	/**
	 * Get info from the BDD
	 * @return Array of infos
	 * @throws DAOException
	 */
	List<BDDInfo> getAllBDDInfo(String fileName) throws DAOException;
}
