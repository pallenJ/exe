package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {

	public List<BoardVO> getList();
	public List<BoardVO> getListWithPaging(Criteria criteria);
	public void insert(BoardVO board);
	public BoardVO read(Long bno);
	public int delete(long bno);
	public int update(BoardVO board);
	public int count();
}
