package egovframework.example.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RolesVO {
	private String authority;
	private String role_name;
	private String description;
	private Date create_date;
	private Date modify_date;
}
