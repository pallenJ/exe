package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTableDTO {
	
	public static final String EQUAL = "=", LARGER = ">" , LESS = "<";
	
	private String tableName;
	private String get;
	private String col;
	private String wcol;
	private String value;
	private String where;
	private String num;

	public SimpleTableDTO(String tableName,String col,String wcol,String num) {
		setTableName(tableName);
		setCol(col);
		setWcol(wcol);
		setNum(num);
	}
	
}
