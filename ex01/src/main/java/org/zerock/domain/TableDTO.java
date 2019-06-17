package org.zerock.domain;

import org.zerock.service.sta.StaService;

import lombok.Data;

@Data
public class TableDTO {
	
	private final String [] types = {"%Y-%m-%d","%H","%a","%U","%u","%d-%H","%w","%d"};
	public static final int YMD = 0,H = 1,A=2,U_l=3,U_s=4,D_H=5,W=6,D=7;
	
	
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
		if(type==H) { formatColumn2 = "stathour";}else {
			formatColumn="statdate";
		}
	}
	
	public void SetFormatType2(int type) {
		this.formatType2 = types[type];
		if(type==H) { formatColumn2 = "stathour";}else {
			formatColumn2 ="statdate";
		}
	}
	
	private String tableName;
	private String formatType;
	private String formatType2;
	
	
	private String formatColumn="statdate";
	private String formatColumn2="statdate";
	private boolean write=false;

}
