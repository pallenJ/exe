package org.zerock.persistence;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.TimeMapper;
import org.zerock.service.BoardService;

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
	BoardMapper mapper;
	
	  @Setter(onMethod_ = {@Autowired}) 
	  BoardService service;
	 
	
	@Test
	public void test() {
		
		//mapper.getList().forEach(vo->log.info(vo));
		/*
		 * BoardVO board = new BoardVO(); board.setTitle("aaaaaa");
		 * board.setContent("content"); board.setWriter("asasas"); mapper.insert(board);
		 */
		//log.info(mapper.read(13L));
		//log.info(mapper.delete(8L));
		
		// log.info(mapper.count());
		// log.info(service.getList(new Criteria(4,1))); 
		/*
		 * List<BoardVO> list = mapper.getListWithPaging(new Criteria(0, 10));
		 * list.forEach(vo-> log.info(vo));
		 */
		/*
		 * BoardVO vo = new BoardVO(); vo.setBno(13L); vo.setContent("ccc");
		 * vo.setTitle("bbb"); vo.setWriter("aaa"); log.info(mapper.update(vo));
		 */
		 
		/*
		 * try (SqlSession session = sqlSessionFactory.openSession(); Connection con =
		 * session.getConnection()
		 * 
		 * ){ log.info(session); log.info(con); } catch (Exception e) { // TODO: handle
		 * exception fail(e.getMessage()); }
		 */
		/*
		 * try (Connection con = dataSource.getConnection()) { } catch (Exception e) {
		 * // TODO: handle exception fail(e.getMessage()); }
		 */		
		/*
		 * log.info(timeMapper.getClass().getName()); log.info(timeMapper.getTime());
		 */
	}
	
}
