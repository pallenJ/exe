package org.zerock.mapper;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.data.UserTest;
import org.zerock.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml"
})
@Log4j
public class MemberTest {
	
	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	/*
	 * @Test public void countTest() { log.info(service.canUse("afdsf"));
	 * 
	 * }
	 */
	@Test 
	public void idtest() {
		UserTest ut = new UserTest(mapper.allIdList());
		 Map<String,List<String>> temp = ut.extractByHour();
		  
		  for(Iterator<String> iterator= new TreeMap<String,List<String>>(temp).keySet().iterator();iterator.hasNext();) {
			  	String key = iterator.next();
			  	List<String> tlist = temp.get(key);
			  	System.out.println(key+"("+tlist.size()+")"+":"+tlist);
			  }
		  
		//mapper.allIdList().forEach(e->log.info(e));
	}
}
