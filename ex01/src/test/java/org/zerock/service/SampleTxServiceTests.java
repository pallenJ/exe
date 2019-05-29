package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.test.SampleServiceTests;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTxServiceTests {

	@Setter(onMethod_ = {@Autowired})
	private SampleTxService service;
	
	@Test
	public void testLong() {
		
		String str = "Starry\r\n"+"Starry night\r\n"
		+"jojo's bizare Adventure\r\n"+"Golden Wind2";
		
		log.info(str.getBytes().length);
		service.addData(str);
	}
}
