package com.mycom.enjoy.bookmark.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.enjoy.bookmark.dto.BookmarkDto;
import com.mycom.enjoy.bookmark.service.BookmarkService;
import com.mycom.enjoy.member.dto.MemberDto;

@RestController
public class BookmarkController {
	private final BookmarkService service;
	
public BookmarkController(BookmarkService service) {
		this.service = service;
	}

	// 추가 및 삭제 수행 전 이미 추가된 값인지 혹은 삭제할 값이 db에 있는 것인지 유효성 판단
	@GetMapping("/bookmarks/{contentId}")
	public int bookmarkValidate(@PathVariable int contentId,HttpSession session) {
		MemberDto user = (MemberDto)session.getAttribute("memberDto");
		BookmarkDto validation;
		int memberId;
		
		if(user == null)
			return -1;  // 로그인 요청
		else {
			memberId = (user).getMemberId();
			validation = service.bookmarkValidate(memberId, contentId);
			if(validation == null)
				return 0;  // 데이터가 없으면 등록 요청
			else
				return 1;  // 삭제 요청
		}
	}

//	bookmarkRegister
	@PostMapping("/bookmarks")
	public int bookmarkRegister(@RequestBody int contentId,HttpSession session) {
		int memberId = ((MemberDto)session.getAttribute("memberDto")).getMemberId();
		return service.bookmarkRegister(memberId, contentId);
	}
//	bookmarkDelete
	@DeleteMapping("/bookmarks/{contentId}")
	public int bookmarkDelete(@PathVariable int contentId,HttpSession session) {
		int memberId = ((MemberDto)session.getAttribute("memberDto")).getMemberId();
		return service.bookmarkDelete(memberId,contentId);
	}
//	bookmarkList
	@GetMapping("/bookmarks")
	public List<BookmarkDto> bookmarkList(HttpSession session){
		int memberId = ((MemberDto)session.getAttribute("memberDto")).getMemberId();;
		return service.bookmarkList(memberId);
	}
}
