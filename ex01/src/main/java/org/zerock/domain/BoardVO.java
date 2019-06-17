package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {

	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
	private int replyCnt;
	
	public BoardVO() {}

	public BoardVO(String title, String content, String writer, Date regdate, Date updateDate) {
		super();
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regdate = regdate;
		this.updateDate = updateDate;
	}
	
}
