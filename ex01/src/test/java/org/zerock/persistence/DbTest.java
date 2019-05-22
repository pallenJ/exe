package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.mapper.TimeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DbTest {
	
	
	  @Setter(onMethod_ = {@Autowired})
	  private DataSource dataSource;
	  
	  @Setter(onMethod_ = {@Autowired})
	  private SqlSessionFactory sqlSessionFactory;
	  @Setter(onMethod_ = {@Autowired})
	  private TimeMapper mapper;
	  @Test
	  public void test() {
		  
		/*
		 * try (SqlSession session = sqlSessionFactory.openSession(); Connection con =
		 * session.getConnection()
		 * 
		 * ){ log.info(session); log.info(con); } catch (Exception e) {
		 * fail(e.getMessage()); }
		 */
			
		/*
		 * try (Connection con = dataSource.getConnection()) { } catch (Exception e) {
		 * // TODO: handle exception fail(e.getMessage()); }
		 */
		log.info(mapper.getTime());	 		
	  }
	  
}
