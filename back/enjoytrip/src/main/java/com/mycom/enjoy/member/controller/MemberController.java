package com.mycom.enjoy.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoy.member.dto.LoginResultDto;
import com.mycom.enjoy.member.dto.MemberDto;
import com.mycom.enjoy.member.service.MemberService;

@RestController
public class MemberController {
	private final MemberService service;

	public MemberController(MemberService service) {
		this.service = service;
	}

//	memberRegister
	@PostMapping("/members")
	public Map<String,String> memberRegist(@RequestBody MemberDto dto) {
		Map<String,String> ans = new HashMap<>();
		System.out.println(dto);
		if( service.memberRegister(dto)==1) {
			ans.put("result","success");
		}else {
			ans.put("result","fail");
		}
		return ans;
		
	}
//	memberUpdateInfo 
	@PutMapping("/members")
	public Map<String,String> memberUpdateInfo(@RequestBody MemberDto dto,HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		dto.setMemberId(memberDto.getMemberId());
		session.setAttribute("memberDto", dto);
		Map<String,String> ans = new HashMap<>();
		int result = service.memberUpdateInfo(dto);
		if(result==1) {
			ans.put("result","success");
		}else {
			ans.put("result","fail");
		}
		return ans;
	}
//	memberUpdatePwd
	@PutMapping("/members/pwd")
	public int memberUpdatePwd(@RequestParam String memberPwd,HttpSession session) {
		MemberDto member = (MemberDto)session.getAttribute("memberDto");
		return service.memberUpdatePwd(member.getMemberId(),memberPwd);
	}
	
//	memberDelete
	@DeleteMapping("/members/delete/{memberId}")
	public int memberDelete(@PathVariable int memberId) {
		return service.memberDelete(memberId);
	}
//	memberList
	@GetMapping("/members")
	public List<MemberDto> memberList(){
		return service.memberList();
	}
//	memberDetail
	@GetMapping("/members/{memberId}")
	public MemberDto memberDetail(@PathVariable int memberId) {
		return service.memberDetail(memberId);
	}
//	login
	@PostMapping("/login")
	public Map<String,String> login(@RequestBody MemberDto memberDto,HttpSession session) {
		Map<String,String> ans = new HashMap<>();
		LoginResultDto loginResultDto = service.login(memberDto);
		MemberDto member = new MemberDto();
		//id,name,email,gender,birth,phone,region,position
		member.setMemberId(loginResultDto.getMemberId());
		member.setMemberName(loginResultDto.getMemberName());
		member.setMemberEmail(loginResultDto.getMemberEmail());
		member.setMemberGender(loginResultDto.getMemberGender());
		member.setMemberBirth(loginResultDto.getMemberBirth());
		member.setMemberPhone(loginResultDto.getMemberPhone());
		member.setMemberRegion(loginResultDto.getMemberRegion());
		member.setMemberPosition(loginResultDto.getMemberPosition());
		
		if(memberDto!=null) {
			session.setAttribute("memberDto", member);
			ans.put("result","success");
			ans.put("memberId",Integer.toString(member.getMemberId()));
			ans.put("memberName",member.getMemberName());
			ans.put("memberEmail",member.getMemberEmail());
			ans.put("memberGender",member.getMemberGender());
			ans.put("memberBirth",member.getMemberBirth());
			ans.put("memberPhone",member.getMemberPhone());
			ans.put("memberRegion",Integer.toString(member.getMemberRegion()));
			ans.put("memberPosition",member.getMemberPosition());
			ans.put("sidoName",loginResultDto.getSidoName());
		}else {
			ans.put("result","fail");
		}
		return ans;
	}
//	logout
	@GetMapping("/logout")
	public Map<String,String> logout(HttpSession session) {
		Map<String,String> ans = new HashMap<>();
		session.invalidate();
		ans.put("result","success");
		return ans;
	}
}

