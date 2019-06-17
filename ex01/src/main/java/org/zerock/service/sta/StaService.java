package org.zerock.service.sta;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.TableDTO;

public interface StaService {
	
	public static final String TEST = "tbl_board",BOARD_10="tbl_board_10",BOARD_50="tbl_board_50",
			BOARD_100="tbl_board_100";
	
	
	public List<BoardVO> getList(TableDTO tableDTO);
	public List<BoardVO> getList(TableDTO tableDTO,Criteria cri);
	public int count(TableDTO tableDTO,Criteria cri);
	List<?> getStatistics(boolean multi, TableDTO table);
}
