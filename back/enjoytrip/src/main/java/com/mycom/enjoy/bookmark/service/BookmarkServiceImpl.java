package com.mycom.enjoy.bookmark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycom.enjoy.bookmark.dao.BookmarkDao;
import com.mycom.enjoy.bookmark.dto.BookmarkDto;
@Service
public class BookmarkServiceImpl implements BookmarkService{
	private final BookmarkDao dao;
	
	public BookmarkServiceImpl(BookmarkDao dao) {
		this.dao = dao;
	}
	@Override
	public int bookmarkRegister(int memberId,int contentId) {
		return dao.bookmarkRegister(memberId,contentId);
	}

	@Override
	public int bookmarkDelete(int memberId, int contentId) {
		return dao.bookmarkDelete(memberId, contentId);
	}

	@Override
	public List<BookmarkDto> bookmarkList(int memberId) {
		return dao.bookmarkList(memberId);
	}

}
