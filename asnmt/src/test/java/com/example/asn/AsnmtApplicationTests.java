package com.example.asn;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.asn.domain.RecordDTO;
import com.example.asn.service.CalService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AsnmtApplicationTests {
	@Resource
	CalService calService;
	
	String [] lectures = {"korean","english","math"};
	
	enum lecs{
		korean, english, math
	}
	@Test
	public void contextLoads() {
		
	
		RecordDTO rdto = calService.recordMap();
		Map<String, Map<Double, Set<String>>> staMap = rdto.getKeyRecord();
		Set<String> keySetSorted = new TreeSet<>(staMap.keySet());
		
		for (String key : keySetSorted) {
			//if(!key.equals("total"))continue;
			log.info("lecture:{}",key);
			
			TreeMap<Double, Set<String>> temp = new TreeMap<>(staMap.get(key));
			for (Double rec : temp.keySet()) {
				
				log.info("	record:{} 점", rec%1>0? String.format("%.2f", rec):Math.round(rec));
				log.info("		name:{}",new TreeSet<>(temp.get(rec)));
				log.info("\n");
			}
			log.info("\n");
			log.info("\n");
		}
		
		for(String key : rdto.keySet()) {
			if(!key.contains("sta_lec")) continue;
			Map<String, Double> temp = rdto.get(key);
			for (String string : temp.keySet()) {
				double rec = temp.get(string);
				log.info("	{}:{}:{} 점", key, string , rec%1>0? String.format("%.2f", rec):Math.round(rec));
			}
		}
		
		
	}
	
	
}
