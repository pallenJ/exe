package com.example.asn.service;

import java.util.Map;

import com.example.asn.domain.RecordDTO;

public interface CalService {
	
	public void test();
	public void fileTest();
	public Map<String,Double> recordTable(String sbj);
	public RecordDTO recordMap();
}
