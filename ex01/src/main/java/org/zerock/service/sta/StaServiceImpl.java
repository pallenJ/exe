package org.zerock.service.sta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.TableDTO;
import org.zerock.mapper.UserDataMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class StaServiceImpl implements StaService{
	
	
	@Setter(onMethod_ = @Autowired)
	UserDataMapper umapper;
	

	
	@Override
	public List<BoardVO> getList(TableDTO tableDTO) {
		// TODO Auto-generated method stub
		return umapper.listByTableName(tableDTO);
	}

	@Override
	public List<BoardVO> getList(TableDTO tableDTO,Criteria cri) {
		// TODO Auto-generated method stub
		
		Criteria temp = new Criteria(cri.getAmount()*(cri.getPageNum()-1), cri.getAmount(),cri.getType(),cri.getKeyword());
		return umapper.getListPaging(tableDTO, temp);
	}

	@Override
	public int count(TableDTO tableDTO, Criteria cri) {
		// TODO Auto-generated method stub
		return umapper.getCountPaging(tableDTO, cri);
	}
	
	@Override
	public List<?> getStatistics(boolean multi, TableDTO table){
		return multi?umapper.getStatisticMulti(table):umapper.getStatistic(table);
	}
	
}
