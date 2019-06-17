package org.zerock.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TitleData extends NewsData{

	List<List<String>> wordList;
	List<String> allwords;
	
	public TitleData() {
		super();
		wordList=new ArrayList<>();
		allwords=new ArrayList<>();
	}
	
	public void setTitleList(String titleDir) {
		subDirList(titleDir);
		parseNews(titleDir);
		for (String stn : listSentence) {
			if(stn==null||stn.length()==0) continue;
		
			String[] wordsTemp = stn.split(" ");
			List<String> tempList = new ArrayList<>();
			
			for (String wtemp : wordsTemp) {
				if("\",.\'".contains(stn)) continue;
				tempList.add(wtemp);
			}
			if(tempList.size()==0) continue;
			wordList.add(tempList);
			allwords.addAll(tempList);
		}
		
	}
	
	public List<List<String>> getTitle2() {
		
		List<String> rs = allwords;
		Collections.shuffle(rs);
		return transList(rs, wordList);
		
	}
	
	public List<String> getTitleShuffle(int n) {
		List<String> tmp = getTitleShuffle();
		if(n<=tmp.size())
		return tmp.subList(0, n);
		else {
			List<String> rs = new ArrayList<String>();
			int size = tmp.size()/10;
			while(true) {
				
				if(n<=size) {
				rs.addAll(getTitleShuffle(n)); 
				break;	
				}
				rs.addAll(getTitleShuffle(size));
				
				n-=size;
			}
			
			return rs;
			
		}
			
	}
	public List<String> getTitleShuffle() {
		List<String> rs = getTitle();
		Collections.shuffle(rs);
		return rs;
	}
	public List<String> getTitle() {
		
		List<String> rs = new ArrayList<>();
		for (List<String> list: wordList) {
			Collections.shuffle(list);
			String tmp = "";
			for (String string : list) {
				tmp += string+" ";
			}
			rs.add(tmp.replace(".", ""));
		}
		
		return rs;
		
	}
	private List<List<String>> transList(List<String>single,List<List<String>> dlist) {
		List<List<String>> rs = new ArrayList<>();
		int startIdx=0;
		int endIdx  =0;
		for (List<String> list : dlist) {
		endIdx +=list.size();
		rs.add(single.subList(startIdx, endIdx));
		startIdx = endIdx;
		}
	    return rs;	
	}
	
	
	
}
