package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.SimpleTableDTO;

public interface SimpleCustomMapper {
	
	
	public int getCustomCnt(@Param("table") SimpleTableDTO table);
	public String getCustomListOne(@Param("table") SimpleTableDTO table);
	public List<?> getCustomList(@Param("table") SimpleTableDTO table);
	public List<String> getCustomListCol(@Param("table") SimpleTableDTO table);
	
	
}
