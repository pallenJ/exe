package egovframework.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TableDTO {

	private String tableName;
	private boolean where; 
	
	public TableDTO(String tableName) {
		this.tableName =tableName;
	}
	
}
