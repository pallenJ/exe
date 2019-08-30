package com.example.asn.service;

import java.util.Map;

import com.example.asn.domain.RecordCSVDTO;

public interface CalCSVService {

	public RecordCSVDTO getDto();
	public void sort(int code);
	
}
