package org.zerock.data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.zerock.domain.DayInfo;

public class ReadCSV
{
	String csvFile;
	String delimiter;
	ArrayList <ArrayList<String>> lines;
	
	public ReadCSV(String csvFile, String delimiter)
	{
		this.csvFile = csvFile;
		this.delimiter = delimiter;
	}

	public void loadFile() {
		String line = null;
		File readFile = new File(this.csvFile);
		lines = new ArrayList <ArrayList<String>>();
		try {
			// 한글깨짐
			// BufferedReader in = new BufferedReader(new FileReader(readFile));
			// utf-8 처리
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(readFile), "utf-8"));

			while( (line = in.readLine()) != null) {
				ArrayList <String> rows = new ArrayList <String>();
				String[] arr = line.split(delimiter);
				rows.addAll(Arrays.asList(arr));
				lines.add(rows);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public HashMap<String, ArrayList<DayInfo>> toHashData()
	{
		HashMap<String, ArrayList<DayInfo>> hashDayInfo = new HashMap<String, ArrayList<DayInfo>>();
		Iterator<ArrayList<String>> it = lines.iterator();
		ArrayList<DayInfo> listDayInfo;

		while(it.hasNext())
		{
			ArrayList<String> rows = it.next();
			String dayString = rows.get(0);
			String dayTitle = rows.get(1);
			String flagHoliday = rows.get(2);

			DayInfo dayInfo = new DayInfo(rows.get(1), rows.get(2));
			
			if (hashDayInfo.containsKey(dayString))
			{
				listDayInfo = hashDayInfo.get(dayString);
			}
			else
			{
				listDayInfo = new ArrayList<DayInfo>();
			}
			listDayInfo.add(dayInfo);
			hashDayInfo.put(dayString, listDayInfo);
		}
		return hashDayInfo;
	}

	public static void main(String args[])
	{
		ReadCSV readCSV = new ReadCSV("201905.csv", "\t");
		readCSV.loadFile();
		HashMap<String, ArrayList<DayInfo>> hashDayInfo = readCSV.toHashData();

		Iterator <String> it = hashDayInfo.keySet().iterator();
		while(it.hasNext())
		{
			String key = it.next();
			ArrayList<DayInfo> listDayInfo = hashDayInfo.get(key);
			for(DayInfo dayInfo : listDayInfo)
				System.out.println(key +" " + dayInfo.getFlagHoliday() +" "+ dayInfo.getDayTitle() );
		}

	}
}