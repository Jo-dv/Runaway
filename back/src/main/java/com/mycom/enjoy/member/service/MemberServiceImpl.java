package com.mycom.enjoy.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.enjoy.member.dao.MemberDao;
import com.mycom.enjoy.member.dto.LoginResultDto;
import com.mycom.enjoy.member.dto.MemberDto;
import com.mycom.enjoy.member.dto.MemberUpdatePwdParams;

@Service
public class MemberServiceImpl implements MemberService{
	private final MemberDao dao;
	
	public MemberServiceImpl(MemberDao dao) {
		this.dao = dao;
	}

	@Override
	public int memberRegister(MemberDto dto) {
		return dao.memberRegister(dto);
	}

	@Override
	public int memberUpdateInfo(MemberDto dto) {
		return dao.memberUpdateInfo(dto);
	}

	@Override
	@Transactional
	public int memberUpdatePwd(MemberUpdatePwdParams updatePwdParams) {
		//1. 현재 비밀번호 일치하는지 확인
		int checkPwd = dao.memberPwdCheck(updatePwdParams);
		if(checkPwd==1) {
			//2. 일치하면 updatePwd 함.
			return dao.memberUpdatePwd(updatePwdParams);
		}else {
			return -1;
		}
	}

	@Override
	public int memberDelete(int memberId) {
		return dao.memberDelete(memberId);
	}

	@Override
	public List<MemberDto> memberList() {
		return dao.memberList();
	}

	@Override
	public MemberDto memberDetail(int memberId) {
		return dao.memberDetail(memberId);
	}

	@Override
	public LoginResultDto login(MemberDto dto) {
		LoginResultDto memberDto = dao.login(dto.getMemberEmail());
		if(memberDto!=null&&memberDto.getMemberPwd().equals(dto.getMemberPwd())) {
			memberDto.setMemberPwd("");
			return memberDto;
		}
		return null;
	}

}
