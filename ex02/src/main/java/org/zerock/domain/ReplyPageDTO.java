package org.zerock.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ReplyPageDTO {

	private int replyCnt;
	private List<ReplyVO> list;
	
	/*
	 * private int startPage; private int endPage; private boolean prev, next;
	 * 
	 * private int total; private Criteria cri;
	 */
	/*
	 * public ReplyPageDTO(int total, Criteria cri) { super(); this.total = total;
	 * this.cri = cri;
	 * 
	 * this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10; this.startPage =
	 * endPage - 9;
	 * 
	 * int realEnd = (int)(Math.ceil(total*1.0))/cri.getAmount()+(total%10==0?0:1);
	 * 
	 * if(realEnd < this.endPage) { this.endPage = realEnd; } this.prev =
	 * this.startPage>1; this.next = this.endPage <realEnd; }
	 */
	
	
}