package org.zerock.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.TableDTO;

@Mapper
public interface UserDataMapper {

	public void insertAll(HashMap<String,?> data);
	public void insert(String userid);
	public Set<?> selectAll();
	public ArrayList<String> listAll();
	public int count();
	
	@Select("SELECT userid FROM users_data ORDER BY RAND() LIMIT #{cnt}")
	public ArrayList<String> randId(int cnt);
	
	public void insertAllBoard(HashMap<String,?> data);
	public ArrayList<BoardVO> listByTableName(@Param("table") TableDTO table);
	public List<?> getBrdStatistics(@Param("table") TableDTO table);
	public List<BoardVO> getListPaging(@Param("table") TableDTO table,@Param("cri") Criteria cri);
	public int getCountPaging(@Param("table") TableDTO table,@Param("cri") Criteria cri);
	
	public List<?> getStatistic(@Param("table") TableDTO table);
	public List<?> getStatisticMulti(@Param("table") TableDTO table);

}
