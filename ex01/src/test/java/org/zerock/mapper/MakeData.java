package org.zerock.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.zerock.data.NewsData;
import org.zerock.data.RandomString;
import org.zerock.data.RatioHour;
import org.zerock.data.TitleData;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.UserDataMapper;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

// TODO: Auto-generated Javadoc
/**
 * The Class MakeData.
 */

@Log4j
public class MakeData {
	
	
	
	
	@Getter
	@Setter(onMethod_ = @Autowired)
	private UserDataMapper umapper;
	
	/** The daily count. */
	int dailyCount;
	
	@Setter
	int firstnum=0;
	/** The start date. */
	String startDate;
	
	/** The end date. */
	String endDate;

	/** The ratio hour. */
	HashMap<String, Float> ratioHour;
	HashMap<String, Integer> usersCnt;
	
	/** The o ratio hour. */
	RatioHour oRatioHour;
	
	/** The list date. */
	ArrayList<String> listDate;
	
	/** The list user. */
	ArrayList<String> listUser;
	//MessageDigest md;
	@Getter
	List<BoardVO> blist;
	/**
	 * Instantiates a new make data.
	 */
	public MakeData() {
		oRatioHour = new RatioHour();
		ratioHour = oRatioHour.getRatioHour(); 
		dailyCount = 1000;
		blist = new ArrayList<BoardVO>();
	}
	public MakeData(int dailyCount) {
		oRatioHour = new RatioHour();
		ratioHour = oRatioHour.getRatioHour(); 
		this.dailyCount = dailyCount;
		blist = new ArrayList<BoardVO>();
	}

	/**
	 * Gets the daily count.
	 *
	 * @return the daily count
	 */
	public int getDailyCount() {
		return dailyCount;
	}

	/**
	 * Sets the daily count.
	 *
	 * @param dailyCount the new daily count
	 */
	public void setDailyCount(int dailyCount) {
		this.dailyCount = dailyCount;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * Prepare.
	 */
	//prepare -> process 순서로 실행
	public void prepare(ArrayList<String> memorig) {
		
		//임의의 날짜 생성
		makeDateList();
		
		//임의의 유저 생성
		//makeUserList();
		
		//받아온 랜덤 array 설정
		generateUserList(memorig);
	}

	// 데이터(뉴스)를 넣을 날짜 생성
	/**
	 * Make date list.
	 */
	// 임시로 날짜 add -> Date등 이용하여 작업할 것(startDate, endDate)
	private void makeDateList() {
		listDate = new ArrayList<String>();
		
		listDate.add("2019-06-01");
		listDate.add("2019-06-02");
		listDate.add("2019-06-03");
		listDate.add("2019-06-04");
		listDate.add("2019-06-05");
		listDate.add("2019-06-06");
		listDate.add("2019-06-07");
	}

	// 데이터(뉴스)에 넣을 유저 생성
	/**
	 * Make user list.
	 */
	//dailycount 쓰지말고 유저 수 정하는 변수 하나 만들 것
	
	private void generateUserList(ArrayList<String>membrOrigin) {
		listUser = new ArrayList<String>();
		usersCnt = new HashMap<String, Integer>();
		Random rd = new Random();
		int length = 0;
		int size = membrOrigin.size();
		while(length<dailyCount) {
			String usertmp = membrOrigin.get(rd.nextInt(size));
			listUser.add(usertmp);
			
			if(usersCnt.containsKey(usertmp)) {
				usersCnt.replace(usertmp, usersCnt.get(usertmp)+1);
			}else {
				usersCnt.put(usertmp, 1);
			}
			
			length++;
		}
	}
	
	private void makeUserList() {
		listUser = new ArrayList<String>();
		RandomString gen = new RandomString(8, ThreadLocalRandom.current());
		for (int i = 0; i < dailyCount; i++) {
			listUser.add(gen.nextString());
		}
		
	}

	// RatioHour Class에서 시간별 비율 받아와서 생성 및 주입
	/**
	 * Process.
	 * @throws ParseException 
	 */
	// http://mwultong.blogspot.com/2006/09/java-gaussian-gaussian-random-numbers.html
	public void process() throws ParseException {
		
		Random oRandom = new Random();

		Iterator<String> it = listDate.iterator();
		boolean flag = it.hasNext();
		while (flag) {
			
			double gDouble = (oRandom.nextGaussian() + 3) * (dailyCount*3/10);
			
			if(gDouble<1) continue;
			
			String tgtDate = it.next();
			
			int tgtNumber = (int) Math.ceil(gDouble);
			
			System.out.println("tgtNum:"+tgtNumber);
			
			if (tgtNumber > dailyCount)
				tgtNumber = (dailyCount*4)/5;
			
			if(tgtNumber<1) continue;
			
			HashMap<String, Integer> writeHourCount = getWriteHourCount(tgtNumber);
			List<String> listWriteUser = getWriteUser(tgtNumber);

			doWrite(tgtDate, listWriteUser, writeHourCount);
			flag = it.hasNext();
		}
		
	}
	
	/**
	 * Gets the write user.
	 *
	 * @param tgtNumber the tgt number
	 * @return the write user
	 */
	//makeUserList()의 결과물(임의의 사용자명)을 섞어서 tgtNumber(만들개수?)만큼 가져옴
	private List<String> getWriteUser(int tgtNumber) {
		
		  Collections.shuffle(listUser); 
		  return listUser.subList(0, tgtNumber);
		 
		//return umapper.randId(tgtNumber);
	}
	
	
	/**
	 * Gets the write hour count.
	 *
	 * @param tgtNumber the tgt number
	 * @return the write hour count
	 */
	private HashMap<String, Integer> getWriteHourCount(int tgtNumber) {
		LinkedHashMap<String, Integer> writeHourCount = new LinkedHashMap<String, Integer>();

		Iterator<String> it = ratioHour.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			float val = ratioHour.get(key);
			writeHourCount.put(key, (int) Math.floor(val * tgtNumber / 100.0));
		}

		return writeHourCount;

	}

	/**
	 * Do write.
	 *
	 * @param tgtDate the tgt date
	 * @param listWriteUser the list write user
	 * @param writeHourCount the write hour count
	 * @throws ParseException 
	 */
	private void doWrite(String tgtDate, List<String> listWriteUser, HashMap<String, Integer> writeHourCount) throws ParseException {
		//System.out.println(dailyCount);
		Iterator<String> it = writeHourCount.keySet().iterator();
		
		
		int curPos = 0;
		int bdCnt = 0;
		
		while (it.hasNext()) {
			String key = it.next();
			int val = writeHourCount.get(key); 

			ArrayList<String> writeMinSec = makeWriteMinSec(val);
			
			
			String newsDir = "C:\\upload\\news\\"; NewsData newsData = new NewsData();
			newsData.subDirList(newsDir); newsData.parseNews(newsDir);
			  
			String titleDir = "C:\\upload\\title\\";
			TitleData td = new TitleData(); td.setTitleList(titleDir);
			
			List<String>contents = newsData.getNews(val);
			List<String> titles = td.getTitleShuffle(val);
			
			
			for (int j = 0; j < val; j++) {
				
				bdCnt++;
				String writeDateTime = tgtDate + " " + key + ":" + writeMinSec.get(j);
				String writer = listWriteUser.get(curPos + j);
				// 날짜 시간 아이디까지 생성됨
				//System.out.println(j + "\t" + writeDateTime + "\t" + writer);
				//String numTemp = String.format("%0"+((dailyCount+"").length())+"d", bdCnt);
				
				Date InsDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(writeDateTime);
				
				BoardVO vo =new BoardVO(titles.get(j), contents.get(j), writer, InsDate,InsDate);
				
				blist.add(vo);
				
				//System.out.println(vo.getRegdate());
				//System.out.println(j + "\t" + vo.getUpdateDate() + "\t" + vo.getWriter());
				// 앞서 만든 것에 제목과 내용을 넣어야함.
				// ReadCSV 실습 시 했던 문단 쪼개기와 합치기(?)로 해야할듯 (NewsData.java)
				// getNewsTitle();
				// getNewsContent();
			}
			curPos += val;
		}
		//System.out.println(bdCnt);
	}

	/**
	 * Make write min sec.
	 *
	 * @param count the count
	 * @return the array list
	 */
	//임의의 시간(분, 초)를 만들어서 ArrayList writeMinSec에 저장 후 return
	public ArrayList<String> makeWriteMinSec(int count) {
		ArrayList<String> writeMinSec = new ArrayList<String>();
		Random r = new Random();
		for (int i = 0; i < count; i++) {
			String min = String.format("%02d", r.nextInt(59));
			String sec = String.format("%02d", r.nextInt(59));
			writeMinSec.add(min + ":" + sec);
		}
		Collections.sort(writeMinSec);

		return writeMinSec;
	}
}