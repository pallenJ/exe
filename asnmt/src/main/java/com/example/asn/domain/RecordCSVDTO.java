package com.example.asn.domain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import lombok.Getter;

public class RecordCSVDTO {

	public enum LecEnum{
		name, korean, english, math, re
	}
	
	private static final String[] HEADERS = { "name", "korean", "english", "math" };
	@Getter
	private List<CSVRecord> recordTable;
	
	private Comparator<CSVRecord> comparator = (o1,o2) ->{return (int) (o1.getRecordNumber()-o2.getRecordNumber());};
	
	public RecordCSVDTO() throws IOException {
		setRecord();
	}

	private void setRecord() throws IOException {
		
		recordTable = new ArrayList<>();
		
		Reader in = new FileReader("C:\\prtc\\csv\\record.csv");

		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(HEADERS).withFirstRecordAsHeader().parse(in);
		
		for (CSVRecord csvRecord : records) {
			recordTable.add(csvRecord);
		}
	}
	
	public void sort(int code) {
		try {
				String lec = HEADERS[code];
				comparator = (o1,o2) -> {return -Integer.parseInt(o2.get(lec)) + Integer.parseInt(o1.get(lec));};
		} catch (Exception e) {
			comparator = (o1,o2) ->{return (int) (o1.getRecordNumber()-o2.getRecordNumber());};
		}
		Collections.sort(recordTable, comparator);
	}
	
}
