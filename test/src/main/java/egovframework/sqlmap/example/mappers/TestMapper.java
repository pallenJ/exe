package egovframework.sqlmap.example.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import egovframework.example.dto.TableDTO;
import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("testMapper")
public class TestMapper extends EgovAbstractMapper{
	
	public String timeTest() {
		return selectOne("timeTest");
	}
	
	public int tableCnt(String tableName) {
		TableDTO table = new TableDTO(tableName); 
		return (int)selectOne("tblCnt",table);
	}
}
