package org.zerock.mapper;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MapperTest {
	
	 @Setter(onMethod_ = {@Autowired})
	 private BoardMapper bmapper;
	 @Setter(onMethod_ = {@Autowired})
	 private BoardService bservice;
	
	 @Test
	 public void test() {
		 
		 
		 //log.info(bmapper.getRcount(390L));
		 //log.info(bservice.rcounts());
	 }
	 
}
