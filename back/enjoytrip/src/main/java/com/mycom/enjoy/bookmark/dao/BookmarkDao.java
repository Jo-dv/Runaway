package com.mycom.enjoy.bookmark.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.enjoy.bookmark.dto.BookmarkDto;
@Mapper
public interface BookmarkDao {
	BookmarkDto bookmarkValidate(int memberId,int contentId);
	int bookmarkRegister(int memberId,int contentId);
	int bookmarkDelete(int memberId,int contentId);
	List<BookmarkDto> bookmarkList(int memberId);
}
