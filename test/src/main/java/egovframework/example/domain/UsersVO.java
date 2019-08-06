package egovframework.example.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersVO {

	private  String username;
	private  String Password;
	private int enabled;
	
}
