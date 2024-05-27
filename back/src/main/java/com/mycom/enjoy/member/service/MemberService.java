package com.mycom.enjoy.member.service;

import java.util.List;

import com.mycom.enjoy.member.dto.LoginResultDto;
import com.mycom.enjoy.member.dto.MemberDto;
import com.mycom.enjoy.member.dto.MemberUpdatePwdParams;

public interface MemberService {
	// memberRegister
	int memberRegister(MemberDto dto);

	// memberUpdateInfo
	int memberUpdateInfo(MemberDto dto);

	// memberUpdatePwd
	int memberUpdatePwd(MemberUpdatePwdParams updatePwdParams);

	// memberDelete
	int memberDelete(int memberId);

	// memberList
	List<MemberDto> memberList();

	// memberDetail
	MemberDto memberDetail(int memberId);

	// login
	LoginResultDto login(MemberDto memberDto);
}
