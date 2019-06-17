package org.zerock.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String userid);
	public int countSame(@Param("userid") String userid);
	public List<String> allIdList();
	@Select("select userid from tbl_member")
	public Set<String> allIdList2();
}
