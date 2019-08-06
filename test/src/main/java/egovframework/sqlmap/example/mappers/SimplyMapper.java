package egovframework.sqlmap.example.mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.example.dto.TableDTO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import lombok.extern.log4j.Log4j;

@Log4j
@Repository("simplyMapper")
public class SimplyMapper extends EgovAbstractMapper{

	public List<?> list(String table){
		return selectList("simpleList",new TableDTO(table));
	}
	
	public Object selectOne(String table,String col,String val) {
		Map<String, Object> param = new HashMap<>();
		param.put("table", new TableDTO(table));
		param.put("col", col);
		param.put("val", val);
		log.info(val);
		return selectOne("simpleSelect" , param);
	}
	
	
	
}
