package org.zerock.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;
import org.zerock.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyTest {
	
	@Setter(onMethod_ = {@Autowired}) 
	private ReplyMapper mapper;
	@Test
	public void test() {
		//log.info(mapper);
		/*
		 * ReplyVO reply = new ReplyVO(); reply.setBno(7L); reply.setReply("test");
		 * reply.setReplyer("test"); mapper.insert(reply);
		 */
		//log.info(mapper.read(3L));
		//mapper.delete(1L);
		/*
		 * ReplyVO vo = new ReplyVO(); vo.setRno(2L); vo.setReply("asdf");
		 * mapper.update(vo);
		 */
		
		mapper.getListWithPaging(new Criteria(), 74L).forEach(r->log.info(r));
		
		
	}
	
}
