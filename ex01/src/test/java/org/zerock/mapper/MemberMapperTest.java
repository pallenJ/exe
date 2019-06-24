package org.zerock.mapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.MemberVO;
import org.zerock.domain.TableDTO;
import org.zerock.security.MemberTest;
import org.zerock.service.sta.StaService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml" })
@Log4j
public class MemberMapperTest {

	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;

	@Setter(onMethod_ = @Autowired)
	private UserDataMapper umapper;
	
	@Setter(onMethod_ = @Autowired)
	private StaService staService;
	
	@Setter(onMethod_ = @Autowired)
	SqlSession sqlSession;

	BasicDAO bdao;

	@Test
	public void test04() {
		
		
		  TableDTO tdto = new TableDTO("tbl_board_10",TableDTO.YMD,TableDTO.H);
		  Map<String,Map<String,Object>>maps = new TreeMap<String,Map<String,Object>>(staService.getStatisticsMap(tdto));
		  
		  
		  
		  for (String key : maps.keySet()) {
		      System.out.print(key+"||");
		      
		      Map<String,Object> valueMap = maps.get(key);
		      Set<String> keySet = new TreeSet<String>(valueMap.keySet());
		      
		      for (String string : keySet) {
				System.out.print(valueMap.get(string)+"|");
			}
		      System.out.println();
		  }
		 
		/*
		 * Set<String> set = new TreeSet<String>(); int i = 1; while(i<20) {
		 * set.add(String.format("%02d", i)); i++; } for (String string : set) {
		 * log.info(string); }
		 */
	}

	// @Test
	public void testRead() {
		/*
		 * MemberVO vo = mapper.read("admin90"); log.info(vo);
		 * vo.getAuthList().forEach(auth -> log.info(auth));
		 */

		// mapper.allIdList2().forEach(e->log.info(e));

		HashMap<String, HashSet<String>> data = new HashMap<>();
		HashSet<String> set = new HashSet<String>();
		for (int i = 0; i < 1000; i++) {
			set.add("user" + String.format("%03d", i));
		}

		data.put("data", set);

		long start = System.currentTimeMillis();
		umapper.insertAll(data);
		long end = System.currentTimeMillis();

		System.out.println("실행 시간 : " + (end - start) / 1000.0);

		// 0.386초

		// System.out.println(umapper.count());
		/*
		 * bdao = new BasicDAO("org.zerock.mapper.BoardMapper");
		 * System.out.println(bdao.list("getList"));
		 */
		// System.out.println(sqlSession.selectList("org.zerock.mapper.BoardMapper.getList"));
		/*
		 * String statement = "org.zerock.mapper.UserDataMapper.insert"; HashSet<String>
		 * set = new HashSet<String>(); for (int i = 0; i < 1000; i++) { set.add("user"
		 * + String.format("%03d", i)); } long start = System.currentTimeMillis(); for
		 * (String string : set) { sqlSession.insert(statement,string);
		 * 
		 * } long end = System.currentTimeMillis();
		 * 
		 * System.out.println("실행 시간 : " + (end - start) / 1000.0);
		 */

	}

	//@Test
	public void test2() {
		/*
		 * String filepath = "C:/upload/data"; String title = "test"; createIDCSV(10000,
		 * filepath, title);
		 */
		// createBoardData();

		int dcnt = 100000;
		int one = 2000;
		int dataCnt = 0;
		boolean flag = false;
		List<BoardVO> parameter = new ArrayList<BoardVO>();
		HashMap<String, List<BoardVO>> data = new HashMap<>();

		while (true) {

			try {

				MakeData mkdt = new MakeData(one);
				mkdt.prepare(umapper.listAll());
				mkdt.process();
				// System.out.println(data.get("data").size());
				// TODO: handle exception

				// System.out.println("forcnt:"+forcnt);

				parameter.addAll(mkdt.getBlist());
				/*
				 * for (BoardVO vo : mkdt.getBlist()) { System.out.println(vo.getRegdate()); }
				 */

			} catch (Exception e) {
				continue;
			}
			dataCnt = parameter.size();
			System.out.println("cnt:" + dataCnt);
			if (flag)
				break;
			if (dataCnt >= dcnt)
				flag = true;
		}

		List<BoardVO> tmp = new ArrayList<>();
		dataCnt = 0;
		long start = System.currentTimeMillis();
		int aaaaaa = 0;
		for (BoardVO boardVO : parameter) {

			tmp.add(boardVO);
			dataCnt++;

			if (dataCnt == one) {
				data.put("data", tmp);
				umapper.insertAllBoard(data);
				tmp = new ArrayList<>();
				dataCnt = 0;
				System.out.println((++aaaaaa)+"바퀴/"+parameter.size());
			}

		}

		/*
		 * for (BoardVO boardVO : parameter) { System.out.println(boardVO.getRegdate());
		 * if(i++>30) break; }
		 */
		long end = System.currentTimeMillis();

		 System.out.println("실행 시간 : " + (end - start) / 1000.0);

		// System.out.println(umapper.randId(10001));

		/*
		 * List<String> timelist = mkdt.makeWriteMinSec(1000); int cnt = 0; for (String
		 * time : timelist) { System.out.print(time+"	"); if((++cnt)%10==0)
		 * System.out.println(); }
		 */

	}

	//@Test
	public void test3() {
		
		System.out.println(umapper.getListPaging(new TableDTO("tbl_board"), new Criteria(0, 100,"T","더")));
		
		//List<?> ddd = umapper.getBrdStatistics(new TableDTO("tbl_board_10"));
		//ddd.forEach(e -> log.info(e));
		// System.out.println(ddd.size());

	}

	// String filepath = "C:/upload/data";

	public void createIDCSV(int cnt, String filepath, String title) {

		int cnt10 = (cnt + "").length();
		try (BufferedWriter fw = new BufferedWriter(new FileWriter(filepath + "/" + title + ".csv", true));) {
			for (int i = 0; i < cnt; i++) {
				String userid = "	user" + String.format("%0" + cnt10 + "d", i + 1);

				fw.write(userid);
				fw.newLine();
				
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error");
		}

	}
	/*
	 * public void createCSV(String filepath, String title,List<BoardVO>
	 * boardList,int cols) {
	 * 
	 * try (BufferedWriter fw = new BufferedWriter(new FileWriter(filepath + "/" +
	 * title + ".csv", true));) { for (BoardVO vo:boardList) { for (int j = 0; j <
	 * array.length; j++) {
	 * 
	 * } String userid = "	user" + String.format("%0" + cnt10 + "d", i + 1);
	 * 
	 * fw.write(userid); fw.newLine();
	 * 
	 * }
	 * 
	 * } catch (Exception e) { // TODO: handle exception
	 * System.out.println("error"); }
	 * 
	 * }
	 */

	public void createBoardData() {
		Random rd = new Random();

		int i = 0;
		Map<String, Integer> statistics = new HashMap<>();
		while (i < 1000) {
			int secsum = (int) ((rd.nextGaussian() + 3) * 4 * 60 * 60);

			int hour = secsum / (3600);
			int min = (secsum / 60) % 60;
			int sec = secsum % 60;

			String key = String.format("%02d", hour);
			System.out.println(key + ":" + String.format("%02d", min) + ":" + String.format("%02d", sec));
			if (hour < 0 || hour >= 24)
				continue;
			if (statistics.containsKey(key)) {
				statistics.replace(key, statistics.get(key) + 1);
			} else {
				statistics.put(key, 1);
			}
			i++;
		}
		(new TreeMap<String, Integer>(statistics).keySet()).forEach(e -> log.info(e));
		// System.out.println(secsum);

		/*
		 * String date = "2019-06-10"; DateTime dt = new DateTime();
		 * 
		 * System.out.println(dt);
		 */
	}

	public void readCSV(String filepath, String title) {
		String line;
		try (BufferedReader fr = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File(filepath + "/" + title + ".csv")), "utf-8"));) {
			while ((line = fr.readLine()) != null) {
				System.out.println(line);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
