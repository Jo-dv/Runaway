package com.mycom.enjoy.bookmark.service;

import java.util.List;

import com.mycom.enjoy.bookmark.dto.BookmarkDto;

public interface BookmarkService {
	int bookmarkRegister(int memberId,int contentId);
	int bookmarkDelete(int memberId,int contentId);
	List<BookmarkDto> bookmarkList(int memberId);
}	
