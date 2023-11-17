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
