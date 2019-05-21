package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criteria {
	private int pageNum;
	private int amount;
	
	private String type;
	private String keyword;
	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	public Criteria(int pageNum, int amount,String type, String keyword) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
		this.type   = type;
		this.keyword = keyword;
	}
	public Criteria() {
		this(1,10);
	}
	
	public String[] getTypeArr() {
		return type == null? new String[]{}: type.split("");
	}
	
}
