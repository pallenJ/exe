package org.zerock.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
//@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	@Setter(onMethod_ = {@Autowired})
	BoardMapper mapper;
	
	@Override
	public void register(BoardVO board) {
		mapper.insert(board);
	}

	@Override
	public BoardVO get(Long bno) {
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		return mapper.update(board)>0;
	}

	@Override
	public boolean remove(Long bno) {
		// TODO Auto-generated method stub
		return mapper.delete(bno)>0;
	}

	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		return mapper.getList();
	}

	
	  @Override public List<BoardVO> getList(Criteria cri) {
	  
	  
	  Criteria temp = new Criteria(cri.getAmount()*(cri.getPageNum()-1),
	  cri.getAmount(),cri.getType(),cri.getKeyword());
	  
	  return mapper.getListWithPaging(temp);
	  
	  }
	 
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return mapper.count();
	}

	
	
	  @Override public int count(Criteria cri) { // TODO Auto-generated method stub
	  return mapper.searchCount(cri); }

	@Override
	public Map<String, Integer> rcounts() {
		// TODO Auto-generated method stub
		return listToMap(mapper.getRcounts(), "bno", "rcnt");
	}
	 
	private Map<String,Integer> listToMap(List<Map<String,String>> list,String k1,String k2){
		Map<String,Integer> rs = new HashMap<>();
		for (Map<String, String> map : list) {
			rs.put(map.get(k1), Integer.parseInt(map.get(k2)));
		}
		return rs;
	}
	


}
