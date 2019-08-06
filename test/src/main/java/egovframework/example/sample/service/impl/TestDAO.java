package egovframework.example.sample.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("testDAO")
public class TestDAO extends EgovAbstractDAO {

	public Object testDb() {
		return select("testDAO.testDB");
	}
	
}
