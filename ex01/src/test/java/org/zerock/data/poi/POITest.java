package org.zerock.data.poi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml" })
@Log4j
public class POITest {

	@Test
	public void test() {
		
		Set<String> aaa = new HashSet<String>();
		aaa.add("2018-09-01");
		aaa.add("2011-12-01");
		aaa.add("2019-02-01");
		log.info(new TreeSet<String>(aaa));


		
	}
	
	public List<BoardVO> generateSample(int cnt){
		Date date = new Date();
		List<BoardVO> rs = new ArrayList<>();
		for (int i = 0; i < cnt; i++) {
			BoardVO vo = new BoardVO("title"+i, "content"+i, "writer"+i, date, date);
			vo.setBno((long)(i+1));
			rs.add(vo);
		}
		return rs;
	}
	
}
