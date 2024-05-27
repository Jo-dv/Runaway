package com.mycom.enjoy.bookmark.service;

import java.util.List;

import com.mycom.enjoy.bookmark.dto.BookmarkDto;
import com.mycom.enjoy.member.dto.MemberDto;

public interface BookmarkService {
	BookmarkDto bookmarkValidate(int memberId,int contentId);
	int bookmarkRegister(int memberId,int contentId);
	int bookmarkDelete(int memberId,int contentId);
	List<BookmarkDto> bookmarkList(int memberId);
}	
