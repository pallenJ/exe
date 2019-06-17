package org.zerock.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

// TODO: Auto-generated Javadoc
/**
 * The Class UserTest.
 */
public class UserTest {
	
	/** The users. */
	List<String> users;
	
	
	/** The usercnt. */
	int usercnt=1000;
	
	/** The rh. */
	RatioHour rh; 
	
	/**
	 * Instantiates a new user test.
	 */
	public UserTest() {
		generates();
	}
	
	public UserTest(List<String>users) {
		
		generates(users);
	}
	
	/**
	 * Instantiates a new user test.
	 *
	 * @param cnt the cnt
	 */
	public UserTest(int cnt) {
		this.usercnt= cnt;
		generates();
	}
	
	/**
	 * Generates.
	 */
	private void generates(List<String>userList) {
		rh = new RatioHour();
		this.usercnt = userList.size();
		this.users =userList;
	}
	
	private void generates() {
		rh = new RatioHour();
		users=new ArrayList<>();
		
		int cnt = 0;
		int cnt10 = (usercnt+"").length();
		while(cnt<usercnt) {
			users.add("users"+String.format("%0"+cnt10+"d", cnt));
			cnt++;
		}
	}

	
	/**
	 * Extract by hour.
	 *
	 * @return the map
	 */
	public Map<String,List<String>> extractByHour() {
		Map<String,List<String>> userMap = new HashMap<>();
		List<String> shuffledUser;
		
		int sum = 0;
		
		for (Iterator<String> itr = rh.getRatioHour().keySet().iterator(); itr.hasNext();) {
			
			shuffledUser = users;
			Collections.shuffle(shuffledUser);
			String hour  = itr.next();
			Float value = rh.getRatioHour().get(hour);
			int iValue = (int)(Math.ceil(usercnt*value/100));
			
			sum += iValue;
			
			if(sum>usercnt) {
				int temp = iValue;
				iValue = iValue+usercnt-sum;
				value = (((float)iValue)*100)/usercnt;
				sum -=temp; 
			    sum += iValue;
			}
			List<String> aaa = new ArrayList<>();
			aaa.addAll(shuffledUser.subList(0, iValue));
			System.out.println(aaa);
			userMap.put(hour, aaa);
			//System.out.println(hour +" map:" + userMap.get(hour));
			System.out.println(hour +":" + value +":"+ iValue);
			//System.out.println("full Map:"+userMap);
		}
		// subList()
		
		System.out.println("SUM:"+sum);
		return userMap;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		test();
	}
	
	/**
	 * Test.
	 */
	public static void test() {
		UserTest ut = new UserTest(1000);
		//System.out.println(ut.extractByHour());
		
		  Map<String,List<String>> temp = ut.extractByHour();
		  
		  for(Iterator<String> iterator= new TreeMap<String,List<String>>(temp).keySet().iterator();iterator.hasNext();) {
			  	String key = iterator.next();
			  	List<String> tlist = temp.get(key);
			  	System.out.println(key+"("+tlist.size()+")"+":"+tlist);
			  }
		  
		
	}
}
