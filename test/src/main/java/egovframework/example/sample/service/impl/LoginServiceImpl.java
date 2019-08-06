package egovframework.example.sample.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.example.sample.service.LoginService;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.sqlmap.example.mappers.SimplyMapper;
import egovframework.sqlmap.example.mappers.TestMapper;
import lombok.Setter;

@Service("loginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService{

	@Resource(name = "testMapper")
	TestMapper testMapper;
	@Resource(name = "testDAO")
	TestDAO testDAO;
	
	@Resource(name = "sampleDAO")
	SampleDAO sampleDAO;
	
	@Resource(name = "simplyMapper")
	SimplyMapper simplyMapper;
	
	
	@Override
	public boolean login(String id, String pw) {
		// TODO Auto-generated method stub
		
		return id.contains("admin");
	}

	@Override
	public Object  test() {
		// TODO Auto-generated method stub
		
		return simplyMapper.selectOne("users", "USERNAME", "ddd");
	}

	
	
}
