package org.zerock.domain;

import java.util.HashMap;
import java.util.List;

import org.zerock.service.sta.StaService;

import lombok.Data;

@Data
public class TableDTO {
	
	private final String [] types = {"%Y-%m-%d","%H","%a","%U","%u","%d-%H","%w","%d"};
	public static final int YMD = 0,H = 1,A=2,U_l=3,U_s=4,D_H=5,W=6,D=7;
	public HashMap<String, List <String>> colList;
	
	public TableDTO(String tableName,int type1,int type2) {
		super();
		this.tableName = tableName;
		try {
			SetFormatType(type1);
			SetFormatType2(type2);
			
		} catch (Exception e) {
		}
	}
	public TableDTO(String tableName,int type) {
		super();
		this.tableName = tableName;
		try {
			SetFormatType(type);
		} catch (Exception e) {
			this.formatType = types[YMD];			
		}
	}
	public TableDTO(String tableName) {
		super();
		this.tableName = tableName;
		setFormatColumn(types[YMD]);
	}
	
	public TableDTO() {
		super();
		this.tableName = StaService.BOARD_10;
		this.formatType = types[YMD];		
	}
	
	public void SetFormatType(int type) {
		this.formatType = types[type];
		this.formatColumn = "stat" + (type==H?"hour":"date");
	}
	
	public void SetFormatType2(int type) {
		this.formatType2   = types[type];
		this.formatColumn2 = "stat" + (type==H?"hour":"date");
	}
	
	public void SetFormatType(String type) {
		this.formatType = type;
	}
	
	public void SetFormatType2(String type) {
		this.formatType2 = type;
	}
	
	private String tableName;
	private String formatType;
	private String formatType2;
	
	
	private String formatColumn="statdate";
	private String formatColumn2="statdate";
	private boolean write=false;

}
