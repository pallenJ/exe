package com.example.asn.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

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



}
