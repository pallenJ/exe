package com.example.asn.domain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.sun.xml.messaging.saaj.packaging.mime.Header;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class RecordCSVDTO {

	public enum LecEnum{
		name, korean, english, math,total, re
	}
	
	public static final String[] HEADERS = { "name", "korean", "english", "math" };
	private List<CSVRecord> recordTable;
	private Map<String, Map<String, Object>> staMap;
	
	private Comparator<CSVRecord> comparator = (o1,o2) ->{return (int) (o1.getRecordNumber()-o2.getRecordNumber());};
	
	public RecordCSVDTO() throws IOException {
		setRecord();
	}

	private void setRecord() throws IOException {
		
		recordTable = new ArrayList<>();
		
		Reader in = new FileReader("C:\\prtc\\csv\\record.csv");

		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).withFirstRecordAsHeader().parse(in);
		
		for (CSVRecord csvRecord : records) {
			log.info(csvRecord.get("name").toString());
			recordTable.add(csvRecord);
		}
		setSta();
	}
	
	private void setSta() {
		int total[] = {0,0,0};
		int total_temp;
		int rec_temp;

		staMap = new HashMap<>();
		for (CSVRecord csvR : recordTable) {
			total_temp = 0;
			for (int i = 0; i < total.length; i++) {
				rec_temp = Integer.parseInt(csvR.get(HEADERS[i+1]));
				total[i]+= rec_temp;
				total_temp += rec_temp;
			}
			Map<String, Object> mapTemp = new HashMap<>();
			
			double avg = ((double)total_temp)/((double)total.length);
			
			mapTemp.put("total", total_temp);
			mapTemp.put("average", avg);
			staMap.put(csvR.get(HEADERS[0]), mapTemp);
		}
		Map<String,Object> totalMapTmp  = new HashMap<>();
		Map<String,Object> avgMapTmp  = new HashMap<>();
		
		for (int i = 0; i < total.length; i++) {
			double avg = ((double)total[i])/((double)recordTable.size());
			totalMapTmp.put(HEADERS[i+1], total[i]);
			avgMapTmp.put(HEADERS[i+1], avg);
		}
		
		staMap.put("lec_total", totalMapTmp);
		staMap.put("lec_avg", avgMapTmp);
		
	}
	
	public void sort(int code) {
		try {
			if(code == LecEnum.total.ordinal()) {
				totalSort();
			}else {
				String lec = HEADERS[code];
				comparator = (o1,o2) -> {return Integer.parseInt(o2.get(lec)) - Integer.parseInt(o1.get(lec));};
			}
		} catch (Exception e) {
			comparator = (o1,o2) ->{return (int) (o1.getRecordNumber()-o2.getRecordNumber());};
		}
		Collections.sort(recordTable, comparator);
	}
	private void totalSort() {
		comparator = (o1,o2) ->{
			return Integer.parseInt(o2.get(HEADERS[LecEnum.korean.ordinal()])) - Integer.parseInt(o1.get(LecEnum.korean.ordinal()))
					+ Integer.parseInt(o2.get(HEADERS[LecEnum.english.ordinal()])) - Integer.parseInt(o1.get(LecEnum.english.ordinal()))
					 + Integer.parseInt(o2.get(HEADERS[LecEnum.math.ordinal()])) - Integer.parseInt(o1.get(LecEnum.math.ordinal()));
		};
	}
	
	
}
