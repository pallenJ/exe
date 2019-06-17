package org.zerock.data;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.util.Random;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;

public class NewsData {
	ArrayList<String> listFile;
	ArrayList<String> listSentence;
	Random generator = new Random();

	public NewsData() {
		listSentence = new ArrayList<String>();
	}

	public void subDirList(String source) {
		listFile = new ArrayList<String>();

		File dir = new File(source);
		File[] fileList = dir.listFiles();
		try {
			for (int i = 0; i < fileList.length; i++) {
				File file = fileList[i];
				if (file.isFile()) {
					listFile.add(file.getName());
				} else if (file.isDirectory()) {
					subDirList(file.getCanonicalPath().toString());
				}
			}
		} catch (IOException e) {
		}
	}

	public void parseNews(String source) {
		for (String file : listFile) {
			System.out.println(source + file);
			String content = readTextFile(source + file);
			parseTextToParagraph(content);
		}
	}

	public String readTextFile(String textFile) {
		String content = "";
		File readFile;
		BufferedReader in;
		String line;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(textFile), "utf-8"));

			while ((line = in.readLine()) != null) {
				content += line + "\n";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public void parseTextToParagraph(String content) {
		String[] paragraph = content.split("\n");

		for (int i = 0; i < paragraph.length; i++) {
			if (paragraph[i].trim().equals(""))
				continue;
			parseToSentence(paragraph[i]);
		}
	}

	public void parseToSentence(String paragraph) {
		String[] sentence = paragraph.split("\\.");

		for (int i = 0; i < sentence.length; i++) {
			listSentence.add(sentence[i].trim() + ".");
		}
	}

	public List<String> getNews(int cntNews) {

		List<String> genPara = new ArrayList<String>();
		for (int i = 0; i < cntNews; i++) {
			String contents = generateNews();
			// System.out.println(i+"====================");
			// System.out.println(contents);
			// System.out.println("=====================");
			genPara.add(contents);
		}
		return genPara;
	}

	public String generateNews() {
		int cntParagraph = 0;
		int cntSentence = 0;
		List<String> genPara;

		genPara = new ArrayList<String>();
		Collections.shuffle(listSentence);
		cntParagraph = generator.nextInt(3) + 3;

		int pos = 0;
		for (int j = 0; j < cntParagraph; j++) {
			cntSentence = generator.nextInt(3) + 2;
			List<String> subList = listSentence.subList(pos, pos + cntSentence);
			String strPara = implode(". ", subList);
			genPara.add(strPara);

			pos += cntSentence;
		}
		return implode("\n\n", genPara);
	}

	final public static String implode(String glue, List<String> array) {
		boolean first = true;
		StringBuilder str = new StringBuilder();
		for (String s : array) {
			if (!first)
				str.append(glue);
			str.append(s);
			first = false;
		}
		return str.toString();
	}

	public static void main(String args[]) {

		String newsDir = "C:\\upload\\news\\";
		NewsData newsData = new NewsData();
		newsData.subDirList(newsDir);
		newsData.parseNews(newsDir);

		List<String> aaa = newsData.getNews(10);
		for (String string : aaa) {

			System.out.println("========================================");
			System.out.println(string);
		}

		String titleDir = "C:\\upload\\title\\";
		TitleData td = new TitleData();
		td.setTitleList(titleDir);
		List<String> list = td.getTitleShuffle(10000);
		int line = 0;

		for (String slist : list) {
			System.out.print(slist + "	");
			if ((++line) % 10 == 0)
				System.out.println();

		}

		System.out.println(list.size());

		// td.getNews(10);
	}

}