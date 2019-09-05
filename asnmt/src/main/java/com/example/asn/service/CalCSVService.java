package com.example.asn.service;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import com.example.asn.domain.RecordCSVDTO;

public interface CalCSVService {

	public RecordCSVDTO getDto();
	public void sort(int code);
	public List<?> getCSVList();
}
