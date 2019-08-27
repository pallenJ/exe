package com.example.asn.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class RecordDTO extends ConcurrentHashMap<String, Map<String, Double>> {

	private static final long serialVersionUID = 1L;
	public static final int D=0, K=1, E=2 , M=3, T = 4, A=5, LT=6, LA=7;
	public static final String[] keys={"default","korean","english","math","sta_total","sta_average","sta_lec_total","sta_lec_average"};
	Set<String> lectures;
	{
		lectures = new HashSet<>();
		lectures.add(keys[K]);
		lectures.add(keys[E]);
		lectures.add(keys[M]);

	}
	
	public static final List<String> STD_NAME_LIST = new ArrayList<String>(Arrays.asList(new String[]{"정형돈", "정준하" , "유재석" , "박명수", "하하","지석진" , "김종국", "개리" , "이광수", "송지효"})) ;

	public RecordDTO() {
		keyRecord = new HashMap<>();
		lectureSetting();
	}

	private Map<String, Map<Double, Set<String>>> keyRecord;// 과목 - > 점수 - > 이름

	private void lectureSetting() {
		for (String lec : lectures) {
			this.put(lec, new HashMap<>());
			keyRecord.put(lec, new HashMap<>());
		}

		this.put("sta_total", new HashMap<>());
		keyRecord.put("sta_total", new HashMap<>());
		this.put("sta_average", new HashMap<>());
		keyRecord.put("sta_average", new HashMap<>());

	}

	public void putlec(String lecture) {
		if (this.containsKey(lecture)) {
			log.info("이미 존재하는 과목 입니다");
		}
		this.put(lecture, new HashMap<>());
	}

	public void putRec(String lecture, String name, double record) {
		if (!this.containsKey(lecture)) {
			this.putlec(lecture);
		}
		this.get(lecture).put(name, record);
		putRecRev(lecture, name, record);
	}

	private void putRecRev(String lecture, String name, double record) {
		Map<Double, Set<String>> revMap = keyRecord.get(lecture);
		if (!revMap.containsKey(record))
			revMap.put(record, new HashSet<>());
		revMap.get(record).add(name);
	}

	public void putRec(String lecture, Map<String, Double> rmap) {
		if (rmap == null || rmap.keySet().size() == 0)
			return;
		this.put(lecture, rmap);
		for (String stdName : rmap.keySet()) {
			putRecRev(lecture, stdName, rmap.get(stdName));
		}

	}
	
	public List<String> stdSortList(int lec){
		if(lec == D) return RecordDTO.STD_NAME_LIST;
		List<String> stdNameList = new ArrayList<>();
		String lecStr = keys[lec];
		Map<Double, Set<String>> invRecTable = new TreeMap<>(keyRecord.get(lecStr));
		for (Iterator<Double> itr = invRecTable.keySet().iterator(); itr.hasNext();) {
			double recTemp = itr.next(); 
			stdNameList.addAll(new TreeSet<String>(invRecTable.get(recTemp)));
		}
		
		return stdNameList;
	}
	
	public void setTotal() {
		Map<String, Double> totalRec = new HashMap<>();
		Map<String, Double> aveRec = new HashMap<>();
		Map<String, Double> totalLec = new HashMap<>();
		Map<String, Double> aveLec = new HashMap<>();

		double stdCnt = RecordDTO.STD_NAME_LIST.size();
		double lecCnt = lectures.size();
		totalRec = new HashMap<>();
		double l_total;
		for (String lecture : lectures) {
			l_total = 0.0;
			Map<String, Double> stdTemp = this.get(lecture);
			for (String name : stdTemp.keySet()) {
				double adderRec = stdTemp.get(name);
				l_total += adderRec;
				if (!totalRec.containsKey(name)) {
					totalRec.put(name, 0.0);
					aveRec.put(name, 0.0);
				}
				double bfRec = totalRec.get(name);
				totalRec.replace(name, bfRec + adderRec);
				aveRec.replace(name, (bfRec + adderRec) / lecCnt);

			}
			totalLec.put(lecture, l_total);
			aveLec.put(lecture, l_total / stdCnt);

		}

		Map<Double, Set<String>> totalInv = keyRecord.get("sta_total");
		Map<Double, Set<String>> avgInv = keyRecord.get("sta_average");
		
		
		
		for (String name : totalRec.keySet()) {
			double totalRecV = totalRec.get(name);
			double avgRecV = totalRecV / stdCnt;

			if (!totalInv.containsKey(totalRecV)) {
				totalInv.put(totalRecV, new HashSet<>());
				avgInv.put(avgRecV, new HashSet<>());
			}
			totalInv.get(totalRecV).add(name);
			avgInv.get(avgRecV).add(name);
		}

		if (this.keySet().contains(keys[T])) {
			this.replace(keys[T], totalRec);
			this.replace(keys[A], aveRec);
		} else {
			this.put(keys[T], totalRec);
			this.put(keys[A], aveRec);
		}
		if (this.containsKey(keys[LT])) {
			this.replace(keys[LT], totalLec);
			this.replace(keys[LA], aveLec);
		} else {
			this.put(keys[LT], totalLec);
			this.put(keys[LA], aveLec);
		}

	}

}
