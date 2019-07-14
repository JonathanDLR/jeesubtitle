package jdlr.subtitle.dao;

import java.util.List;

import jdlr.subtitle.beans.BDDInfo;

public interface InfoSubDAO {
	void addInfo(BDDInfo bddinfo) throws DAOException;
	List<BDDInfo> getAllBDDInfo() throws DAOException;
}
