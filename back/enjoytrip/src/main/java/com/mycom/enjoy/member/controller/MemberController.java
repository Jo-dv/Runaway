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
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoy.member.dto.LoginResultDto;
import com.mycom.enjoy.member.dto.MemberDto;
import com.mycom.enjoy.member.dto.MemberUpdatePwdParams;
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
		if( service.memberRegister(dto)==1) {
			ans.put("result","success");
		}else {
			ans.put("result","fail");
		}
		return ans;

	}

//	memberUpdateInfo 
	@PutMapping("/members")
	public Map<String, String> memberUpdateInfo(@RequestBody MemberDto dto, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		dto.setMemberId(memberDto.getMemberId());
		session.setAttribute("memberDto", dto);
		Map<String, String> ans = new HashMap<>();
		int result = service.memberUpdateInfo(dto);
		if (result == 1) {
			ans.put("result", "success");
		} else {
			ans.put("result", "fail");
		}
		return ans;
	}

//	memberUpdatePwd
	@PutMapping("/members/pwd")
	public Map<String, String> memberUpdatePwd(@RequestBody MemberUpdatePwdParams updatePwdParams,
			HttpSession session) {
		System.out.println(updatePwdParams);
		Map<String, String> ans = new HashMap<>();
		MemberDto member = (MemberDto) session.getAttribute("memberDto");
		if (member != null && member.getMemberId() == updatePwdParams.getMemberId()) {
			int result = service.memberUpdatePwd(updatePwdParams);
			if (result == 1) {
				ans.put("result", "success");
			} else {
				ans.put("result", "fail");
			}
		} else {
			ans.put("result", "fail");
		}
		return ans;
	}

//	memberDelete
	@DeleteMapping("/members/delete/{memberId}")
	public Map<String, String> memberDelete(@PathVariable int memberId, HttpSession session) {
		Map<String, String> logoutResult = logout(session);
		Map<String, String> ans = new HashMap<>();
		if (logoutResult.get("result").equals("success")) {
			int result = service.memberDelete(memberId);
			if (result == 1) {
				ans.put("result", "success");
			} else {
				ans.put("result", "fail");
			}
		} else {
			ans.put("result", "fail");
		}

		return ans;
	}

//	memberList
	@GetMapping("/members")
	public List<MemberDto> memberList() {
		return service.memberList();
	}

//	memberDetail
	@GetMapping("/members/{memberId}")
	public MemberDto memberDetail(@PathVariable int memberId) {
		return service.memberDetail(memberId);
	}

//	login
	@PostMapping("/login")
	public Map<String, String> login(@RequestBody MemberDto memberDto, HttpSession session) {
		Map<String, String> ans = new HashMap<>();
		LoginResultDto loginResultDto = service.login(memberDto);
		MemberDto member = new MemberDto();
		// id,name,email,gender,birth,phone,region,position
		
		if (loginResultDto != null) {
			member.setMemberId(loginResultDto.getMemberId());
			member.setMemberName(loginResultDto.getMemberName());
			member.setMemberEmail(loginResultDto.getMemberEmail());
			member.setMemberGender(loginResultDto.getMemberGender());
			member.setMemberBirth(loginResultDto.getMemberBirth());
			member.setMemberPhone(loginResultDto.getMemberPhone());
			member.setMemberRegion(loginResultDto.getMemberRegion());
			member.setMemberPosition(loginResultDto.getMemberPosition());
			session.setAttribute("memberDto", member);

			ans.put("result", "success");
			ans.put("memberId", Integer.toString(member.getMemberId()));
			ans.put("memberName", member.getMemberName());
			ans.put("memberEmail", member.getMemberEmail());
			ans.put("memberGender", member.getMemberGender());
			ans.put("memberBirth", member.getMemberBirth());
			ans.put("memberPhone", member.getMemberPhone());
			ans.put("memberRegion", Integer.toString(member.getMemberRegion()));
			ans.put("memberPosition", member.getMemberPosition());
			ans.put("sidoName", loginResultDto.getSidoName());
		} else {
			ans.put("result", "fail");

		}
		return ans;
	}

//	logout
	@GetMapping("/logout")
	public Map<String, String> logout(HttpSession session) {
		Map<String, String> ans = new HashMap<>();
		session.invalidate();
		MemberDto memberDto = (MemberDto) session.getAttribute("memberDto");
		System.out.print("Logout : ");
		System.out.println(memberDto);
		ans.put("result", "success");
		return ans;
	}
}
