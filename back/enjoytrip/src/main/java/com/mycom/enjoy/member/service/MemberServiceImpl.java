package com.mycom.enjoy.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycom.enjoy.member.dao.MemberDao;
import com.mycom.enjoy.member.dto.LoginResultDto;
import com.mycom.enjoy.member.dto.MemberDto;

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
	public int memberUpdatePwd(int memberId,String memberPwd) {
		return dao.memberUpdatePwd(memberId,memberPwd);
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
		if(memberDto!=null&&memberDto.getMemberPwd().equals(memberDto.getMemberPwd())) {
			memberDto.setMemberPwd("");
			return memberDto;
		}
		return null;
	}

}
