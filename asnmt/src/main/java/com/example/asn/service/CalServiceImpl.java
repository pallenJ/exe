package com.example.asn.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.asn.domain.RecordDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CalServiceImpl implements CalService {

	@Override
	public void test() {
		// TODO Auto-generated method stub
		log.info("successs");
	}

	@Override
	public void fileTest() {
		// TODO Auto-generated method stub
		String line;
		String dir = "C:\\dat\\";
		try (BufferedReader br = new BufferedReader(new FileReader(dir+"english.dat"));) {
			while (true) {
				if ((line = br.readLine()) == null)
					break;
				//System.out.println(line + " 	idx:"+line.indexOf("\t"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Double> recordTable(String sbj) {
		// TODO Auto-generated method stub
		return recordTablePR(sbj);
	}
	
	private Map<String, Double> recordTablePR(String sbj) {
		// TODO Auto-generated method stub
		Map<String, Double> rs = new HashMap<>();
		String line;
		String dir = "C:\\dat\\";
		try (BufferedReader br = new BufferedReader(new FileReader(dir+sbj+".dat"));) {
			String key;
			double value;
			while (true) {
				if ((line = br.readLine()) == null)
					break;
				key = line.split("\t")[0];
				value = Long.parseLong(line.split("\t")[1]);
				rs.put(key, value);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	@Override
	public RecordDTO recordMap() {
		// TODO Auto-generated method stub
		RecordDTO rdto = new RecordDTO();
		for (String key : rdto.keySet()) {
			if(key.startsWith("sta_"))continue;
			rdto.putRec(key, recordTablePR(key));
		}
		rdto.setTotal();
		return rdto;
	}

}
