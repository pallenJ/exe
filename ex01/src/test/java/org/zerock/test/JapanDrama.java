package org.zerock.test;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JapanDrama {
	
	
	private String media;
	private String charactor;
	private String casting;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		JapanDrama[] japanDrama;
		/*
		 * json처럼 데이터를 넣는 부분
		 */
		JapanDrama jd1 = new JapanDrama("한자와 나오키", "한자와 나오키", "사카이 마사토");
		JapanDrama jd2 = new JapanDrama("리갈하이", "코미카도 켄스케", "사카이 마사토");
		JapanDrama jd3 = new JapanDrama("고독한 미식가", "이노가시라 고로", "마츠시게 유타카");
		japanDrama = new JapanDrama[]{jd1,jd2,jd3};
		/*
		 * 데이터넣는 부분 끝
		 */
		String japan_drama = gson.toJson(japanDrama); // json타입
		
		System.out.println("========= Object => Json ==========");
		System.out.println(japan_drama);

        System.out.println("========= Json => Object =========");
        System.out.println(gson.fromJson(japan_drama, ArrayList.class));
        
        japan_drama = new GsonBuilder().serializeNulls().create().toJson(japanDrama);
        System.out.println("========= Object => Json =========");
        System.out.println(japan_drama);
 
        System.out.println("========= Json => Object =========");
		System.out.println(gson.fromJson(japan_drama, ArrayList.class));
        
	}

}
