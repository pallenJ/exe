package org.zerock.mapper;

import java.util.List;
import java.util.Map;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {

	public List<BoardVO> getList();
	//public List<BoardVO> getListWithPaging(Criteria cri);
	public void insert(BoardVO board);
	public BoardVO read(Long bno);
	public int delete(long bno);
	public int update(BoardVO board);
	public int count();
	public List<BoardVO> getListWithPaging(Criteria cri);
	public int searchCount(Criteria cri);
	
	public List<Map<String, String>> getRcounts();
	public int getRcount(long bno);
	
}
