package org.zerock.service.sta;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
	
	private List<Map<String,Object>> getStatisticsMultiList(TableDTO table){
		List<Map<String, Object>> rs = new ArrayList<Map<String,Object>>();
		
		String k1 = table.getFormatColumn(), k2 = table.getFormatColumn2();
		Map<String, Object> tmp = new HashMap<String, Object>();
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> stalist = (List<Map<String, Object>>) umapper.getStatisticMulti(table);
		for (Map<String, Object> map : stalist) {
			if(!tmp.containsKey("-")||!(tmp.get("-").equals(map.get(k1).toString()))) {
				if(tmp.size()!=0)
				rs.add(tmp);
				tmp = new HashMap<String, Object>();
				tmp.put("-", map.get(k1));
			}
			tmp.put(map.get(k2).toString(), map.get("cnt"));
		}
		rs.add(tmp);
		
		
		return rs;
	}
	
	@Override
	public Map<String,Map<String, Object>> getStatisticsMap(TableDTO table){

		@SuppressWarnings("unchecked")
		List<Map<String, Object>> stalist = (List<Map<String, Object>>) umapper.getStatisticMulti(table);
		Map<String, Map<String,Object>> rs = new TreeMap<String, Map<String,Object>>();
		
		String k1 = table.getFormatColumn(), k2 = table.getFormatColumn2();
		
		for (Map<String, Object> map : stalist) {
			//log.info(map);
			
			String key = (String)map.get(k1);
			if(!rs.containsKey(key)) {
				rs.put(key, new HashMap<String, Object>());
			}
			String key2 = (String)map.get(k2);
			Map<String, Object> temp = rs.get(key);
			temp.put(key2, map.get("cnt"));
			rs.replace(key, temp);
		}
		
		return rs;  
	}

	@Override
	public List<?> getStatistics(boolean multi, TableDTO table){
		return multi?getStatisticsMultiList(table):umapper.getStatistic(table);
	}
	
	
	
	@Override
	public void poiMaker(HttpServletResponse response,List<Map<String, Object>> datas,String title,boolean multi) throws Exception {
		// TODO Auto-generated method stub
		
		List<Map<String, Object>> list = datas;
		Set<String> keys = multi?new TreeSet<>(datas.get(0).keySet()):datas.get(0).keySet();
		
		 Workbook wb = new HSSFWorkbook();

		    Sheet sheet = wb.createSheet("게시판");

		    Row row = null;

		    Cell cell = null;

		    int rowNo = 0;



		    // 테이블 헤더용 스타일

		    CellStyle headStyle = wb.createCellStyle();

		    // 가는 경계선

		    headStyle.setBorderTop(BorderStyle.THIN);
		    headStyle.setBorderBottom(BorderStyle.THIN);
		    headStyle.setBorderLeft(BorderStyle.THIN);
		    headStyle.setBorderRight(BorderStyle.THIN);



		    // 배경색은 노란색

		    headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
		    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		   
		    // 데이터는 가운데 정렬
		    headStyle.setAlignment(HorizontalAlignment.CENTER);

		    // 데이터용 경계 스타일 테두리만 지정

		    CellStyle bodyStyle = wb.createCellStyle();

		    bodyStyle.setBorderTop(BorderStyle.THIN);
		    bodyStyle.setBorderBottom(BorderStyle.THIN);
		    bodyStyle.setBorderLeft(BorderStyle.THIN);
		    bodyStyle.setBorderRight(BorderStyle.THIN);

		    // 헤더 생성
		    row = sheet.createRow(rowNo++);
		    
		    int titleCnt = 0;
		    for (String key : keys) {
				
		    	cell = row.createCell(titleCnt++);
		    	cell.setCellStyle(headStyle);
		    	cell.setCellValue(key);
			}
		    
		    titleCnt = 0;

		    // 데이터 부분 생성

		    for(Map<String, Object> mapData : list) {

		        row = sheet.createRow(rowNo++);
		        
		        for (String key  : keys) {
			    	cell = row.createCell(titleCnt++);
			    	cell.setCellStyle(key.equals("-")?headStyle:bodyStyle);
			    	cell.setCellValue(mapData.get(key).toString());
				}
		        titleCnt = 0;
		        
		    }



		    // 컨텐츠 타입과 파일명 지정

		    response.setContentType("ms-vnd/excel");
		    response.setHeader("Content-Disposition", "attachment;filename="+title+".xls");

		    // 엑셀 출력

		   wb.write(response.getOutputStream());
		   wb.close();




		
		
	}
	
	
	
}
