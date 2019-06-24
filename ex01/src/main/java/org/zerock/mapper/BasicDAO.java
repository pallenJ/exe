package org.zerock.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import lombok.Data;
import lombok.Setter;

public class BasicDAO {
	@Setter(onMethod_ = @Autowired)
	private SqlSession sqlSession;
	
	@Setter
	private String statement;
	
	public BasicDAO() {
	}
	
	public BasicDAO(String statement) {
		this.statement=statement;
	}
	
	public <E> List<E> list(String queryId){
		return sqlSession.selectList(statement+"."+queryId);
	}
	
	public <E> List<E> list(String queryId,Object parameter){
		return sqlSession.selectList(statement+"."+queryId, parameter);
	}
	
	
}
