package com.mycom.enjoy.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.enjoy.member.dto.LoginResultDto;
import com.mycom.enjoy.member.dto.MemberDto;

@Mapper
public interface MemberDao {
	//memberRegister
	int memberRegister(MemberDto dto);
	//memberUpdateInfo
	int memberUpdateInfo(MemberDto dto);
	//memberUpdatePwd
	int memberUpdatePwd(int memberId,String memberPwd);
	//memberDelete
	int memberDelete(int memberId);
	//memberList
	List<MemberDto> memberList();
	//memberDetail
	MemberDto memberDetail(int memberId);
	//login
	LoginResultDto login(String memberEmail);
	
}
