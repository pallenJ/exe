package org.zerock.data;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"
	,"file:src/main/webapp/WEB-INF/spring/security-context.xml"
})
@Log4j
public class Clients {
	@Test
	public void test() throws Exception {
		
		/*
		 * MakeData makeData = new MakeData(); makeData.setStartDate("2019-06-08");
		 * makeData.setEndDate("2019-06-10"); makeData.setDailyCount(100);
		 * 
		 * makeData.prepare();
		 * 
		 * makeData.process();
		 */
		
		/*
		 * String newsDir = "C:\\upload\\news\\"; NewsData newsData = new NewsData();
		 * newsData.subDirList(newsDir); newsData.parseNews(newsDir);
		 * System.out.println(newsData.listSentenceSet);
		 */  
		 		  //newsData.getNews(10); System.out.println(newsData.listSentence);
		 
		/*
		 * String titleDir = "C:\\upload\\news\\"; TitleData titleData = new
		 * TitleData(); titleData.readTextFile(titleDir);
		 * titleData.getTitle().forEach(e->log.info(e));
		 */
		  //newsData.getNews(10); System.out.println(newsData.listSentence);
		  
	
	}
}
