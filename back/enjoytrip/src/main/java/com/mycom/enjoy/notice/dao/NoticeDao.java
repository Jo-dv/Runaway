package com.mycom.enjoy.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.enjoy.board.dto.BoardDto;
import com.mycom.enjoy.notice.dto.NoticeDto;

@Mapper
public interface NoticeDao {
	int noticeRegister(NoticeDto dto);
	int noticeUpdate(NoticeDto dto);
	int noticeDelete(int noticeId);
	List<NoticeDto> noticeList();
	NoticeDto noticeDetail(int noticeId);
}
