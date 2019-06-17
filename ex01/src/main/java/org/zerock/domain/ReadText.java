package org.zerock.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;

@Getter
public class ReadText {

	private List<String> listFile;
	private Set<String> listSentence;
	
	public ReadText() {
		listFile = new ArrayList<>();
		listSentence = new HashSet<>();
	}
}
