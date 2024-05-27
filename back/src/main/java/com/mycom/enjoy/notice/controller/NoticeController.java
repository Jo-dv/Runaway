package com.mycom.enjoy.notice.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoy.member.dto.MemberDto;
import com.mycom.enjoy.notice.dto.NoticeDto;
import com.mycom.enjoy.notice.dto.NoticeParamDto;
import com.mycom.enjoy.notice.dto.NoticeResultDto;
import com.mycom.enjoy.notice.service.NoticeService;

@RestController
public class NoticeController {
	private final NoticeService service;

	public NoticeController(NoticeService service) {
		this.service = service;
	}
	private final int SUCCESS = 1;
	private final int FAIL = -1;
//	noticeList
	@GetMapping("/notices")//@ModelAttribute
	public NoticeResultDto noticeList(@ModelAttribute NoticeParamDto noticeParamDto){
		NoticeResultDto noticeResultDto;
		System.out.println(noticeParamDto);
		if (noticeParamDto.getSearchWord() == null) { // isEmpty()
			noticeResultDto = service.noticeList(noticeParamDto);
			System.out.println(noticeResultDto);
		} else {
			noticeResultDto = service.noticeListSearchWord(noticeParamDto);
		}
		return noticeResultDto;
	}
//	noticeInsert
	@PostMapping("/notices") //session에서 확인 필요.
	public NoticeResultDto noticeInsert(@RequestBody NoticeDto dto,HttpSession session){
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		MemberDto member = (MemberDto) session.getAttribute("memberDto");
		if(member!=null) {
			if(member.getMemberPosition().equals("관리자")) {
				dto.setMemberId(member.getMemberId());
				noticeResultDto = service.noticeInsert(dto);
			}
		}else {
			noticeResultDto.setResult(FAIL);
		}
		
		
		return noticeResultDto;
	}
//	noticeUpdate
	@PutMapping("/notices")
	public NoticeResultDto noticeUpdate(@RequestBody NoticeDto dto){
		NoticeResultDto noticeResultDto = service.noticeUpdate(dto);
		return noticeResultDto;
	}
//	noticeDelete
	@DeleteMapping("/notices/{noticeId}")
	public NoticeResultDto noticeDelete(@PathVariable int noticeId){
		NoticeResultDto noticeResultDto = service.noticeDelete(noticeId);
		return noticeResultDto;
	}
//	noticeDetail
	@GetMapping("/notices/{noticeId}")
	public NoticeResultDto noticeDetail(@PathVariable int noticeId,HttpSession session){
		NoticeParamDto paramDto = new NoticeParamDto();
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		MemberDto member = (MemberDto) session.getAttribute("memberDto");
		if (member != null) {
			paramDto.setMemberId(member.getMemberId());
			paramDto.setNoticeId(noticeId);
			noticeResultDto = service.noticeDetail(paramDto);
			if (member.getMemberId() == noticeResultDto.getDto().getMemberId()) {
				noticeResultDto.getDto().setSameUser(true);
			}
		}else {
			noticeResultDto.setResult(FAIL);
		}

		
		return noticeResultDto;
	}
}
