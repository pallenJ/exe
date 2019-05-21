package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;
import org.zerock.persistence.DbTest;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SearchTest {
	@Setter(onMethod_ = {@Autowired})
	BoardMapper mapper;
	@Test
	public void searchTest() {
		Criteria cri = new Criteria(0,10);
		cri.setKeyword("a");
		cri.setType("W");
		mapper.getListWithPaging(cri).forEach(board->log.info(board));
	}
}
