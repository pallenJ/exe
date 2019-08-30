package com.example.asn.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.example.asn.domain.RecordDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CalServiceImpl implements CalService {

	final String dir = "C:\\prtc\\csv\\"; // "C:\\prtc\\dat\\";

	@Override
	public void test() {
		// TODO Auto-generated method stub
		log.info("successs");
	}

	@Override
	public void fileTest() {
		// TODO Auto-generated method stub
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(dir + "english.dat"));) {
			while (true) {
				if ((line = br.readLine()) == null)
					break;
				// System.out.println(line + " idx:"+line.indexOf("\t"));
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
		log.info(sbj);

		// String key; double value;
		try (Reader in = new FileReader(dir + sbj + ".csv");) {
			Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader(new String[] { "name", "record" })
					.withFirstRecordAsHeader().parse(in);

			for (CSVRecord csvRecord : records) {
				rs.put(csvRecord.get("name"), Double.parseDouble(csvRecord.get("record")));
			}

		} catch (Exception e) { // TODO Auto-generated catch block
			log.info("error");
		}

		/*
		 * String line; try (BufferedReader br = new BufferedReader(new
		 * FileReader(dir+sbj+".dat"));) { String key; double value; while (true) { if
		 * ((line = br.readLine()) == null) break; key = line.split("\t")[0]; value =
		 * Long.parseLong(line.split("\t")[1]); rs.put(key, value); } } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
		return rs;
	}

	@Override
	public RecordDTO recordMap() {
		// TODO Auto-generated method stub
		RecordDTO rdto = new RecordDTO();
		for (String key : rdto.keySet()) {
			if (key.startsWith("sta_"))
				continue;
			rdto.putRec(key, recordTablePR(key));
		}
		rdto.setSta();
		return rdto;
	}

}
