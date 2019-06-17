package org.zerock.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class TestUser.
 */
public class TestUser {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		extractedUser();
	    //extractByHour();
	    

	}

	/**
	 * Extract by hour.
	 */
	private static void extractByHour() {
		
		int cntUser = 1000;
		
		RatioHour rh = new RatioHour();
		
		//---
		ArrayList<String> listUser = new ArrayList<String>();
		
		for(int i=0; i<cntUser;i++)
			listUser.add("user_"+i);
		//-------
		
		Collections.shuffle(listUser);
		
		Iterator<String> it = rh.getRatioHour().keySet().iterator();
		
		int sum = 0;
		while(it.hasNext())
		{
			String hour = it.next();
			Float value = rh.getRatioHour().get(hour);
			int iValue = (int)( Math.ceil(cntUser * value/100));
			sum += iValue;
			System.out.println(hour +":" + value +":"+ iValue);
		}
		// subList()
		
		System.out.println("SUM:"+sum);
	}

	/**
	 * Extracted user.
	 * @author 박준모
	 * 
	 */
	private static void extractedUser() {
		int cntUser = 10;
		int cntPerUser = 8;
		
		Random r = new Random();
		ArrayList<Integer> listUserCnt = new ArrayList<Integer>();
		
		for(int i=0;i<10;i++)
			listUserCnt.add(r.nextInt(cntPerUser)+1);
		
		ArrayList<String> listUser = new ArrayList<String>();
		
		for(int i=0; i<cntUser;i++)
			listUser.add("user_"+i);
		
		for(int i=0;i<10;i++) {
			int cnt = listUserCnt.get(i);
			
			Collections.shuffle(listUser);
			List<String> listExtractedUSer = listUser.subList(0, cnt);
			
			System.out.println(cnt+":"+listExtractedUSer);
		}
	}

}
