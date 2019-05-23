package org.zerock.persistence;

import java.util.List;
import java.util.stream.IntStream;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MapperTest {

	@Setter(onMethod_ = {@Autowired})
	private ReplyMapper mapper;
	private Long[] bnoArr = {291L,292L,293L,294L,295L};
	//@Test
	public void testCreater() {
		IntStream.rangeClosed(11, 50).forEach(i->{
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i%5]);
			vo.setReply("댓글 테스트 "+i);
			vo.setReplyer("replyer "+i);
			mapper.insert(vo);
		});
		
	}
//	@Test
	public void testMapper() {
		log.info(mapper);
	}
//	@Test
	public void testList() {
		Criteria cri = new Criteria(0,4);
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 293L);
		log.info(cri.getAmount());
		replies.forEach(r->log.info(r));
	}
//	@Test
	public void testCnt() {
		log.info(mapper.getCountByBno(bnoArr[2]));
	}


}
