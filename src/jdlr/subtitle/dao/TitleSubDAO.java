package jdlr.subtitle.dao;

import java.util.List;

import jdlr.subtitle.beans.BDDTitle;

public interface TitleSubDAO {
	void addTitle(BDDTitle bddtitle) throws DAOException;
	List<BDDTitle> getAllBDDTitle();
}
