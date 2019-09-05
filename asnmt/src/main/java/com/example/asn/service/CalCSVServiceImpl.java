package com.example.asn.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.example.asn.domain.RecordCSVDTO;
import com.example.asn.domain.RecordDTO;

@Service("calCSVService")
public class CalCSVServiceImpl implements CalCSVService{

	RecordCSVDTO dto;
	{
		try {
			dto = new RecordCSVDTO();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			dto = null;
		}
	}
	@Override
	public RecordCSVDTO getDto() {
		// TODO Auto-generated method stub
		return dto;
	}

	@Override
	public void sort(int code) {
		Comparator<CSVRecord> comparator;
		try {
			String lec = RecordCSVDTO.HEADERS[code];
			comparator = (o1,o2) -> {return -Integer.parseInt(o2.get(lec)) + Integer.parseInt(o1.get(lec));};
	} catch (Exception e) {
		comparator = (o1,o2) ->{return (int) (o1.getRecordNumber()-o2.getRecordNumber());};
	}
	Collections.sort(dto.getRecordTable(), comparator);
		
	}

	@Override
	public List<CSVRecord> getCSVList() {
		// TODO Auto-generated method stub
		try (Reader in = new FileReader("C:\\prtc\\csv\\record.csv");){
			Iterable<CSVRecord> records =  
			 CSVFormat.DEFAULT.withHeader(new String[]{""}).withFirstRecordAsHeader().parse(in);
			List<CSVRecord> list = new ArrayList<>();
			for (CSVRecord csvRecord : records) {
				list.add(csvRecord);
			}
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}

	}
	


}
