package jdlr.subtitle.dao;

import java.util.List;

import jdlr.subtitle.beans.BDDTitle;

public interface TitleSubDAO {
	/**
	 * Add title in the BDD
	 * @param bddtitle
	 * @throws DAOException
	 */
	void addTitle(BDDTitle bddtitle) throws DAOException;
	
	/**
	 * Get title of the file
	 * @return The title
	 * @throws DAOException
	 */
	List<BDDTitle> getAllBDDTitle() throws DAOException;
}
